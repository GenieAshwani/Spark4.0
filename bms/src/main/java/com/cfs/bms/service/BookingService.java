package com.cfs.bms.service;

import com.cfs.bms.dto.*;
import com.cfs.bms.exception.ResourceNotFoundException;
import com.cfs.bms.exception.SeatUnavailableException;
import com.cfs.bms.model.*;
import com.cfs.bms.repository.BookingRepository;
import com.cfs.bms.repository.ShowRepository;
import com.cfs.bms.repository.ShowSeatRepository;
import com.cfs.bms.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public BookingDto createBooking(BookingRequestDto bookingRequest)
    {
        User user= userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        Show show = showRepository.findById(bookingRequest.getShowId())
                .orElseThrow(()->new ResourceNotFoundException("Show Not Found"));

        List<ShowSeat> selectedSeats=showSeatRepository.findAllById(bookingRequest.getSeatIds());

        for(ShowSeat seat:selectedSeats)
        {
            if(!"AVAILABLE".equals(seat.getStatus()))
            {
                throw  new SeatUnavailableException("Seat "+seat.getSeat().getSeatNumber()+" is not available");
            }
            seat.setStatus("LOCKED");
        }
        showSeatRepository.saveAll(selectedSeats);

        Double totalAmount=selectedSeats.stream()
                .mapToDouble(ShowSeat::getPrice)
                .sum();

        //payment
        Payment payment=new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(bookingRequest.getPaymentMethod());
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());


        //booking
        Booking booking=new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setTotalAmount(totalAmount);
        booking.setBookingNumber(UUID.randomUUID().toString());
        booking.setPayment(payment);

        Booking saveBooking=bookingRepository.save(booking);

        selectedSeats.forEach(seat->
        {
            seat.setStatus("BOOKED");
            seat.setBooking(saveBooking);
        });
        showSeatRepository.saveAll(selectedSeats);
        return mapToBookingDto(saveBooking,selectedSeats);
    }


    public BookingDto getBookingById(Long id)
    {
        Booking booking=bookingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Booking Not Found"));
        List<ShowSeat> seats=showSeatRepository.findAll()
                .stream().
                filter(seat->seat.getBooking()!=null && seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());
        return mapToBookingDto(booking,seats);
    }

    private BookingDto getBookingByNumber(String bookingNumber)
    {
        Booking booking=bookingRepository.findByBookingNumber(bookingNumber)
                .orElseThrow(()->new ResourceNotFoundException("Booking Not Found"));
        List<ShowSeat> seats=showSeatRepository.findAll()
                .stream().
                filter(seat->seat.getBooking()!=null && seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());
        return mapToBookingDto(booking,seats);
    }

    private List<BookingDto> getBookingByUserId(Long userId)
    {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
       return bookings.stream()
               .map(booking -> {
                   List<ShowSeat> seats=showSeatRepository.findAll()
                           .stream().
                   filter(seat->seat.getBooking()!=null && seat.getBooking().getId().equals(booking.getId()))
                           .collect(Collectors.toList());
                   return mapToBookingDto(booking,seats);
               })
               .collect(Collectors.toList());

    }

    @Transactional
    public BookingDto cancelBooking(Long id)
    {
        Booking booking=bookingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Booking Not found"));

        booking.setStatus("CANCELLED");

        List<ShowSeat> seats=showSeatRepository.findAll()
                .stream()
                .filter(seat->seat.getBooking()!=null && seat.getBooking().getId().equals(booking.getId()))
                .collect(Collectors.toList());

        seats.forEach(seat->{
            seat.setStatus("AVAILABLE");
            seat.setBooking(null);
        });

        if (booking.getPayment()!=null)
        {
            booking.getPayment().setStatus("REFUNDED");
        }

        Booking updateBooking=bookingRepository.save(booking);
        showSeatRepository.saveAll(seats);
        return mapToBookingDto(updateBooking,seats);

    }


    private BookingDto mapToBookingDto(Booking booking,List<ShowSeat> seats)
    {
            BookingDto bookingDto=new BookingDto();
            bookingDto.setId(booking.getId());
            bookingDto.setBookingNumber(booking.getBookingNumber());
            bookingDto.setBookingTime(booking.getBookingTime());
            bookingDto.setStatus(bookingDto.getStatus());
            bookingDto.setTotalAmount(booking.getTotalAmount());

            //user
            UserDto userDto=new UserDto();
            userDto.setId(booking.getUser().getId());
            userDto.setName(booking.getUser().getName());
            userDto.setEmail(booking.getUser().getEmail());
            userDto.setPhoneNumber(booking.getUser().getPhoneNumber());
            bookingDto.setUser(userDto);

            ShowDto showDto=new ShowDto();
            showDto.setId(booking.getShow().getId());
            showDto.setStartTime(booking.getShow().getStartTime());
            showDto.setEndTime(booking.getShow().getEndTime());

            MovieDto movieDto = new MovieDto();
            movieDto.setId(booking.getShow().getMovie().getId());
            movieDto.setTitle(booking.getShow().getMovie().getTitle());
            movieDto.setDescription(booking.getShow().getMovie().getDescription());
            movieDto.setLanguage(booking.getShow().getMovie().getLanguage());
            movieDto.setGenre(booking.getShow().getMovie().getGenre());
            movieDto.setDurationMins(booking.getShow().getMovie().getDurationMins());
            movieDto.setReleaseDate(booking.getShow().getMovie().getReleaseDate());
            movieDto.setPosterUrl(booking.getShow().getMovie().getPosterUrl());
            showDto.setMovie(movieDto);


            ScreenDto screenDto=new ScreenDto();
            screenDto.setId(booking.getShow().getScreen().getId());
            screenDto.setName(booking.getShow().getScreen().getName());
            screenDto.setTotalSeats(booking.getShow().getScreen().getTotalSeats());


            TheaterDto theaterDto=new TheaterDto();
            theaterDto.setId(bookingDto.getShow().getScreen().getTheater().getId());
            theaterDto.setName(bookingDto.getShow().getScreen().getTheater().getName());
            theaterDto.setAddress(bookingDto.getShow().getScreen().getTheater().getAddress());
            theaterDto.setCity(bookingDto.getShow().getScreen().getTheater().getCity());
            theaterDto.setTotalScreens(bookingDto.getShow().getScreen().getTheater().getTotalScreens());

            screenDto.setTheater(theaterDto);
            showDto.setScreen(screenDto);
            bookingDto.setShow(showDto);


            List<ShowSeatDto> seatDtos=seats.stream()
                    .map(seat->{
                        ShowSeatDto seatDto=new ShowSeatDto();
                        seatDto.setId(seat.getId());
                        seatDto.setStatus(seat.getStatus());
                        seatDto.setPrice(seat.getPrice());

                        SeatDto baseSeatDto=new SeatDto();
                        baseSeatDto.setId(seat.getSeat().getId());
                        baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());
                        baseSeatDto.setSeatType(seat.getSeat().getSeatType());
                        baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());
                        seatDto.setSeat(baseSeatDto);
                        return seatDto;
                    })
                    .collect(Collectors.toList());
            bookingDto.setSeats(seatDtos);


            if(booking.getPayment()!=null)
            {
                PaymentDto paymentDto=new PaymentDto();
                paymentDto.setId(booking.getPayment().getId());
                paymentDto.setAmount(booking.getPayment().getAmount());
                paymentDto.setPaymentMethod(booking.getPayment().getPaymentMethod());
                paymentDto.setPaymentTime(booking.getPayment().getPaymentTime());
                paymentDto.setStatus(booking.getPayment().getStatus());
                paymentDto.setTransactionId(booking.getPayment().getTransactionId());
                bookingDto.setPayment(paymentDto);
            }

            return bookingDto;

    }
}

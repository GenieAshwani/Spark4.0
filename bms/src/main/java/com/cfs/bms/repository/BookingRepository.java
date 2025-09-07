package com.cfs.bms.repository;

import com.cfs.bms.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>
{
    List<Booking> findByUserId(Long userId);
    Optional<Booking> findByBookingNumber(String bookingNumber);

    List<Booking> findByShowId(Long id);

}

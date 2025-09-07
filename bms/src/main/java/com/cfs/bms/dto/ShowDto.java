package com.cfs.bms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private MovieDto movie;
    private ScreenDto screen;
    private List<ShowSeatDto> availableSeats;
}

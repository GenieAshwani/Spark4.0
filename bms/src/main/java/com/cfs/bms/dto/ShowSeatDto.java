package com.cfs.bms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDto {
    private Long id;
    private SeatDto seat;
    private String status;
    private Double price;
}

package com.cfs.bms.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //table

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String seatType; // GOLD , SILVER, PLATINUM

    @Column(nullable = false)
    private Double basePrice;

    @ManyToOne
    @JoinColumn(name="screen_id",nullable = false)
    private Screen screen;
}

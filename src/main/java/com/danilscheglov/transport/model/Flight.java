package com.danilscheglov.transport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "dispatcher_id", nullable = false)
    private Dispatcher dispatcher;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false, length = 255)
    private String flightStartpoint;

    @Column(nullable = false, length = 255)
    private String flightEndpoint;

    @Column(nullable = false)
    private Double flightDistance;

    @Column(nullable = false)
    private Date flightDepartureDate;
}

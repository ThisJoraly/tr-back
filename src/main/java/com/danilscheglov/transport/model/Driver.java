package com.danilscheglov.transport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    @Column(nullable = false, length = 50)
    private String driverSurname;

    @Column(nullable = false, length = 50)
    private String driverName;

    @Column(length = 50)
    private String driverPatronymic;

    @Column(nullable = false, length = 25)
    private String driverPhone;

    @Column(nullable = false)
    private Integer driverExperience;

    public Long getDriverId() {
        return driverId;
    }
}

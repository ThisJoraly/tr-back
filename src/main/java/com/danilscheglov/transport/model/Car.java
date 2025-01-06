package com.danilscheglov.transport.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "carId")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    @JsonManagedReference
    private Driver driver;

    @Column(nullable = false, length = 20)
    private String carNumber;

    @Column(nullable = false, length = 50)
    private String carModel;

    @Column(nullable = false, length = 50)
    private String carBrand;

    @Column(nullable = false)
    private Double carCapacity;

    @Column(nullable = false)
    private Integer carMileage;

    @Column(nullable = false, length = 20)
    private String carCondition;

    @Column
    private Date carLastMaintenanceDate;

    @Version
    private Long version;
}

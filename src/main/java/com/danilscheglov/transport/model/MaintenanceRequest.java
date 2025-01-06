package com.danilscheglov.transport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaintenanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenanceRequestId;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @JsonManagedReference
    private Car car;

    @ManyToOne
    @JoinColumn(name = "mechanic_id", nullable = false)
    @JsonManagedReference
    private Mechanic mechanic;

    @Column(nullable = false)
    private Date fillingDate;

    @Column(nullable = false, length = 50)
    private String serviceType;

    @Column(nullable = false, length = 20)
    private String maintenanceRequestStatus;

    @Column(length = 1000)
    private String maintenanceRequestNote;
}

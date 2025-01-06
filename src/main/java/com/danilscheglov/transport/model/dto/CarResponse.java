package com.danilscheglov.transport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private Long carId;
    private String carNumber;
    private String carModel;
    private String carBrand;
    private Double carCapacity;
    private Integer carMileage;
    private String carCondition;
    private Date carLastMaintenanceDate;
    private Long driverId;
}


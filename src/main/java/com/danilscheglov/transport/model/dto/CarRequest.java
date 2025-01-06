package com.danilscheglov.transport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private String carNumber;
    private String carModel;
    private String carBrand;
    private Double carCapacity;
    private Integer carMileage;
    private String carCondition;
    private Long driverId;
}

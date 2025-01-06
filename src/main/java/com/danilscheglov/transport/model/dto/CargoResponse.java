package com.danilscheglov.transport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoResponse {
    private Long cargoId;
    private String cargoType;
    private Double cargoWeight;
    private Double cargoVolume;
    private Long orderId;
}


package com.danilscheglov.transport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String clientName;
    private String operatorName;
    private Long flightId;
    private String orderStartpoint;
    private String orderEndpoint;
    private Date orderDispatchDate;
    private Date orderDeliveryDate;
    private String orderStatus;
}

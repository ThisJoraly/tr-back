package com.danilscheglov.transport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "operator_id", nullable = false)
    private Operator operator;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(nullable = false, length = 255)
    private String orderStartpoint;

    @Column(nullable = false, length = 255)
    private String orderEndpoint;

    @Column(nullable = false)
    private Date orderDispatchDate;

    @Column(nullable = false)
    private Date orderDeliveryDate;

    @Column(nullable = false, length = 20)
    private String orderStatus;
}

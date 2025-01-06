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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false, length = 100)
    private String clientName;

    @Column(nullable = false, length = 25)
    private String clientPhone;

    @Column(length = 100)
    private String clientEmail;

    @Column(length = 255)
    private String clientAddress;

    @Column(nullable = false, length = 50)
    private String clientType;
}

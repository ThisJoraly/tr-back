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
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operatorId;

    @Column(nullable = false, length = 50)
    private String operatorSurname;

    @Column(nullable = false, length = 50)
    private String operatorName;

    @Column(length = 50)
    private String operatorPatronymic;

    @Column(nullable = false, length = 25)
    private String operatorPhone;

    @Column(length = 100)
    private String operatorEmail;

    @Column(nullable = false)
    private Date operatorStartDate;

    @Column(nullable = false)
    private Date operatorEndDate;
}

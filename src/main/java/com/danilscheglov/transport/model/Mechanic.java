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
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mechanicId;

    @Column(nullable = false, length = 50)
    private String mechanicSurname;

    @Column(nullable = false, length = 50)
    private String mechanicName;

    @Column(length = 50)
    private String mechanicPatronymic;

    @Column(nullable = false, length = 25)
    private String mechanicPhone;

    @Column(length = 100)
    private String mechanicEmail;
}

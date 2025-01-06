package com.danilscheglov.transport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Dispatcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dispatcherId;

    @Column(nullable = false, length = 50)
    private String dispatcherSurname;

    @Column(nullable = false, length = 50)
    private String dispatcherName;

    @Column(length = 50)
    private String dispatcherPatronymic;

    @Column(nullable = false, length = 25)
    private String dispatcherPhone;

    @Column(length = 100)
    private String dispatcherEmail;

    @Column(nullable = false)
    private Date dispatcherStartChange;

    @Column(nullable = false)
    private Date dispatcherEndChange;
}

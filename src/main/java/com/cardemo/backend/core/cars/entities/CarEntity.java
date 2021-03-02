package com.cardemo.backend.core.cars.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class CarEntity implements Serializable {

    private static final long serialVersionUID = 5751959429446536876L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name ="first_name")
    private String name;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="birth_date")
    private Date birthDate;

    @Column(name ="email")
    private String email;

    @Column(name ="password")
    private String password;
}

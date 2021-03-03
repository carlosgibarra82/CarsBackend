package com.cardemo.backend.core.cars.entities;

import com.cardemo.backend.core.users.entities.UserEntity;
import com.cardemo.backend.generics.datatypes.CarType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class CarEntity implements Serializable {

    private static final long serialVersionUID = 5751959429446536876L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name ="license_plate", nullable = false, length = 50)
    private String licensePlate;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Column(name = "type", nullable = false, length = 1)
    private CarType carType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

}

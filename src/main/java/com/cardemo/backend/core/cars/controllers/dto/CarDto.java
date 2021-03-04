package com.cardemo.backend.core.cars.controllers.dto;

import com.cardemo.backend.core.users.entities.UserEntity;
import com.cardemo.backend.generics.datatypes.CarType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
        private long Id;
        private String licensePlate;
        private String color;
        private CarType carType;
        private UserEntity userEntity;
}

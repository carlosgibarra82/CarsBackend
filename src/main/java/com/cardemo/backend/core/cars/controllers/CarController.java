package com.cardemo.backend.core.cars.controllers;

import com.cardemo.backend.core.cars.entities.CarEntity;
import com.cardemo.backend.core.cars.services.repositories.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CarController {

    @Autowired
    private ICarRepository carRepository;

    @GetMapping("/cars")
    public List<CarEntity> getAllCars(){
        return carRepository.findAll();
    }
}

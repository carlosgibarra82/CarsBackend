package com.cardemo.backend.core.cars.controllers;

import com.cardemo.backend.core.cars.controllers.dto.CarDto;
import com.cardemo.backend.core.cars.entities.CarEntity;
import com.cardemo.backend.core.cars.mappers.ICarMapper;
import com.cardemo.backend.core.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CarDto get(@PathVariable Long id){
        return ICarMapper.MAPPER.carEntityToCarDto((carService.getOne(id)));
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<CarDto> search(){
        return ICarMapper.MAPPER.carEntitiesToCarDto(carService.findAll());
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CarDto create(@RequestBody CarDto dto){
        return ICarMapper.MAPPER.carEntityToCarDto(carService.create(ICarMapper.MAPPER.createCarDtoToCarEntity(dto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CarDto update(@PathVariable Long id, @RequestBody  CarDto dto){
        CarEntity carEntity = ICarMapper.MAPPER.updateCarDtoToCarEntity(dto);
        carEntity.setId(id);
        return ICarMapper.MAPPER.carEntityToCarDto(this.carService.update(carEntity));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return this.carService.delete(id);
    }
}

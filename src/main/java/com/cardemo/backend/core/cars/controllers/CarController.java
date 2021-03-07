package com.cardemo.backend.core.cars.controllers;

import com.cardemo.backend.core.cars.controllers.dto.CarDto;
import com.cardemo.backend.core.cars.entities.CarEntity;
import com.cardemo.backend.core.cars.mappers.ICarMapper;
import com.cardemo.backend.core.cars.services.CarService;
import com.cardemo.backend.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        CarEntity car = carService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car with id :" + id + "Doesn't exists"));

        carService.delete(car.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

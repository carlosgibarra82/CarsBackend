package com.cardemo.backend.core.cars.services;

import com.cardemo.backend.core.cars.entities.CarEntity;
import com.cardemo.backend.core.cars.services.repositories.ICarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarService {

    @Autowired
    private ICarRepository carRepository;

    public CarEntity create(CarEntity carEntity) {
        this.createValidations(carEntity);

        return carRepository.save(carEntity);
    }

    private void createValidations(CarEntity carEntity) {
        this.idNumberValidation(carEntity.getId());
        this.plateValidation(carEntity.getLicensePlate());
    }

    public CarEntity update(CarEntity newCarEntity) {
        CarEntity carEntity = carRepository.findById(newCarEntity.getId())
                .orElseThrow(() -> new ExpressionException("Car doesn't exists"));

        this.updateValidations(carEntity, newCarEntity);

        carEntity.setId(newCarEntity.getId());
        carEntity.setCarType(newCarEntity.getCarType());
        carEntity.setLicensePlate(newCarEntity.getLicensePlate());
        carEntity.setColor(newCarEntity.getColor());
        carEntity.setUserEntity(newCarEntity.getUserEntity());

        return carRepository.save(carEntity);
    }

    public String delete(Long id){
        try {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT).toString();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR).toString();
        }
    }


    private void updateValidations(CarEntity carEntity, CarEntity newCarEntity) {
        if (!(carEntity.getId() == newCarEntity.getId())) {
            this.idNumberValidation(newCarEntity.getId());
        }

        if (!carEntity.getLicensePlate().equals(newCarEntity.getLicensePlate())) {
            this.plateValidation(newCarEntity.getLicensePlate());
        }
    }

    private void idNumberValidation(Long id) {
        if (carRepository.existsById(id)) {
            throw new ExpressionException("ID already exists");
        }
    }

    private void plateValidation(String plate) {
        if (carRepository.existsByLicensePlate(plate)) {
            throw new ExpressionException("plate already exists");
        }
    }

    public CarEntity getOne(Long id) {
        return carRepository.getOne(id);
    }

    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }

    public Optional<CarEntity> findById(Long id) { return carRepository.findById(id);  }
}

package com.cardemo.backend.core.cars.services.repositories;

import com.cardemo.backend.core.cars.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository  extends JpaRepository<CarEntity, Long> {
    boolean existsByLicensePlate(String plate);
}

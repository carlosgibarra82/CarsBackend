package com.cardemo.backend.core.cars.mappers;

import com.cardemo.backend.core.cars.controllers.dto.CarDto;
import com.cardemo.backend.core.cars.entities.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ICarMapper {
    ICarMapper MAPPER = Mappers.getMapper(ICarMapper.class);

    CarDto carEntityToCarDto(CarEntity carEntity);

    CarEntity createCarDtoToCarEntity(CarDto createCarDTO);

    CarEntity updateCarDtoToCarEntity(CarDto updateCarDTO);

    List<CarDto> carEntitiesToCarDto(List<CarEntity> carEntities);
}

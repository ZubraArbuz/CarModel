package org.example.service.mapper;

import org.example.dto.CarModelDTO;
import org.example.entity.CarModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarModelMapper {
    CarModelMapper INSTANCE = Mappers.getMapper(CarModelMapper.class);

    // Конвертация из Entity в DTO
    CarModelDTO carModelEntityToCarModelDTO(CarModelEntity carModelEntity);

    // Конвертация из DTO в Entity
    CarModelEntity carModelDTOToCarModelEntity(CarModelDTO carModelDTO);
}

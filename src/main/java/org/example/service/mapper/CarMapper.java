package org.example.mapper;

import org.example.dto.CarDTO;
import org.example.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    // Конвертация из Entity в DTO
    CarDTO carEntityToCarDTO(CarEntity carEntity);

    // Конвертация из DTO в Entity
    CarEntity carDTOToCarEntity(CarDTO carDTO);
}

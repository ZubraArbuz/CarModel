package org.example.service.mapper;

import org.example.dto.DealerDTO;
import org.example.entity.DealerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DealerMapper {
    DealerMapper INSTANCE = Mappers.getMapper(DealerMapper.class);

    // Конвертация из Entity в DTO
    DealerDTO dealerEntityToDealerDTO(DealerEntity dealerEntity);

    // Конвертация из DTO в Entity
    DealerEntity dealerDTOToDealerEntity(DealerDTO dealerDTO);
}

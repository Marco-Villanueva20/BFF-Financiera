package com.retotecnico.msproductosfinancieros.persistence.mapper;

import com.retotecnico.model.*;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    @Mapping(target = "id", ignore = true)
    CuentaEntity toEntity(CuentaRequest productoBaseRequest);
    
    
    CuentaResponse toResponse(CuentaEntity productoEntity);

    CuentaEntity toEntityFromResponse(CuentaResponse productoResponse);

}

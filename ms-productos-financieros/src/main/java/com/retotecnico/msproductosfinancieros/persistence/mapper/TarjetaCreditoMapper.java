package com.retotecnico.msproductosfinancieros.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.retotecnico.model.TarjetaCreditoRequest;
import com.retotecnico.model.TarjetaCreditoResponse;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;
import com.retotecnico.msproductosfinancieros.persistence.entity.TarjetaCreditoEntity;

@Mapper(componentModel = "spring")
public interface TarjetaCreditoMapper {

	@Mapping(target = "cuentaId", ignore = true)
	TarjetaCreditoEntity toTarjetaCreditoEntity(TarjetaCreditoRequest request);

	@Mapping(target = "cuentaId", source = "entity.id")
	TarjetaCreditoResponse toTarjetaCreditoResponse(CuentaEntity entity, TarjetaCreditoEntity detalle);

	
}

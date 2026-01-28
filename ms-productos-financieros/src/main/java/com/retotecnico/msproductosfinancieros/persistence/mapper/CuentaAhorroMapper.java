package com.retotecnico.msproductosfinancieros.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.retotecnico.model.CuentaAhorroRequest;
import com.retotecnico.model.CuentaAhorroResponse;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaAhorroEntity;
import com.retotecnico.msproductosfinancieros.persistence.entity.CuentaEntity;

@Mapper(componentModel = "spring")
public interface CuentaAhorroMapper {

	@Mapping(target = "cuentaId", source = "entity.id")
	@Mapping(target = "nombre", source = "entity.nombre")
	@Mapping(target = "saldo", source = "entity.saldo")
	CuentaAhorroResponse toCuentaAhorroResponse(CuentaEntity entity, CuentaAhorroEntity detalle);

	@Mapping(target = "cuentaId", ignore = true)
	CuentaAhorroEntity toCuentaAhorroEntity(CuentaAhorroRequest cuentaAhorroRequest);

}

package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyEntity;

import java.util.UUID;

public final class SupplyMapper {

    private SupplyMapper() {
    }

    public static Supply toDomain(SupplyEntity entity) {
        return new Supply(
          entity.getId().toString(),
          entity.getIdSupplier(),
          entity.getDate()
        );
    }

    public static SupplyEntity toEntity(Supply supply) {
        return new SupplyEntity(
          UUID.fromString(supply.getId()),
          supply.getIdSupplier(),
          supply.getDate()
        );
    }

}

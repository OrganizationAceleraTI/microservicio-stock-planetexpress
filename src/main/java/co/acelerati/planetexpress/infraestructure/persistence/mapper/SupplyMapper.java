package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyEntity;

public final class SupplyMapper {

    private SupplyMapper() {
    }

    public static Supply toDomain(SupplyEntity entity) {
        return new Supply(
          entity.getId(),
          entity.getIdSupplier(),
          entity.getDate()
        );
    }

    public static SupplyEntity toEntity(Supply supply) {
        return new SupplyEntity(
          supply.getId(),
          supply.getIdSupplier(),
          supply.getDate()
        );
    }

}

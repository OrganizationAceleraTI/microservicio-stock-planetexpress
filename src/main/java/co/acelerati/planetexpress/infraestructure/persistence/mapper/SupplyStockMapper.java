package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyStockEntity;

import java.util.UUID;

public final class SupplyStockMapper {

    private SupplyStockMapper() {
    }

    public static SupplyStock toDomain(SupplyStockEntity entity) {
        return new SupplyStock(
          entity.getId().toString(),
          entity.getSupplyId().toString(),
          entity.getStockId(),
          entity.getQuantity(),
          entity.getSupplyPrice()
        );
    }

    public static SupplyStockEntity toEntity(SupplyStock supplyStock) {
        return new SupplyStockEntity(
          UUID.fromString(supplyStock.getId()),
          UUID.fromString(supplyStock.getSupplyId()),
          supplyStock.getStockId(),
          supplyStock.getQuantity(),
          supplyStock.getSupplyPrice()
        );
    }

}

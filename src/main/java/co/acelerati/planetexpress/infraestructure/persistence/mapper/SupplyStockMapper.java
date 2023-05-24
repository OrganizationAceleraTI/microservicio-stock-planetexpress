package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyStockEntity;

public final class SupplyStockMapper {

    private SupplyStockMapper() {
    }

    public static SupplyStock toDomain(SupplyStockEntity entity) {
        return new SupplyStock(
          entity.getId(),
          entity.getSupplyId(),
          entity.getStockId(),
          entity.getQuantity(),
          entity.getSupplyPrice()
        );
    }

    public static SupplyStockEntity toEntity(SupplyStock supplyStock) {
        return new SupplyStockEntity(
          supplyStock.getId(),
          supplyStock.getSupplyId(),
          supplyStock.getStockId(),
          supplyStock.getQuantity(),
          supplyStock.getSupplyPrice()
        );
    }

}

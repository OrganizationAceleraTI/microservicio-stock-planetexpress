package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartStockEntity;

import java.util.Optional;

public final class ShoppingCartStockMapper {

    private ShoppingCartStockMapper() {
    }

    public static ShoppingCartStock toModel(ShoppingCartStockEntity entity) {
        return new ShoppingCartStock(entity.getShoppingCartStockId(),
          entity.getStockId(),
          entity.getShoppingCartId(),
          entity.getQuantity());
    }

    public static ShoppingCartStockEntity toEntity(ShoppingCartStock model) {
        return new ShoppingCartStockEntity(model.getShoppingCartStockId(),
          model.getStockId(),
          model.getShoppingCartId(),
          model.getQuantity());
    }
}

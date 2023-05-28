package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartEntity;

import java.util.Optional;

public final class ShoppingCartMapper {

    private ShoppingCartMapper() {
    }

    public static ShoppingCart toModel(ShoppingCartEntity entity) {
        return new ShoppingCart(entity.getShoppingCartId(),
          entity.getUserId(),
          entity.getLastUpdate());
    }

    public static ShoppingCartEntity toEntity(ShoppingCart model) {
        return new ShoppingCartEntity(model.getShoppingCartId(),
          model.getUserId(),
          model.getLastUpdate());
    }

    public static Optional<ShoppingCart> toOptionalModel(Optional<ShoppingCartEntity> entity) {
        return entity.map(cart ->
          new ShoppingCart(cart.getShoppingCartId(),
            cart.getUserId(),
            cart.getLastUpdate()));
    }
}

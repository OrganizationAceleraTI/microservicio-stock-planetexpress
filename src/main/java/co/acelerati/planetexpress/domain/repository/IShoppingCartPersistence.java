package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartEntity;

import java.util.Optional;

public interface IShoppingCartPersistence {

    Optional<ShoppingCart> createCart(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getCartByUser(Integer userId);
}

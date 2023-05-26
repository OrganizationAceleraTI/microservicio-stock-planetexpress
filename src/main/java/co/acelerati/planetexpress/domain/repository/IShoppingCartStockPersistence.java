package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;

import java.util.Optional;
import java.util.UUID;

public interface IShoppingCartStockPersistence {

    ShoppingCartStock addItemToCart(ShoppingCartStock shoppingCartStock);

    Optional<ShoppingCartStock> findByStockIdAndCart(int stockId, int shoppingCartId);
}

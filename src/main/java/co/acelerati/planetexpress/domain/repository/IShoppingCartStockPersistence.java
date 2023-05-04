package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;

public interface IShoppingCartStockPersistence {

    ShoppingCartStock addItemToCart(ShoppingCartStock shoppingCartStock);
}

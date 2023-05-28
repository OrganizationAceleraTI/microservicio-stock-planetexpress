package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;

public interface IShoppingCartStockHandler {

    void addItemsToCart(Integer userId, ShoppingCartStock cartStock);
}

package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;

public interface IShoppingCartHandler {

    void createShoppingCart(ShoppingCart shoppingCart, int userId);
}

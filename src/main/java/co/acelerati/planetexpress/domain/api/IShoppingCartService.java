package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;

public interface IShoppingCartService {

    void createShoppingCart(ShoppingCart shoppingCart, int userId);
}

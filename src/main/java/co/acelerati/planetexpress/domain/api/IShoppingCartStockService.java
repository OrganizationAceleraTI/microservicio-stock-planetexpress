package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.exception.BadRequestException;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;

import java.util.Optional;

public interface IShoppingCartStockService {

    void addItemToCart(Integer userId, ShoppingCartStock shoppingCartStock) throws BadRequestException;
}

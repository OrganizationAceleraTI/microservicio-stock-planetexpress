package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IShoppingCartStockHandler;
import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ShoppingCartStockHandler implements IShoppingCartStockHandler {

    private final IShoppingCartStockService cartStockService;

    @Override
    public void addItemsToCart(Integer userId, ShoppingCartStock cartStock) {
        cartStockService.addItemToCart(userId, cartStock);
    }
}

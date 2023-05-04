package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IShoppingCartHandler;
import co.acelerati.planetexpress.domain.api.IShoppingCartService;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ShoppingCartHandler implements IShoppingCartHandler {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartHandler.class);

    private final IShoppingCartService shoppingCartService;

    @Override
    public void createShoppingCart(ShoppingCart shoppingCart, int userId) {
        shoppingCartService.createShoppingCart(shoppingCart, userId);
    }
}

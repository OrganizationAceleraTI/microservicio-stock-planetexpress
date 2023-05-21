package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.api.IShoppingCartService;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ShoppingCartHandlerTest {

    private IShoppingCartService shoppingCartService;
    private ShoppingCartHandler shoppingCartHandler;

    @BeforeEach
    void setUp() {
        shoppingCartService = mock(IShoppingCartService.class);
        shoppingCartHandler = new ShoppingCartHandler(shoppingCartService);
    }

    @Test
    @DisplayName("Testing the call to the Shopping Cart service method")
    void whenRequestANewShoppingCart_ThenCallServiceMethod() {
        UUID cartId = UUID.randomUUID();
        ShoppingCart cart = new ShoppingCart(cartId, 1, LocalDateTime.now());
        shoppingCartHandler.createShoppingCart(cart, 1);
        verify(shoppingCartService).createShoppingCart(cart, 1);
    }
}
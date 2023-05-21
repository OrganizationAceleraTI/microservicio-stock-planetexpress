package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ShoppingCartStockHandlerTest {

    private IShoppingCartStockService cartStockService;
    private ShoppingCartStockHandler cartStockHandler;

    @BeforeEach
    void setUp() {
        cartStockService = mock(IShoppingCartStockService.class);
        cartStockHandler = new ShoppingCartStockHandler(cartStockService);
    }

    @Test
    @DisplayName("Testing the call to the Shopping Cart Stock service method")
    void whenAddItemToCart_ThenMakeTheCorrectTransaction() {
        ShoppingCartStock cartStock = new ShoppingCartStock(1, 3);
        cartStockHandler.addItemsToCart(2, cartStock);
        verify(cartStockService).addItemToCart(2, cartStock);
    }
}
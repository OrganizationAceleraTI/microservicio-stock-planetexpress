package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.StockFactory;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockHandlerTest {

    private IStockService stockService;

    private IStockPersistence stockPersistence;

    private StockHandler stockHandler;

    @BeforeEach
    void setUp() {
        stockService = mock(IStockService.class);
        stockPersistence = mock(IStockPersistence.class);
        stockHandler = new StockHandler(stockService);
    }

    @Test
    void whenUpdateAnExistingStock_ThenReturnTheNewStock() {
        Stock stock = new StockFactory().withCurrentPrice(2000).build();
        when(stockService.updateStock(anyInt(), anyInt())).thenReturn(stock);

        Stock response = stockHandler.updateStock(1, new StockFactory().build());

        assertEquals(2000, response.getCurrentPrice());
    }

    @Test
    void whenUpdateAnNonExistingStock_ThenReturnErrorMessage() {
        when(stockPersistence.updateStock(any(Stock.class))).thenReturn(null);
        when(stockService.updateStock(anyInt(), anyInt())).thenThrow(new NoSuchElementException());

        Exception exception = assertThrows(NoSuchElementException.class,
          () -> stockHandler.updateStock(10, new StockFactory().build()));

        assertEquals(exception.getClass(), NoSuchElementException.class);
    }
}
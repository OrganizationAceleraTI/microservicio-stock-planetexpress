package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StockHandlerTest {

    private IStockService stockService;
    private IStockHandler stockHandler;

    @BeforeEach
    void setUp() {
        stockService = mock(IStockService.class);
        stockHandler = new StockHandler(stockService);
    }

    @Test
    void whenPostSupplyStock_thenReturnInsertValueConfirmation(){
        List<Stock> stockList = new ArrayList<>();
        Stock stock = new Stock(1,10,10000);
        stockList.add(stock);

        when(stockService.supplyStock(anyList(), anyInt())).thenReturn(true);

        boolean insertedValue = stockHandler.supplyStock(stockList, 1203);
        assertTrue(insertedValue);
    }
}

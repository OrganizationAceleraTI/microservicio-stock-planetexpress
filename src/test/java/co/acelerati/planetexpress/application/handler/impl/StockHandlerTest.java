package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.DetailStockFactory;
import co.acelerati.planetexpress.domain.model.StockFactory;
import co.acelerati.planetexpress.domain.model.product.BrandFactory;
import co.acelerati.planetexpress.domain.model.product.CategoryFactory;
import co.acelerati.planetexpress.domain.model.product.ProductFactory;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import co.acelerati.planetexpress.domain.model.stock.ProductSale;
import co.acelerati.planetexpress.domain.model.stock.ProductSaleFactory;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockHandlerTest {

    private IStockService stockService;

    private IStockPersistence stockPersistence;

    private IStockHandler stockHandler;

    @BeforeEach
    void setUp() {
        stockService = mock(IStockService.class);
        stockPersistence = mock(IStockPersistence.class);
        stockHandler = new StockHandler(stockService);
    }

    @Test
    void whenPostSupplyStock_thenReturnInsertValueConfirmation() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock = new Stock(1, 10, 10000);
        stockList.add(stock);

        when(stockService.supplyStock(anyList(), anyInt())).thenReturn(true);

        boolean insertedValue = stockHandler.supplyStock(stockList, 1203);
        assertTrue(insertedValue);
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

    @Test
    void whenCallAllProducts_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("page", "0");
        List<DetailStock> detailStockList = new DetailStockFactory().buildList();

        doReturn(detailStockList).when(stockService).getAllProducts(any(), anyList(), anyList(), anyList());

        List<DetailStock> detailStockListResponse = stockHandler.allProducts(filters, new ProductFactory().buildList(),
          new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(detailStockList.get(0).getId(), detailStockListResponse.get(0).getId());
        assertEquals(detailStockList.get(0).getQuantity(), detailStockListResponse.get(0).getQuantity());
        assertEquals(detailStockList.get(0).getModel(), detailStockListResponse.get(0).getModel());
        assertEquals(detailStockList.get(0).getBrand(), detailStockListResponse.get(0).getBrand());
        assertEquals(detailStockList.get(0).getCategory(), detailStockListResponse.get(0).getCategory());

        assertEquals(detailStockList.get(1).getId(), detailStockListResponse.get(1).getId());
        assertEquals(detailStockList.get(1).getQuantity(), detailStockListResponse.get(1).getQuantity());
        assertEquals(detailStockList.get(1).getModel(), detailStockListResponse.get(1).getModel());
        assertEquals(detailStockList.get(1).getBrand(), detailStockListResponse.get(1).getBrand());
        assertEquals(detailStockList.get(1).getCategory(), detailStockListResponse.get(1).getCategory());

        assertEquals(detailStockList.get(2).getId(), detailStockListResponse.get(2).getId());
        assertEquals(detailStockList.get(2).getQuantity(), detailStockListResponse.get(2).getQuantity());
        assertEquals(detailStockList.get(2).getModel(), detailStockListResponse.get(2).getModel());
        assertEquals(detailStockList.get(2).getBrand(), detailStockListResponse.get(2).getBrand());
        assertEquals(detailStockList.get(2).getCategory(), detailStockListResponse.get(2).getCategory());
    }

    @Test
    void whenCallProductsSale_thenReturnAListProductSale() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("page", "0");
        List<ProductSale> productSaleList = new ProductSaleFactory().buildList();

        when(stockService.getProductsSale(any(),anyList(), anyList(), anyList())).thenReturn(productSaleList);

        List<ProductSale> productSaleListResponse = stockHandler.productsSale(filters, new ProductFactory().buildList(),
          new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(productSaleList.get(0).getName(), productSaleListResponse.get(0).getName());
        assertEquals(productSaleList.get(0).getBrandName(), productSaleListResponse.get(0).getBrandName());
        assertEquals(productSaleList.get(0).getCategoryName(), productSaleListResponse.get(0).getCategoryName());
        assertTrue(productSaleListResponse.get(0).getQuantity() > 0);
        assertTrue(productSaleListResponse.get(0).getCurrentPrice() > 0);
        assertEquals(productSaleList.size(), productSaleListResponse.size());
    }

}


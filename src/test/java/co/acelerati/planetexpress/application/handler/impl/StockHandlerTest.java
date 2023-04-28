package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.DetailStockFactory;
import co.acelerati.planetexpress.domain.model.product.BrandFactory;
import co.acelerati.planetexpress.domain.model.product.CategoryFactory;
import co.acelerati.planetexpress.domain.model.product.ProductFactory;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class StockHandlerTest {

    private IStockService stockService;
    private IStockHandler stockHandler;

    @BeforeEach
    void setUp() {
        stockService = mock(IStockService.class);
        stockHandler = new StockHandler(stockService);
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

}

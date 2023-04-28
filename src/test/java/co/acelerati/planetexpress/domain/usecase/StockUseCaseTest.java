package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.model.DetailStockFactory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.BrandFactory;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.CategoryFactory;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.product.ProductFactory;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.StockFactory;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class StockUseCaseTest {

    private IStockPersistence stockPersistence;
    private StockUseCase stockUseCase;

    @BeforeEach
    void setUp() {
        stockPersistence = mock(IStockPersistence.class);
        ISupplyPersistence supplyPersistence = mock(ISupplyPersistence.class);
        ISupplyStockPersistence supplyStockPersistence = mock(ISupplyStockPersistence.class);
        stockUseCase = new StockUseCase(stockPersistence, supplyPersistence, supplyStockPersistence);
    }

    @Test
    void whenGetAllProductsFilterBetweenMinPriceMaxPrice_thenReturnAListStockDetail() {
        when(stockPersistence.getByCurrentPriceBetween(anyDouble(), anyDouble(), anyInt(), anyInt())).thenReturn(new StockFactory().buildList());
        when(stockPersistence.getByProductIdInAndCurrentPriceBetween(anyList(), anyDouble(), anyDouble(), anyInt(), anyInt())).thenReturn(new StockFactory().buildList());
        List<DetailStock> detailStockList = new DetailStockFactory().buildList();

        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("minPrice", "1000000.99");
        filters.add("maxPrice", "2860000.98");
        filters.add("sizePage", "2");
        filters.add("page", "1");

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertTrue("Actual: Current price NOT between " + filters.getFirst("minPrice") + " and " + filters.getFirst("maxPrice")
          , detailStockList.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice")))
            && detailStockList.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

        assertTrue("Response: Current price NOT between " + filters.getFirst("minPrice") + " and " + filters.getFirst("maxPrice")
          , detailStockListResponse.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice")))
            && detailStockListResponse.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

        assertEquals(detailStockList.get(0).getBrand(), detailStockListResponse.get(0).getBrand());
        assertEquals(detailStockList.get(0).getCategory(), detailStockListResponse.get(0).getCategory());

        assertEquals(detailStockList.get(0).getId(), detailStockListResponse.get(0).getId());
        assertEquals(detailStockList.get(0).getQuantity(), detailStockListResponse.get(0).getQuantity());
        assertEquals(detailStockList.get(0).getModel(), detailStockListResponse.get(0).getModel());

    }

    @Test
    void whenGetAllProductsFilterMaxPrice_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("maxPrice", "1870000.98");
        filters.add("sizePage", "2");
        filters.add("page", "1");

        List<DetailStock> detailStockList = new DetailStockFactory().buildList().stream().filter(
            result -> result.getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))))
          .collect(Collectors.toList());

        when(stockPersistence.getByCurrentPriceLessThanEqual(anyDouble(), anyInt(), anyInt())).thenReturn(
          new StockFactory().buildList().stream().filter(
              result -> result.getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))))
            .collect(Collectors.toCollection(ArrayList::new)));

        when(stockPersistence.getByProductIdInAndCurrentPriceLessThanEqual(anyList(), anyDouble(), anyInt(), anyInt())).thenReturn(
          new StockFactory().buildList().stream().filter(
              result -> result.getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))))
            .collect(Collectors.toCollection(ArrayList::new)));

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertTrue("Actual: Current price NOT expect Max price " + filters.getFirst("maxPrice")
          , detailStockList.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

        assertTrue("Response: Current price NOT expect Max price " + filters.getFirst("maxPrice")
          , detailStockListResponse.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

        assertEquals(detailStockList.get(1).getBrand(), detailStockListResponse.get(1).getBrand());
        assertEquals(detailStockList.get(1).getCategory(), detailStockListResponse.get(1).getCategory());
    }

    @Test
    void whenGetAllProductsFilterMinPrice_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("minPrice", "80000.98");
        filters.add("sizePage", "2");
        filters.add("page", "1");

        List<DetailStock> detailStockList = new DetailStockFactory().buildList().stream().filter(
            result -> result.getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))))
          .collect(Collectors.toList());

        when(stockPersistence.getByCurrentPriceGreaterThanEqual(anyDouble(), anyInt(), anyInt())).thenReturn(
          new StockFactory().buildList().stream().filter(
              result -> result.getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))))
            .collect(Collectors.toCollection(ArrayList::new)));

        when(stockPersistence.getByProductIdInAndCurrentPriceGreaterThanEqual(anyList(), anyDouble(), anyInt(), anyInt())).thenReturn(
          new StockFactory().buildList().stream().filter(
              result -> result.getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))))
            .collect(Collectors.toCollection(ArrayList::new)));

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertTrue("Actual: Current price NOT expect min price " + filters.getFirst("minPrice")
          , detailStockList.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))));

        assertTrue("Response: Current price NOT expect min price " + filters.getFirst("maxPrice")
          , detailStockListResponse.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))));

        assertEquals(detailStockList.get(1).getBrand(), detailStockListResponse.get(1).getBrand());
        assertEquals(detailStockList.get(1).getCategory(), detailStockListResponse.get(1).getCategory());
    }

    @Test
    void whenGetAllProductsFilterPrice_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("price", "2859000.99");
        filters.add("sizePage", "2");
        filters.add("page", "1");

        List<DetailStock> detailStockList = new DetailStockFactory().buildList().stream().filter(
            result -> result.getCurrentPrice() == Double.parseDouble(Objects.requireNonNull(filters.getFirst("price"))))
          .collect(Collectors.toList());

        when(stockPersistence.getByCurrentPrice(anyDouble(), anyInt(), anyInt())).thenReturn(
          new StockFactory().buildList().stream().filter(
              result -> result.getCurrentPrice() == Double.parseDouble(Objects.requireNonNull(filters.getFirst("price"))))
            .collect(Collectors.toCollection(ArrayList::new)));

        when(stockPersistence.getByProductIdInAndCurrentPrice(anyList(), anyDouble(), anyInt(), anyInt())).thenReturn(
          new StockFactory().buildList().stream().filter(
              result -> result.getCurrentPrice() == Double.parseDouble(Objects.requireNonNull(filters.getFirst("price"))))
            .collect(Collectors.toCollection(ArrayList::new)));

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(detailStockList.get(0).getCurrentPrice(), detailStockListResponse.get(0).getCurrentPrice());

    }

    @Test
    void whenGetAllProductsDefaultNotFilter_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("sizePage", "2");
        filters.add("page", "0");

        List<DetailStock> detailStockList = new DetailStockFactory().buildList();

        when(stockPersistence.getAll(anyInt(), anyInt())).thenReturn(new StockFactory().buildList());

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(detailStockList.get(0).getBrand(), detailStockListResponse.get(0).getBrand());
        assertEquals(detailStockList.get(0).getCategory(), detailStockListResponse.get(0).getCategory());

        assertEquals(detailStockList.get(0).getId(), detailStockListResponse.get(0).getId());
        assertEquals(detailStockList.get(0).getQuantity(), detailStockListResponse.get(0).getQuantity());
        assertEquals(detailStockList.get(0).getModel(), detailStockListResponse.get(0).getModel());

        assertEquals(detailStockList.get(0).getName(), detailStockListResponse.get(0).getName());
        assertEquals(detailStockList.get(0).getDescription(), detailStockListResponse.get(0).getDescription());
        assertEquals(detailStockList.get(0).getCurrentPrice(), detailStockListResponse.get(0).getCurrentPrice());

    }

}

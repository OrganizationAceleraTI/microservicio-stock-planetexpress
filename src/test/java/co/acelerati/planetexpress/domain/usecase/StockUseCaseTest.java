package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.model.DetailStockFactory;
import co.acelerati.planetexpress.domain.model.product.BrandFactory;
import co.acelerati.planetexpress.domain.model.product.CategoryFactory;
import co.acelerati.planetexpress.domain.model.product.ProductFactory;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import co.acelerati.planetexpress.domain.model.stock.ProductSale;
import co.acelerati.planetexpress.domain.model.stock.ProductSaleFactory;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.StockFactory;
import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockUseCaseTest {

    private IStockPersistence stockPersistence;
    private ISupplyPersistence supplyPersistence;
    private ISupplyStockPersistence supplyStockPersistence;
    private StockUseCase stockUseCase;


    @BeforeEach
    void setUp() {
        stockPersistence = mock(IStockPersistence.class);
        supplyPersistence = mock(ISupplyPersistence.class);
        supplyStockPersistence = mock(ISupplyStockPersistence.class);
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

        assertTrue(detailStockList.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice")))
          && detailStockList.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

        assertTrue(detailStockListResponse.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice")))
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

        assertTrue(detailStockList.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

        assertTrue(detailStockListResponse.get(0).getCurrentPrice() <= Double.parseDouble(Objects.requireNonNull(filters.getFirst("maxPrice"))));

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

        assertTrue(detailStockList.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))));

        assertTrue(detailStockListResponse.get(0).getCurrentPrice() >= Double.parseDouble(Objects.requireNonNull(filters.getFirst("minPrice"))));

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

    @Test
    void whenInsertStock_thenReturnInsertValueConfirmation() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock = new Stock(1, 10, 10000);
        stockList.add(stock);

        when(stockPersistence.insertStock(any(Stock.class))).thenReturn(Optional.of(stock));

        boolean insertedValue = stockUseCase.supplyStock(stockList, 1203);
        assertTrue(insertedValue);
    }

    @Test
    void whenInsertSupplyStock_thenReturnInsertValueConfirmation() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock = new Stock(1, 10, 10000);
        stockList.add(stock);
        SupplyStock supplyStock = new SupplyStock("1", "213", 12, 1500, 10500.5);

        when(supplyStockPersistence.insertSupplyStock(any(SupplyStock.class))).thenReturn(Optional.of(supplyStock));

        boolean insertedValue = stockUseCase.supplyStock(stockList, 1203);
        assertTrue(insertedValue);
    }

    @Test
    void whenInsertSupply_thenReturnInsertValueConfirmation() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock = new Stock(1, 10, 10000);
        stockList.add(stock);
        Supply supply = new Supply(UUID.randomUUID().toString(), 1203, LocalDateTime.now());

        when(supplyPersistence.insertSupply(any(Supply.class))).thenReturn(Optional.of(supply));

        boolean insertedValue = stockUseCase.supplyStock(stockList, 1203);
        assertTrue(insertedValue);
    }

    @Test
    void whenGetProductsSaleWithNameProduct_thenReturnListProductsSale() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("productName", "Iphone 14 Pro Max");
        filters.add("page", "0");
        List<ProductSale> productSaleList = new ProductSaleFactory()
          .buildList()
          .stream()
          .filter(productSale -> productSale.getName().toUpperCase()
            .equals(Objects.requireNonNull(filters.getFirst("productName").toUpperCase())))
          .collect(Collectors.toList());

        when(stockPersistence
          .getByProductIdInAndCurrentPriceGreaterThanAndQuantityGreaterThan(anyList()
            , anyDouble()
            , anyInt()
            , anyInt()
            , anyInt()))
          .thenReturn(new StockFactory().buildList());

        List<ProductSale> productSaleListResponse = stockUseCase.getProductsSale(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(productSaleList.get(0).getId(), productSaleListResponse.get(0).getId());
        assertEquals(productSaleList.get(0).getName(), productSaleListResponse.get(0).getName());
        assertTrue(productSaleList.get(0).getCurrentPrice() > 0);
        assertTrue(productSaleListResponse.get(0).getQuantity() > 0);
        assertEquals(productSaleList.size(), productSaleListResponse.size());

    }

    @Test
    void whenGetProductsSaleWithNameBrand_thenReturnListProductsSale() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("brandName", "Lg");
        filters.add("page", "0");
        List<ProductSale> productSaleList = new ProductSaleFactory()
          .buildList()
          .stream()
          .filter(productSale -> productSale.getBrandName().toUpperCase()
            .equals(Objects.requireNonNull(filters.getFirst("brandName").toUpperCase())))
          .collect(Collectors.toList());

        when(stockPersistence
          .getByProductIdInAndCurrentPriceGreaterThanAndQuantityGreaterThan(anyList()
            , anyDouble()
            , anyInt()
            , anyInt()
            , anyInt()))
          .thenReturn(new StockFactory().buildList());

        List<ProductSale> productSaleListResponse = stockUseCase.getProductsSale(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(productSaleList.get(0).getId(), productSaleListResponse.get(0).getId());
        assertEquals(productSaleList.get(0).getBrandName(), productSaleListResponse.get(0).getBrandName());
        assertTrue(productSaleList.get(0).getCurrentPrice() > 0);
        assertTrue(productSaleListResponse.get(0).getQuantity() > 0);
        assertEquals(productSaleList.size(), productSaleListResponse.size());

    }

    @Test
    void whenGetProductsSaleWithNameCategory_thenReturnListProductsSale() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("categoryName", "Accesorios");
        filters.add("page", "0");
        List<ProductSale> productSaleList = new ProductSaleFactory()
          .buildList()
          .stream()
          .filter(productSale -> productSale.getCategoryName().toUpperCase()
            .equals(Objects.requireNonNull(filters.getFirst("categoryName").toUpperCase())))
          .collect(Collectors.toList());

        when(stockPersistence
          .getByProductIdInAndCurrentPriceGreaterThanAndQuantityGreaterThan(anyList()
            , anyDouble()
            , anyInt()
            , anyInt()
            , anyInt()))
          .thenReturn(new StockFactory().buildList());

        List<ProductSale> productSaleListResponse = stockUseCase.getProductsSale(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(productSaleList.get(0).getId(), productSaleListResponse.get(0).getId());
        assertEquals(productSaleList.get(0).getCategoryName(), productSaleListResponse.get(0).getCategoryName());
        assertTrue(productSaleList.get(0).getCurrentPrice() > 0);
        assertTrue(productSaleListResponse.get(0).getQuantity() > 0);
        assertEquals(productSaleList.size(), productSaleListResponse.size());

    }

    @Test
    void whenGetProductsSaleWithOutFilters_thenReturnListProductsSale() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("page", "0");
        List<ProductSale> productSaleList = new ProductSaleFactory().buildList();

        when(stockPersistence
          .getByProductIdInAndCurrentPriceGreaterThanAndQuantityGreaterThan(anyList()
            , anyDouble()
            , anyInt()
            , anyInt()
            , anyInt()))
          .thenReturn(new StockFactory().buildList());

        List<ProductSale> productSaleListResponse = stockUseCase.getProductsSale(filters, new ProductFactory().buildList()
          , new CategoryFactory().buildList(), new BrandFactory().buildList());

        assertEquals(productSaleList.get(0).getId(), productSaleListResponse.get(0).getId());
        assertEquals(productSaleList.get(0).getCategoryName(), productSaleListResponse.get(0).getCategoryName());
        assertTrue(productSaleList.get(0).getCurrentPrice() > 0);
        assertTrue(productSaleListResponse.get(0).getQuantity() > 0);
        assertEquals(productSaleList.size(), productSaleListResponse.size());

    }

}

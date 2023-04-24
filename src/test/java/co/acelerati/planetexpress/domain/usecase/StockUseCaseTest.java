package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.DetailStockFactory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.BrandFactory;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.CategoryFactory;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.product.ProductFactory;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.StockFactory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import co.acelerati.planetexpress.domain.usecase.StockUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class StockUseCaseTest {

    private static final double ZERO = 0;

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
        when(stockPersistence.getByCurrentPriceBetween(anyDouble(), anyDouble(), anyInt())).thenReturn(getStocksTest());

        List<DetailStock> detailStockList = getDetailStocksTest();

        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("minPrice","1000000.99");
        filters.add("maxPrice","2860000.98");
        filters.add("page","1");

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, getProductsTest()
          , getCategoriesTest(), getBrandsTest());

        assertTrue("Actual: Current price NOT between" + filters.getFirst("minPrice") + "and" + filters.getFirst("maxPrice")
          ,detailStockList.get(0).getCurrentPrice() >= Double.parseDouble(filters.getFirst("minPrice"))
            && detailStockList.get(0).getCurrentPrice() <= Double.parseDouble(filters.getFirst("maxPrice")));

        assertTrue("Response: Current price NOT between" + filters.getFirst("minPrice") + "and" + filters.getFirst("maxPrice")
          ,detailStockListResponse.get(0).getCurrentPrice() >= Double.parseDouble(filters.getFirst("minPrice"))
          && detailStockListResponse.get(0).getCurrentPrice() <= Double.parseDouble(filters.getFirst("maxPrice")));

        assertEquals(detailStockList.get(1).getBrand(), detailStockListResponse.get(1).getBrand());
        assertEquals(detailStockList.get(1).getCategory(), detailStockListResponse.get(1).getCategory());

        assertEquals(detailStockList.get(2).getId(), detailStockListResponse.get(2).getId());
        assertEquals(detailStockList.get(2).getQuantity(), detailStockListResponse.get(2).getQuantity());
        assertEquals(detailStockList.get(2).getModel(), detailStockListResponse.get(2).getModel());

    }

    @Test
    void whenGetAllProductsFilterMaxPrice_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("maxPrice","1870000.98");
        filters.add("page","1");

        List<DetailStock> detailStockList = getDetailStocksTest().stream().filter(
            result -> result.getCurrentPrice() <= Double.parseDouble(filters.getFirst("maxPrice")))
          .collect(Collectors.toList());

        when(stockPersistence.getByCurrentPriceGreaterThanEqual(anyDouble(), anyInt())).thenReturn(
          getStocksTest().stream().filter(
              result -> result.getCurrentPrice() <= Double.parseDouble(filters.getFirst("maxPrice")))
            .collect(Collectors.toCollection(ArrayList::new)));

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, getProductsTest()
          , getCategoriesTest(), getBrandsTest());

        assertTrue("Actual: Current price NOT expect Max price " + filters.getFirst("maxPrice")
          , detailStockList.get(0).getCurrentPrice() <= Double.parseDouble(filters.getFirst("maxPrice")));

        assertTrue("Response: Current price NOT expect Max price " + filters.getFirst("maxPrice")
          ,detailStockListResponse.get(0).getCurrentPrice() <= Double.parseDouble(filters.getFirst("maxPrice")));

        assertEquals(detailStockList.get(1).getBrand(), detailStockListResponse.get(1).getBrand());
        assertEquals(detailStockList.get(1).getCategory(), detailStockListResponse.get(1).getCategory());
    }

    @Test
    void whenGetAllProductsFilterMinPrice_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("minPrice","80000.98");
        filters.add("page","1");

        List<DetailStock> detailStockList = getDetailStocksTest().stream().filter(
            result -> result.getCurrentPrice() >= Double.parseDouble(filters.getFirst("minPrice")))
          .collect(Collectors.toList());

        when(stockPersistence.getByCurrentPriceLessThanEqual(anyDouble(), anyInt())).thenReturn(
          getStocksTest().stream().filter(
              result -> result.getCurrentPrice() >= Double.parseDouble(filters.getFirst("minPrice")))
            .collect(Collectors.toCollection(ArrayList::new)));

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, getProductsTest()
          , getCategoriesTest(), getBrandsTest());

        assertTrue("Actual: Current price NOT expect min price " + filters.getFirst("minPrice")
          , detailStockList.get(0).getCurrentPrice() >= Double.parseDouble(filters.getFirst("minPrice")));

        assertTrue("Response: Current price NOT expect min price " + filters.getFirst("maxPrice")
          ,detailStockListResponse.get(0).getCurrentPrice() >= Double.parseDouble(filters.getFirst("minPrice")));

        assertEquals(detailStockList.get(1).getBrand(), detailStockListResponse.get(1).getBrand());
        assertEquals(detailStockList.get(1).getCategory(), detailStockListResponse.get(1).getCategory());
    }

    @Test
    void whenGetAllProductsDefaultNotFilter_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("page","1");

        List<DetailStock> detailStockList = getDetailStocksTest();

        when(stockPersistence.getAll(anyInt())).thenReturn(getStocksTest());

        List<DetailStock> detailStockListResponse = stockUseCase.getAllProducts(filters, getProductsTest()
          , getCategoriesTest(), getBrandsTest());

        assertEquals(detailStockList.get(0).getBrand(), detailStockListResponse.get(0).getBrand());
        assertEquals(detailStockList.get(0).getCategory(), detailStockListResponse.get(0).getCategory());

        assertEquals(detailStockList.get(1).getId(), detailStockListResponse.get(1).getId());
        assertEquals(detailStockList.get(1).getQuantity(), detailStockListResponse.get(1).getQuantity());
        assertEquals(detailStockList.get(1).getModel(), detailStockListResponse.get(1).getModel());

        assertEquals(detailStockList.get(2).getName(), detailStockListResponse.get(2).getName());
        assertEquals(detailStockList.get(2).getDescription(), detailStockListResponse.get(2).getDescription());
        assertEquals(detailStockList.get(2).getCurrentPrice(), detailStockListResponse.get(2).getCurrentPrice());

    }

    private static List<Product> getProductsTest(){
        List<Product> products = new ArrayList<>();
        products.add(new ProductFactory().withAllArguments(
          Long.parseLong("1")
          , "Iphone 14 Pro Max"
          ,"Teléfono célular apple"
          ,"PHOMX198974"
          ,Long.parseLong("10")
          ,Long.parseLong("20")).build());

        products.add(new ProductFactory().withAllArguments(
          Long.parseLong("2")
          , "Lavadora LG Carga Superior 19kg Negro"
          ,"Lavadora LG Smart Inverter con 10 años de garantía en su motor, tiene un rendimiento de lavado " +
            "superior, más limpio, más higiénico, El Motor Smart Inverter es confiable, silencioso y duradero"
          ,"WT19BSB"
          ,Long.parseLong("11")
          ,Long.parseLong("21")).build());
        List<Category> categories = new ArrayList<>();

        products.add(new ProductFactory().withAllArguments(
          Long.parseLong("3")
          , "Cargador Pared SAMSUNG USB-C 25W Carga Rápida"
          ,"Samsung llego con el nuevo cargador rápido de pared que permite cargar todos tus dispositivos a " +
            "una velocidad increible para que puedas disfrutar de todo tu contenido con la mayor energia."
          ,"CP013681"
          ,Long.parseLong("12")
          ,Long.parseLong("22")).build());

        return products;
    }

    private static List<Stock> getStocksTest(){
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new StockFactory().withAllArguments(
          1
          ,52
          ,2859000.99).build());
        stocks.add(new StockFactory().withAllArguments(
          2
          ,28
          ,1087000.99).build());
        stocks.add(new StockFactory().withAllArguments(
          3
          ,251
          ,69900.99).build());
        return stocks;
    }

    private static List<Category> getCategoriesTest(){
        List<Category> categories = new ArrayList<>();
        categories.add(new CategoryFactory().withAllArguments(
          Long.parseLong("20")
          ,"Electronica").build());
        categories.add(new CategoryFactory().withAllArguments(
          Long.parseLong("21")
          ,"Electrodomesticos").build());
        categories.add(new CategoryFactory().withAllArguments(
          Long.parseLong("22")
          ,"Accesorios").build());
        return categories;
    }

    private static List<Brand> getBrandsTest(){
        List<Brand> brands = new ArrayList<>();
        brands.add(new BrandFactory().withAllArguments(
          Long.parseLong("10")
          ,"Apple").build());
        brands.add(new BrandFactory().withAllArguments(
          Long.parseLong("11")
          ,"Lg").build());
        brands.add(new BrandFactory().withAllArguments(
          Long.parseLong("12")
          ,"Samsung").build());
        return brands;
    }

    private static List<DetailStock> getDetailStocksTest(){
        List<DetailStock> detailStockList = new ArrayList<>();
        detailStockList.add(new DetailStockFactory().withAllArguments(
          Long.parseLong("1")
          ,"Iphone 14 Pro Max"
          ,"Teléfono célular apple"
          , "PHOMX198974"
          ,"Apple"
          ,"Electronica"
          ,52
          ,2859000.99).build());
        detailStockList.add(new DetailStockFactory().withAllArguments(
          Long.parseLong("2")
          ,"Lavadora LG Carga Superior 19kg Negro"
          ,"Lavadora LG Smart Inverter con 10 años de garantía en su motor, tiene un rendimiento de lavado " +
            "superior, más limpio, más higiénico, El Motor Smart Inverter es confiable, silencioso y duradero"
          , "WT19BSB"
          ,"Lg"
          ,"Electrodomesticos"
          ,28
          ,1087000.99).build());
        detailStockList.add(new DetailStockFactory().withAllArguments(
          Long.parseLong("3")
          ,"Cargador Pared SAMSUNG USB-C 25W Carga Rápida"
          ,"Samsung llego con el nuevo cargador rápido de pared que permite cargar todos tus dispositivos a " +
            "una velocidad increible para que puedas disfrutar de todo tu contenido con la mayor energia."
          , "CP013681"
          ,"Samsung"
          ,"Accesorios"
          ,251
          ,69900.99).build());

        return detailStockList;
    }

}

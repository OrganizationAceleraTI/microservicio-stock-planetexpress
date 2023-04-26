package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.DetailStockFactory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.BrandFactory;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.CategoryFactory;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.product.ProductFactory;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
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
    void whenCallAllProducts_thenReturnAListStockDetail() {
        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        filters.add("page","0");
        List<DetailStock> detailStockList = getDetailStocksTest();

        doReturn(detailStockList).when(stockService).getAllProducts(any(), anyList(), anyList(), anyList());

        List<DetailStock> detailStockListResponse = stockHandler.allProducts(filters, getProductsTest(),
          getCategoriesTest(), getBrandsTest());

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

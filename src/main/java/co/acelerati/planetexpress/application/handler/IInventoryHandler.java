package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.product.Product;
import org.springframework.util.MultiValueMap;

import java.util.List;


public interface IInventoryHandler {

    List<Inventory> getAllInventory(int page);
    List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands);
    List<Inventory> getInventoryByPrice(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, int page);
    Inventory updateStock(Integer stockId, Inventory updateStock);
    void inventorySupply(List<Inventory> inventoryList);
}

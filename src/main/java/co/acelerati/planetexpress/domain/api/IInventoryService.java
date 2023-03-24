package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Brand;
import co.acelerati.planetexpress.domain.model.Category;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Product;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface IInventoryService {

    void inventorySupply(List<Inventory> inventoryList);

    List<Inventory> getInventoryByPrice(int currentPrice, int page);
    List<Inventory> getByCurrentPriceLessThanEqual(int currentPrice, int page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(int currentPrice, int page);
    List<Inventory> getByCurrentPriceBetween(int minPrice, int maxPrice, int page);
    List<Inventory> getAllInventory(int page);

    List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands);
}

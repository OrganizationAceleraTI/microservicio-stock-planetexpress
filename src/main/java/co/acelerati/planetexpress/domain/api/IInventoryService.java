package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Deprecated
public interface IInventoryService {

    Inventory updateStock(Integer newSalePrice, Integer stockId);
    Optional<Inventory> getStockById(Integer stockId);
    void inventorySupply(List<Inventory> inventoryList);

    List<Inventory> getInventoryByPrice(int currentPrice, int page);
    List<Inventory> getByCurrentPriceLessThanEqual(int currentPrice, int page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(int currentPrice, int page);
    List<Inventory> getByCurrentPriceBetween(int minPrice, int maxPrice, int page);
    List<Inventory> getAllInventory(int page);

    List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands);
}

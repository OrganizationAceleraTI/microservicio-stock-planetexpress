package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.Brand;
import co.acelerati.planetexpress.domain.model.Category;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Product;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import org.springframework.util.MultiValueMap;
import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory(int page);
    List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands);
    List<Inventory> getInventoryByPrice(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, int page);
    void inventorySupply(List<Inventory> inventoryList);
}

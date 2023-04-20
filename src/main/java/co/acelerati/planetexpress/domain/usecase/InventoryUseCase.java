package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class InventoryUseCase implements IInventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryUseCase.class);

    private final IInventoryPersistence inventoryPersistence;

    public InventoryUseCase(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public Inventory updateStock(Integer newSalePrice, Integer stockId) {
        return inventoryPersistence.updateStock(this.getStockById(stockId).map(inventory ->
            inventory.withCurrentPrice(newSalePrice)).orElseThrow());
    }

    @Override
    public Optional<Inventory> getStockById(Integer stockId) {
        return inventoryPersistence.getStockById(stockId);
    }
    
    public void inventorySupply(List<Inventory> inventoryList) {
        inventoryList.forEach(inventory -> {
            Inventory inventoryExist = inventoryPersistence.getInventoryOfSupplier(inventory.getPersonSupplierId(),
              inventory.getProductId());
            if (inventoryExist != null) {
                Inventory inventoryUpdate = new Inventory(inventoryExist.getInventoryId(),
                  inventoryExist.getProductId(),
                  inventoryExist.getPersonSupplierId(),
                  inventory.getIncomingPrice(),
                  inventory.getCurrentPrice(),
                  Integer.sum(inventoryExist.getQuantity(), inventory.getQuantity()));
                inventoryPersistence.updateInventory(inventoryUpdate);
            } else {
                Inventory inventorySave = new Inventory(inventory.getProductId(),
                  inventory.getPersonSupplierId(),
                  inventory.getQuantity());
                inventoryPersistence.saveInventory(inventorySave);
            }
        });
    }

    @Override
    public List<Inventory> getInventoryByPrice(int currentPrice, int page) {
        return inventoryPersistence.getByCurrentPrice(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceLessThanEqual(int currentPrice, int page) {
        return inventoryPersistence.getByCurrentPriceLessThanEqual(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceGreaterThanEqual(int currentPrice, int page) {
        return inventoryPersistence.getByCurrentPriceGreaterThanEqual(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceBetween(int minPrice, int maxPrice, int page) {
        return inventoryPersistence.getByCurrentPriceBetween(minPrice, maxPrice, page);
    }

    @Override
    public List<Inventory> getAllInventory(int page) {
        return inventoryPersistence.getAllInventory(page);
    }

    @Override
    public List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands) {
        List<DetailStock> detailStocks = new ArrayList<>();
        List<Inventory> inventories = new ArrayList<>();
        int minPrice = filters.containsKey("minPrice") ? Integer.parseInt(String.valueOf(filters.getFirst("minPrice"))) : -1;
        int maxPrice = filters.containsKey("maxPrice") ? Integer.parseInt(filters.getFirst("maxPrice")) : -1;
        int page = Integer.parseInt(filters.getFirst("page"));
       if (maxPrice >= 0) {
            inventories = inventoryPersistence.getByCurrentPriceBetween(minPrice, maxPrice, page);
        }
        else if (minPrice >= 0) {
            inventories = inventoryPersistence.getByCurrentPriceLessThanEqual(minPrice, page);
        }
        else if (minPrice >= 0 && maxPrice >= 0) {
            inventories = inventoryPersistence.getByCurrentPriceBetween(minPrice, maxPrice, page);
        } else {
            inventories = inventoryPersistence.getAllInventory(page);
        }
        for (Inventory inventory : inventories) {
            Product product = products.stream().filter(prd -> inventory.getProductId().equals(prd.getId())).findFirst().get();
            Category category = categories.stream().filter(cat -> product.getIdCategory().equals(cat.getId())).findFirst().get();
            Brand brand = brands.stream().filter(bra -> product.getIdBrand().equals(bra.getId())).findFirst().get();
            DetailStock detailStock = new DetailStock(
              product.getId(),
              product.getName(),
              product.getDescription(),
              product.getModel(),
              brand.getName(),
              category.getName(),
              inventory.getQuantity(),
              inventory.getCurrentPrice()
            );
            detailStocks.add(detailStock);
        }
        return detailStocks;
    }
}

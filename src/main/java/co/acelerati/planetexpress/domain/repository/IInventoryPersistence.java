package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;
import java.util.Optional;

@Deprecated
public interface IInventoryPersistence {

    Inventory updateStock(Inventory inventory);
    Optional<Inventory> getStockById(Integer stockId);
    Integer saveInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productID);
    List<Inventory> getAllInventory(Integer page);
    List<Inventory> getByCurrentPrice(Integer currentPrice, Integer page);
    List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, Integer page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, Integer page);
    List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, Integer page);

}

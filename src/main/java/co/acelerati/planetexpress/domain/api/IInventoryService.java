package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {

    Inventory updateStock(Integer newSalePrice, Integer stockId);
    Optional<Inventory> getStockById(Integer stockId);
    void inventorySupply(List<Inventory> inventoryList);


}

package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {

    Inventory updateStock(Inventory newStock);
    Inventory getStockById(Integer stockId);
    void inventorySupply(List<Inventory> inventoryList);


}

package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.Inventory;
import java.util.List;


public interface IInventoryHandler {

    Inventory updateStock(Integer stockId, Inventory updateStock);
    void inventorySupply(List<Inventory> inventoryList);
}

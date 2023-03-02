package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Provider;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAllInventory();

    Provider inventorySupply(List<Inventory> inventoryList);


}

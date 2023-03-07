package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Provider;

import java.util.List;

public interface IInventoryService {

    Provider inventorySupply(List<Inventory> inventoryList);

    List<Inventory> getInventoryByPriceIsNull(int currentPrice, int page);

    List<Inventory> getByQuantityIsNull(int quantity, int page);


}

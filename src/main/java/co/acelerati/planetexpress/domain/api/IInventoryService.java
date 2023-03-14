package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;

public interface IInventoryService {

    void inventorySupply(List<Inventory> inventoryList);

    List<Inventory> getInventoryByPrice(int currentPrice, int page);

    List<Inventory> getByQuantityIsNull(int quantity, int page);


}

package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAllInventory();

    void inventorySupply(List<Inventory> inventoryList);

    List<Inventory> getInventoryByPriceIsNull(int currentPrice, int page);

    List<Inventory> getByQuantityIsNull(int quantity, int page);


}

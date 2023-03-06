package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Provider;
import co.acelerati.planetexpress.domain.model.User;

import java.util.List;

public interface IInventoryService {

    Provider inventorySupply(List<Inventory> inventoryList);


}

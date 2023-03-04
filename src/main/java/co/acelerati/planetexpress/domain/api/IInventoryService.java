package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.User;

import java.util.List;

public interface IInventoryService {

    User inventorySupply(List<Inventory> inventoryList);


}

package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getAllInventory();
}
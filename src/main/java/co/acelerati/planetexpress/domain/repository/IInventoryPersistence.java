package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;

public interface IInventoryPersistence {

    List<Inventory> getAllInventory();
}

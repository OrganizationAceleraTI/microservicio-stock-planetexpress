package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;

public interface IInventoryPersistence {

    List<Inventory> getAllInventory();

    Integer saveInventory(Inventory inventory);

    void updateInventory(Inventory inventory);

    Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productID);
}

package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import java.util.Optional;

public interface IInventoryPersistence {

    Inventory updateStock(Inventory inventory);
    Optional<Inventory> getStockById(Integer stockId);
    Integer saveInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productID);
}

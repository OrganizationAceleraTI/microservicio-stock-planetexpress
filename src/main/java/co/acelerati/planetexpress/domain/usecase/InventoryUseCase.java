package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public class InventoryUseCase implements IInventoryService {

    private final IInventoryPersistence inventoryPersistence;

    public InventoryUseCase(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public void inventorySupply(List<Inventory> inventoryList) {
        inventoryList.forEach(inventory -> {
            Inventory inventoryExist = inventoryPersistence.getInventoryOfSupplier(inventory.getPersonSupplierId(),
                                        inventory.getProductId());
            if(inventoryExist != null){
                Inventory inventoryUpdate = new Inventory(inventoryExist.getInventoryId(),
                  inventoryExist.getProductId(),
                  inventoryExist.getPersonSupplierId(),
                  inventory.getIncomingPrice(),
                  inventory.getCurrentPrice(),
                  Integer.sum(inventoryExist.getQuantity(),inventory.getQuantity()));
                inventoryPersistence.updateInventory(inventoryUpdate);
            }else{
                Inventory inventorySave = new Inventory(inventory.getProductId(),
                  inventory.getPersonSupplierId(),
                  inventory.getQuantity());
                inventoryPersistence.saveInventory(inventorySave);
            }
        });
    }

    @Override
    public List<Inventory> getInventoryByPrice(int currentPrice, int page) {
        return inventoryPersistence.getByCurrentPrice(currentPrice, page);
    }

    @Override
    public List<Inventory> getByQuantityIsNull(int quantity, int page) {
        return null;
    }
}

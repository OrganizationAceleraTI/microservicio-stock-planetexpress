package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Provider;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;

import java.util.List;

public class InventoryUseCase implements IInventoryService {

    private final IInventoryPersistence inventoryPersistence;

    public InventoryUseCase(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public Provider inventorySupply(List<Inventory> inventoryList) {
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

        return new Provider("Samuel", "Barrera", inventoryList.get(0).getPersonSupplierId());
    }


}

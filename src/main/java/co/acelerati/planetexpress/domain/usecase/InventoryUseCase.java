package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.User;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;

import java.util.List;

public class InventoryUseCase implements IInventoryService {

    private final IInventoryPersistence inventoryPersistence;

    public InventoryUseCase(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public User inventorySupply(List<Inventory> inventoryList) {
        inventoryList.forEach(inventory -> {
            Inventory inventoryExist = inventoryPersistence.getInventoryOfSupplier(inventory.getPersonSupplierId(), inventory.getProductId());
            if(inventoryExist != null){
                inventoryExist.setQuantity(Integer.sum(inventoryExist.getQuantity(),inventory.getQuantity()));
                inventoryExist.setCurrentPrice(inventory.getCurrentPrice());
                inventoryPersistence.updateInventory(inventoryExist);
            }else{
                inventory.setCurrentPrice(0);
                inventoryPersistence.saveInventory(inventory);
            }
        });

        return new User("Samuel", "Barrere","mail@domain.com","32145678","calle falsa 123","CC","34233456","10");
    }


}

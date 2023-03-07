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
    public List<Inventory> getAllInventory() {
        return inventoryPersistence.getAllInventory();
    }

    @Override
    public void inventorySupply(List<Inventory> inventoryList) {

        inventoryList.stream().forEach( inventory -> {
            Inventory inventoryExist = inventoryPersistence.getInventoryOfSupplier(inventory.getPersonSupplierId(), inventory.getProductId());
            if(inventoryExist != null){
                inventoryExist.setQuantity(Integer.sum(inventoryExist.getQuantity(),inventory.getQuantity()));
                inventoryPersistence.updateInventory(inventoryExist);
            }else{
                inventory.setCurrentPrice(0);
                inventoryPersistence.saveInventory(inventory);
            }
        });

    }

    @Override
    public List<Inventory> getInventoryByPriceIsNull(int currentPrice, int page) {
        return null;
    }

    @Override
    public List<Inventory> getByQuantityIsNull(int quantity, int page) {
        return null;
    }
}

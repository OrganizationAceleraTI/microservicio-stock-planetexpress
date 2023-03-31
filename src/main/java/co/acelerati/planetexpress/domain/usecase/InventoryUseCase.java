package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class InventoryUseCase implements IInventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryUseCase.class);

    private final IInventoryPersistence inventoryPersistence;

    public InventoryUseCase(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public Inventory updateStock(Integer newSalePrice, Integer stockId) {
        return inventoryPersistence.updateStock(this.getStockById(stockId).map(inventory ->
            inventory.withCurrentPrice(newSalePrice)).orElseThrow());
    }

    @Override
    public Optional<Inventory> getStockById(Integer stockId) {
        return inventoryPersistence.getStockById(stockId);
    }
    
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


}

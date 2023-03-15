package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InventoryJpaAdapter implements IInventoryPersistence {

    private final IInventoryRepository inventoryRepository;

    @Override
    public void updateStock(Inventory inventory) {
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setInventoryId(inventory.getInventoryId());
        inventoryEntity.setProductId(inventory.getProductId());
        inventoryEntity.setIncomingPrice(inventory.getIncomingPrice());
        inventoryEntity.setCurrentPrice(inventory.getCurrentPrice());
        inventoryRepository.save(inventoryEntity);
    }

}

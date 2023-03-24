package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.application.mapper.InventoryUpdateMapper;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class InventoryJpaAdapter implements IInventoryPersistence {

    private final IInventoryRepository inventoryRepository;

    private final IInventoryEntityMapper inventoryEntityMapper;

    @Override
    public Inventory updateStock(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryUpdateMapper.modelToEntity(inventory);
        return InventoryUpdateMapper.entityToModel(inventoryRepository.save(inventoryEntity));
    }

    @Override
    public Optional<Inventory> getStockById(Integer stockId) {
        return InventoryUpdateMapper.entityToModelOptional(inventoryRepository.findById(stockId));
    }

    @Override
    public Integer saveInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = inventoryEntityMapper.toInventoryEntity(inventory);
        return inventoryRepository.save(inventoryEntity).getInventoryId();
    }

    @Override
    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventoryEntityMapper.toInventoryEntity(inventory));
    }

    @Override
    public Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productId) {
        InventoryEntity inventoryEntity = inventoryRepository.findByPersonSupplierIdAndProductId(personSupplierId, productId);
        return inventoryEntityMapper.toInventoryModel(inventoryEntity);
    }


}

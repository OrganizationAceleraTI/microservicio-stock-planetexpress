package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.application.mapper.InventoryUpdateMapper;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class InventoryJpaAdapter implements IInventoryPersistence {

    private final IInventoryRepository inventoryRepository;

    @Override
    public Inventory updateStock(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryUpdateMapper.modelToEntity(inventory);
        return InventoryUpdateMapper.entityToModel(inventoryRepository.save(inventoryEntity));
    }

    @Override
    public Optional<Inventory> getStockById(Integer stockId) {
        return InventoryUpdateMapper.entityToModelOptional(inventoryRepository.findById(stockId));
    }

}

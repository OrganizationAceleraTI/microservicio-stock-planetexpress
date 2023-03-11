package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class InventoryJpaAdapter implements IInventoryPersistence {

    private final IInventoryRepository inventoryRepository;

    private final IInventoryEntityMapper inventoryEntityMapper;

    private final int SIZE_PAGE = 25;

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

    @Override
    public List<Inventory> getByCurrentPrice(Integer currentPrice, Integer page) {
        return inventoryRepository.findByCurrentPrice(currentPrice, PageRequest.of(page, SIZE_PAGE))
                .map(pages -> pages.map(inventoryEntityMapper::toInventoryModel)).get().toList();
    }

    @Override
    public List<Inventory> getByQuantityIsnull(Integer quantity, Integer page) {
        return inventoryRepository.findByQuantityIsNull(quantity, PageRequest.of(page, SIZE_PAGE))
                .map(pages -> pages.map(inventoryEntityMapper::toInventoryModel)).get().toList();
    }


}

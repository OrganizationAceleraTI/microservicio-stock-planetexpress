package co.acelerati.planetexpress.infraestructure.output.adapter;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import co.acelerati.planetexpress.infraestructure.output.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.output.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InventoryJpaAdapter implements IInventoryPersistence {

    private final IInventoryRepository inventoryRepository;
    private final IInventoryEntityMapper inventoryEntityMapper;

    private final int SIZE_PAGE = 25;

    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        if (inventoryEntityList.isEmpty()) throw new NoResultException();
            inventoryEntityList.forEach(inventory -> {
                Inventory inventoryModel = new Inventory();
                inventoryModel.setInventoryId(inventory.getInventoryId());
                inventoryModel.setProductId(inventory.getProductId());
                //inventoryModel.setWarehouseId(inventory.getWarehouseId());
                inventoryModel.setQuantity(inventory.getQuantity());
                inventoryList.add(inventoryModel);
        });
        return inventoryList;
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

    @Override
    public List<Inventory> getByCurrentPriceIsNull(Integer currentPrice, int page) {
        return inventoryRepository.findByQuantityIsNull(currentPrice, PageRequest.of(page, SIZE_PAGE))
                .map(pages -> pages.map(inventoryEntityMapper::toInventoryModel)).get().toList();
    }

    /*@Override
    public Inventory getByQuantityIsNull(Integer quantity, int page) throws Exception {
        return inventoryRepository.findByQuantityIsNull(quantity, PageRequest.of(page, SIZE_PAGE))
                .map((Page<InventoryEntity> listInventory) -> inventoryEntityMapper.toInventoryList(listInventory)).orElseThrow();
    }*/


}

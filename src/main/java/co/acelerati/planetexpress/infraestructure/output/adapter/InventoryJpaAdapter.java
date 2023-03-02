package co.acelerati.planetexpress.infraestructure.output.adapter;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import co.acelerati.planetexpress.infraestructure.output.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.output.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InventoryJpaAdapter implements IInventoryPersistence {

    private final IInventoryRepository inventoryRepository;

    private final IInventoryEntityMapper inventoryEntityMapper;

    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        List<InventoryEntity> inventoryEntityList = inventoryRepository.findAll();
        if (inventoryEntityList.isEmpty()) throw new NoResultException();
        inventoryEntityList.forEach(inventory -> {
            Inventory inventoryModel = new Inventory();
            inventoryModel.setInventoryId(inventory.getInventoryId());
            inventoryModel.setProductId(inventory.getProductId());
            inventoryModel.setPersonSupplierId(inventory.getPersonSupplierId());
            inventoryModel.setCurrentPrice(inventory.getCurrentPrice());
            inventoryModel.setIncomingPrice(inventory.getIncomingPrice());
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


}

package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.application.mapper.InventoryUpdateMapper;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;

import java.util.Optional;

public class InventoryUseCase implements IInventoryService {

    private final IInventoryPersistence inventoryPersistence;

    public InventoryUseCase(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public Inventory updateStock(Inventory newStock) {
        Inventory currentStock = this.getStockById(newStock.getInventoryId());
        currentStock.setCurrentPrice(newStock.getCurrentPrice());
        return inventoryPersistence.updateStock(currentStock);
    }

    @Override
    public Inventory getStockById(Integer stockId) {
        return InventoryUpdateMapper.optionalToModel(inventoryPersistence.getStockById(stockId));
    }
}

package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InventoryHandler implements IInventoryHandler {

    private final IInventoryService inventoryService;

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @Override
    public void updateStock(UpdateStockRequestDTO updateStockRequest) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(updateStockRequest.getInventoryId());
        inventory.setProductId(updateStockRequest.getProductId());
        inventory.setIncomingPrice(updateStockRequest.getIncomingPrice());
        inventory.setCurrentPrice(updateStockRequest.getSalePrice());
        inventoryService.updateStock(inventory);
    }
}

package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryHandler implements IInventoryHandler {

    private static final Logger logger = LoggerFactory.getLogger(InventoryHandler.class);

    private final IInventoryService inventoryService;

    @Override
    public Inventory updateStock(Integer stockId, Inventory updateStock) {
        return inventoryService.updateStock(updateStock.getCurrentPrice(), stockId);
    }
    
    public void inventorySupply(List<Inventory> inventoryList) {
        inventoryService.inventorySupply(inventoryList);
    }
}

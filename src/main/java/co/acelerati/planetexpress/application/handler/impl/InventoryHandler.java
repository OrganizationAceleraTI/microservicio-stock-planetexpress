package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryHandler implements IInventoryHandler {

    private static final Logger logger = LoggerFactory.getLogger(InventoryHandler.class);

    private final IInventoryService inventoryService;

    @Override
    public Inventory updateStock(Inventory updateStock) {
        return inventoryService.updateStock(updateStock);
    }
    
    public void inventorySupply(List<Inventory> inventoryList) {
        inventoryService.inventorySupply(inventoryList);
    }
}

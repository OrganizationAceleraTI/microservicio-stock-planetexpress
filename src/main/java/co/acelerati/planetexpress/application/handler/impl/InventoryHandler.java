package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.dto.response.InventoryResponse;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.IInventoryResponseMapper;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InventoryHandler implements IInventoryHandler {

    private final IInventoryService inventoryService;

    private final IInventoryResponseMapper inventoryResponseMapper;

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }
}

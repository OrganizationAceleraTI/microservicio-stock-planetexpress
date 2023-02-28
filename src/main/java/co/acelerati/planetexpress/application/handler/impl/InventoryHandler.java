package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
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

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @Override
    public void inventorySupply(List<InventorySupplyRequestDTO> inventorySupplyRequestDTO) {
        inventoryService.inventorySupply(InventorySupplyRequestMapper.toInventoryModelList(inventorySupplyRequestDTO));
    }
}

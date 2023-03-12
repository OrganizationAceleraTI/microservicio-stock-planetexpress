package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.application.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
import co.acelerati.planetexpress.application.mapper.ProviderResponseMapper;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public
class InventoryHandler implements IInventoryHandler {

    private final IInventoryService inventoryService;

    @Override
    public ProviderResponseDTO inventorySupply(List<InventorySupplyRequestDTO> inventorySupplyRequestDTO) {
        Provider provider = inventoryService.inventorySupply(InventorySupplyRequestMapper.toInventoryModelList(inventorySupplyRequestDTO));
        return ProviderResponseMapper.toProviderResponse(provider);
    }

    @Override
    public List<Inventory> getAllInventory() {
        return null;
    }

    @Override
    public List<Inventory> getInventoryByPriceIsNull(Integer currentPrice, int page) {
        System.out.println("currentPrice = " + currentPrice + ", page = " + page);
        return inventoryService.getInventoryByPriceIsNull(currentPrice, page);
    }

    @Override
    public List<Inventory> getByQuantityIsNull(Integer quantity, int page) {
        return null;
    }

}

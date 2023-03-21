package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
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
    public void inventorySupply(List<InventorySupplyRequestDTO> inventorySupplyRequestDTO) {
        inventoryService.inventorySupply(InventorySupplyRequestMapper.toInventoryModelList(inventorySupplyRequestDTO));
    }

    @Override
    public List<Inventory> getAllInventory(int page) {
        return inventoryService.getAllInventory(page);
    }

    @Override
    public List<Inventory> getInventoryByPrice(Integer currentPrice, int page) {
        System.out.println("currentPrice = " + currentPrice + ", page = " + page);
        return inventoryService.getInventoryByPrice(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, int page) {
        return inventoryService.getByCurrentPriceLessThanEqual(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, int page) {
        return inventoryService.getByCurrentPriceGreaterThanEqual(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, int page) {
        return inventoryService.getByCurrentPriceBetween(minPrice, maxPrice, page);
    }
}

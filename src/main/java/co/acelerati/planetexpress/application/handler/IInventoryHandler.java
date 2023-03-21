package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;

import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory(int page);
    List<Inventory> getInventoryByPrice(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, int page);
    List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, int page);
    void inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);

}

package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.application.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory();

    void inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);
    List<Inventory> getInventoryByPriceIsNull(Integer currentPrice);
}

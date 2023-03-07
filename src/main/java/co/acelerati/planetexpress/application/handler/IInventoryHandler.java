package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.application.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.domain.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory();

    void inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);
    List<Inventory> getInventoryByPriceIsNull(Integer currentPrice, int page);

    List<Inventory> getByQuantityIsNull(Integer quantity, int page);
}

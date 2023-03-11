package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;

import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory();
    List<Inventory> getInventoryByPrice(Integer currentPrice, int page);
    List<Inventory> getByQuantityIsNull(Integer quantity, int page);
    ProviderResponseDTO inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);
}

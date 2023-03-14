package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.application.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;

import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory();
    List<Inventory> getInventoryByPrice(Integer currentPrice, int page);
    List<Inventory> getByQuantityIsNull(Integer quantity, int page);
    void inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);

}

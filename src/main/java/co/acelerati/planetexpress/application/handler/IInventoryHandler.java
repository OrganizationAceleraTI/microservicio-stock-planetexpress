package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;

import java.util.List;

public interface IInventoryHandler {

    void inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);

    void updateStock(UpdateStockRequestDTO updateStockRequest);
}

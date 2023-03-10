package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;

import java.util.List;

public interface IInventoryHandler {

    void inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);
}

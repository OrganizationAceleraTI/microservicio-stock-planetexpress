package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;

import java.util.List;

public interface IInventoryHandler {

    ProviderResponseDTO inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);
}

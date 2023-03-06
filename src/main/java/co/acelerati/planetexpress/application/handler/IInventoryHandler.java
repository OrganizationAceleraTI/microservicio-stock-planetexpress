package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.application.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.dto.response.ProviderResponseDTO;

import java.util.List;

public interface IInventoryHandler {

    ProviderResponseDTO inventorySupply(List<InventorySupplyRequestDTO> InventorySupplyRequestDTO);
}

package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;
import java.util.stream.Collectors;

public class InventorySupplyRequestMapper {

    public static Inventory toInventoryModel(InventorySupplyRequestDTO inventorySupplyRequestDTO) {
        return new Inventory(inventorySupplyRequestDTO.getProductId(),
                             inventorySupplyRequestDTO.getPersonSupplierId(),
                             inventorySupplyRequestDTO.getIncomingPrice(),
                             inventorySupplyRequestDTO.getCurrentPrice(),
                             inventorySupplyRequestDTO.getQuantity());
    }

    public static List<Inventory> toInventoryModelList(List<InventorySupplyRequestDTO> inventorySupplyRequestDTO) {
        return inventorySupplyRequestDTO.stream()
              .map(InventorySupplyRequestMapper::toInventoryModel)
              .collect(Collectors.toList());
    }

    private InventorySupplyRequestMapper() {  }
}

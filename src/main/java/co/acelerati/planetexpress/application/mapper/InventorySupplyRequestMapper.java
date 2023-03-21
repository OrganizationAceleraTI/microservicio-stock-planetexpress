package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;

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

    public static ProductResponseDTO toProductDTO(Inventory inventory) {
        return new ProductResponseDTO(Integer.toUnsignedLong(inventory.getProductId()),
          "name", "description", "model", Integer.toUnsignedLong(1), Integer.toUnsignedLong(1),
          inventory.getQuantity(),
          inventory.getIncomingPrice(),
          inventory.getCurrentPrice());
    }

    public static List<ProductResponseDTO> toProductDTOList(List<Inventory> inventory) {
        return inventory.stream()
          .map(InventorySupplyRequestMapper::toProductDTO)
          .collect(Collectors.toList());
    }

    private InventorySupplyRequestMapper() {  }
}
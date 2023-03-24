package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.UpdateStockResponseDTO;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;

import java.util.Optional;

public class InventoryUpdateMapper {

    private InventoryUpdateMapper() { }

    public static Optional<Inventory> entityToModelOptional(Optional<InventoryEntity> updateStock) {
        return updateStock.map(inventoryEntity -> new Inventory(
          inventoryEntity.getProductId(),
          inventoryEntity.getPersonSupplierId(),
          inventoryEntity.getProductId(),
          inventoryEntity.getIncomingPrice(),
          inventoryEntity.getCurrentPrice(),
          inventoryEntity.getQuantity()));
    }

    public static Inventory optionalToModel(Optional<Inventory> optionalInventory) {
        return optionalInventory.map(inventoryEntity -> new Inventory(
          inventoryEntity.getProductId(),
          inventoryEntity.getPersonSupplierId(),
          inventoryEntity.getProductId(),
          inventoryEntity.getIncomingPrice(),
          inventoryEntity.getCurrentPrice(),
          inventoryEntity.getQuantity())).orElse(null);
    }

    public static UpdateStockResponseDTO modelToResponse(Inventory inventory) {
        return new UpdateStockResponseDTO(inventory.getInventoryId(), inventory.getPersonSupplierId(), inventory.getProductId(),
          inventory.getIncomingPrice(), inventory.getCurrentPrice(), inventory.getQuantity());
    }

    public static Inventory requestToModel(UpdateStockRequestDTO request) {
        return new Inventory(request.getInventoryId(), request.getSalePrice());
    }

    public static InventoryEntity modelToEntity(Inventory inventory) {
        return new InventoryEntity(inventory.getInventoryId(),
          inventory.getPersonSupplierId(),
          inventory.getProductId(),
          inventory.getIncomingPrice(),
          inventory.getCurrentPrice(),
          inventory.getQuantity());
    }

    public static Inventory entityToModel(InventoryEntity inventoryEntity) {
        return new Inventory(inventoryEntity.getInventoryId(),
          inventoryEntity.getPersonSupplierId(),
          inventoryEntity.getProductId(),
          inventoryEntity.getIncomingPrice(),
          inventoryEntity.getCurrentPrice(),
          inventoryEntity.getQuantity());
    }
}

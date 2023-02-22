package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.application.dto.response.InventoryResponse;
import co.acelerati.planetexpress.domain.model.Inventory;

import java.util.List;

public interface IInventoryHandler {

    List<Inventory> getAllInventory();
}

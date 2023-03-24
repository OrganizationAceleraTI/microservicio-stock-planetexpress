package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;


public interface IInventoryHandler {

    Inventory updateStock(Inventory updateStock);
}

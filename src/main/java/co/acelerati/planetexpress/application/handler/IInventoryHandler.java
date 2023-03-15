package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;


public interface IInventoryHandler {

    void updateStock(UpdateStockRequestDTO updateStockRequest);
}

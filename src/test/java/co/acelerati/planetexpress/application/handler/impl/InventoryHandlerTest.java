package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InventoryHandlerTest {

    private IInventoryHandler inventoryHandler;

    @BeforeEach
    void setUp() {
        inventoryHandler = mock(IInventoryHandler.class);
    }

    @Test
    void updateStock() {
        UpdateStockRequestDTO data = new UpdateStockRequestDTO(1, 1, 250,100);
        inventoryHandler.updateStock(data);
        verify(inventoryHandler, times(1)).updateStock(data);
    }
}
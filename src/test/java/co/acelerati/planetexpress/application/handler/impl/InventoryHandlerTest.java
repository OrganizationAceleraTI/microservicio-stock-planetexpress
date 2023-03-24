package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.model.Inventory;
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
        Inventory data = new Inventory(1, 800);
        inventoryHandler.updateStock(data);
        verify(inventoryHandler, times(1)).updateStock(data);
    }
}
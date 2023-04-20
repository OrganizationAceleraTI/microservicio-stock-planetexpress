package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class InventoryHandlerTest {

    private IInventoryHandler inventoryHandler;

    @BeforeEach
    void setUp() {
        inventoryHandler = mock(IInventoryHandler.class);
    }

    @Test
    void updateStock() {

    }
}
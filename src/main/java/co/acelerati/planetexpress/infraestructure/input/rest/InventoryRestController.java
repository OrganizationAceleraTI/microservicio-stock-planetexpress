package co.acelerati.planetexpress.infraestructure.input.rest;

import co.acelerati.planetexpress.application.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryRestController {

    private final IInventoryHandler inventoryHandler;

    @GetMapping("/")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryHandler.getAllInventory());
    }

    @PostMapping("/supply")
    public ResponseEntity<ProviderResponseDTO> inventorySupply(@RequestBody List<InventorySupplyRequestDTO> inventorySupplyRequestDTOList){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryHandler.inventorySupply(inventorySupplyRequestDTOList));
    }
}

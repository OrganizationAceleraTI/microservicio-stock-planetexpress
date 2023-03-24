package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.UpdateStockResponseDTO;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventoryUpdateMapper;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryRestController {

    private final IInventoryHandler inventoryHandler;

    @PatchMapping("/update")
    public ResponseEntity<UpdateStockResponseDTO> updateStock(@RequestBody @Valid UpdateStockRequestDTO updateStock) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
          .contentType(MediaType.APPLICATION_JSON)
          .body(InventoryUpdateMapper.modelToResponse(
            inventoryHandler.updateStock(InventoryUpdateMapper.requestToModel(updateStock))));
    }
}

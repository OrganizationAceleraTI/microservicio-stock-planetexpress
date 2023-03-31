package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
import co.acelerati.planetexpress.infraestructure.mapper.InventoryUpdateMapper;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.UpdateStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryRestController {

    /**
     * feignclient user ->> pendiente terminar de implementar con token
     */
    private final IUserFeignClient userFeignClient;

    private final IInventoryHandler inventoryHandler;

    @PatchMapping("/update/{stockId}")
    public ResponseEntity<UpdateStockResponseDTO> updateStock(@PathVariable Integer stockId,
                                                              @RequestBody @Valid UpdateStockRequestDTO updateStock) {
        inventoryHandler.updateStock(stockId, InventoryUpdateMapper.requestToModel(updateStock));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/supply")
    public ResponseEntity<Void> inventorySupply(
      @RequestBody List<@Valid InventorySupplyRequestDTO> inventorySupplyRequestDTOList) {
        inventoryHandler.inventorySupply(InventorySupplyRequestMapper.toInventoryModelList(inventorySupplyRequestDTOList));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ProviderResponseDTO> getProviderFeignClient(@RequestParam Integer id) {
        // LLAMA FEIGN CLIENT END POINT MOCKUP
        return ResponseEntity.ok(userFeignClient.getProvider(id));
    }
}

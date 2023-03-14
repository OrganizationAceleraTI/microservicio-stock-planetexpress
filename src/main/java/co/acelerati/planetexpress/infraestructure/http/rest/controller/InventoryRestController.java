package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final IInventoryHandler inventoryHandler;

    /**
     * feignclient user ->> pendiente terminar de implementar con token
     *
     */
    private final UserService userService;

    @PostMapping("/supply")
    public ResponseEntity<Void> inventorySupply(
      @RequestBody List<@Valid InventorySupplyRequestDTO> inventorySupplyRequestDTOList){
        inventoryHandler.inventorySupply(inventorySupplyRequestDTOList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ProviderResponseDTO> getProviderFeignClient(@RequestParam Integer id) {
        // LLAMA FEIGN CLIENT END POINT MOCKUP
        return ResponseEntity.ok(userService.getProvider(id));
    }

    @GetMapping("/filter")
    ResponseEntity getInventoryByPrice(@RequestParam MultiValueMap<String, String> filters){
        System.out.println("Filters:");
        filters.get("currentPrice");
        System.out.println("Price: "+filters.get("currentPrice"));
        System.out.println("Page: "+filters.get("page"));
        return ResponseEntity.ok(inventoryHandler.getInventoryByPrice(Integer.parseInt(filters.get("currentPrice").get(0)), Integer.parseInt(filters.get("page").get(0))));
    }
}

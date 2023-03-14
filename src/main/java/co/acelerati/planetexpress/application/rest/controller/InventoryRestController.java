package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryRestController {

    private final IInventoryHandler inventoryHandler;

    @PostMapping("/supply")
    public ResponseEntity<ProviderResponseDTO> inventorySupply(
      @RequestBody List<@Valid InventorySupplyRequestDTO> inventorySupplyRequestDTOList){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryHandler
                .inventorySupply(inventorySupplyRequestDTOList));
    }
    
 /*   @GetMapping
    ResponseEntity<List<Inventory>> getInventoryByPriceIsNulla(@RequestParam(value = "filter") String filters){
        //System.out.println("filters = " + filters);
        String [] filter = filters.split("_");
        int currentPrice = Integer.parseInt(filter[0].split("-")[1]);
        int page = Integer.parseInt(filter[1].split("-")[1]);
            return ResponseEntity.ok(inventoryHandler.getInventoryByPriceIsNull(currentPrice, page));
    }*/

    @GetMapping("/filter")
    ResponseEntity getInventoryByPriceIsNull(@RequestParam MultiValueMap<String, String> filters){
        System.out.println("Filters:");
        filters.get("currentPrice");
        System.out.println("Price: "+filters.get("currentPrice"));
        System.out.println("Page: "+filters.get("page"));
      //  return ResponseEntity.ok(HttpStatus.OK);
        return ResponseEntity.ok(inventoryHandler.getInventoryByPrice(Integer.parseInt(filters.get("currentPrice").get(0)), Integer.parseInt(filters.get("page").get(0))));
    }

    @GetMapping
    ResponseEntity<List<Inventory>> getInventoryByPriceIsNullall(){
        System.out.println("entro = ");
        //String [] filter = filters.split("_");
       // int currentPrice = Integer.parseInt(filter[0].split("-")[1]);
       // int page = Integer.parseInt(filter[1].split("-")[1]);
        return ResponseEntity.ok(inventoryHandler.getInventoryByPrice(10000, 0));
    }
}

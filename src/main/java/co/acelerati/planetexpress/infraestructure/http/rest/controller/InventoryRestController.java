package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.service.ProductService;
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
    private final ProductService productService;

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
    ResponseEntity<List<Inventory>> getInventoryByPrice(@RequestParam MultiValueMap<String, String> filters){
        System.out.println("Filters:");
        filters.get("currentPrice");
        System.out.println("Price: "+filters.get("currentPrice"));
        System.out.println("Page: "+filters.get("page"));
        return ResponseEntity.ok(inventoryHandler.getInventoryByPrice(Integer.parseInt(filters.get("currentPrice").get(0)), Integer.parseInt(filters.get("page").get(0))));
    }

    @GetMapping("/filtro")
    ResponseEntity<List<ProductResponseDTO>> getProductWithFilter(@RequestParam MultiValueMap<String, Integer> filters){
        System.out.println("Filters:");
        System.out.println("Price: "+filters.get("currentPrice"));
        System.out.println("Min Price: "+filters.get("minPrice"));
        System.out.println("Max Price: "+filters.get("maxPrice"));
        System.out.println("Brand: "+filters.get("brand"));
        System.out.println("Category: "+filters.get("category"));
        System.out.println("Page: "+filters.get("page"));
        int currentPrice = filters.get("currentPrice").get(0);
        int minPrice = filters.get("currentPrice").get(0);
        int maxPrice = filters.get("currentPrice").get(0);
        int page = filters.get("currentPrice").get(0);
        //getByCurrentPriceGreaterThanEqual
        if(!filters.get("maxPrice").isEmpty()){
            return ResponseEntity.ok(InventorySupplyRequestMapper.toProductDTOList(inventoryHandler.getByCurrentPriceGreaterThanEqual(maxPrice, page)));
        }

        //findByCurrentPriceLessThanEqual
        if(!filters.get("minPrice").isEmpty()){
            return ResponseEntity.ok(InventorySupplyRequestMapper.toProductDTOList(inventoryHandler.getByCurrentPriceLessThanEqual(minPrice, page)));
        }



        //getByCurrentPriceBetween
        if(!filters.get("minPrice").isEmpty() && !filters.get("maxPrice").isEmpty()){
            return ResponseEntity.ok(InventorySupplyRequestMapper.toProductDTOList(inventoryHandler.getByCurrentPriceBetween(minPrice, maxPrice, page)));
        }


        if(!filters.get("currentPrice").isEmpty()){
            return ResponseEntity.ok(InventorySupplyRequestMapper.toProductDTOList(inventoryHandler.getInventoryByPrice(currentPrice, page)));
        }
        return ResponseEntity.ok(InventorySupplyRequestMapper.toProductDTOList(inventoryHandler.getAllInventory(page)));
    }
}

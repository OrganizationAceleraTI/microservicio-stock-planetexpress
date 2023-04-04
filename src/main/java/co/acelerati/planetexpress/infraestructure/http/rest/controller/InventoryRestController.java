package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.BrandResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.CategoryResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.DetailStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IBrandFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.ICategoryFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IProductFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IUserFeignClient;
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
    private final IProductFeignClient productFeignClient;
    private final IBrandFeignClient brandFeignClient;
    private final ICategoryFeignClient categoryFeignClient;
    private final IUserFeignClient userFeignClient;

    @PostMapping("/supply")
    public ResponseEntity<Void> inventorySupply(
      @RequestBody List<@Valid InventorySupplyRequestDTO> inventorySupplyRequestDTOList){
        inventoryHandler.inventorySupply(InventorySupplyRequestMapper.toInventoryModelList(inventorySupplyRequestDTOList));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ProviderResponseDTO> getProviderFeignClient(@RequestParam Integer id) {
        // LLAMA FEIGN CLIENT END POINT MOCKUP
        return ResponseEntity.ok(userFeignClient.getProvider(id));
    }

    @GetMapping("/filtro")
    ResponseEntity<List<DetailStockResponseDTO>> getProductWithFilter(@RequestParam MultiValueMap<String, String> filters){
        List<CategoryResponseDTO> categories = categoryFeignClient.getCategories(0, 1000);
        List<BrandResponseDTO> brands = brandFeignClient.getBrands(0, 1000);
        List<ProductResponseDTO> products = productFeignClient.getProducts(0, 1000);

        return ResponseEntity.ok(InventorySupplyRequestMapper.toProductDTOList(inventoryHandler.allProducts(filters,
          InventorySupplyRequestMapper.toProductList(products),
          InventorySupplyRequestMapper.toCategoryList(categories),
          InventorySupplyRequestMapper.toBrandList(brands))));
    }
}

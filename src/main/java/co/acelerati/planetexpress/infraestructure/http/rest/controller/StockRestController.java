package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.SupplyStockRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.UpdateStockRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.DetailStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.UpdateStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IBrandFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.ICategoryFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IProductFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.mapper.StockRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockRestController {
    private final IBrandFeignClient brandFeignClient;
    private final ICategoryFeignClient categoryFeignClient;
    private final IProductFeignClient productFeignClient;
    private final IStockHandler stockHandler;

    @GetMapping("/filtro")
    ResponseEntity<List<DetailStockResponseDTO>> getProductWithFilter(@RequestParam MultiValueMap<String, String> filters) {
        List<DetailStockResponseDTO> detailStockResponseDTOS =stockHandler.allProducts(filters,
            StockRequestMapper.toProductList(productFeignClient.getProducts(0, 1000)),
            StockRequestMapper.toCategoryList(categoryFeignClient.getCategories(0, 1000)),
            StockRequestMapper.toBrandList(brandFeignClient.getBrands(0, 1000)))
          .stream().map(StockRequestMapper::toProductDTO).collect(Collectors.toList());
        return (!detailStockResponseDTOS.isEmpty())
                    ? ResponseEntity.ok(detailStockResponseDTOS)
                    : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{stockId}")
    public ResponseEntity<UpdateStockResponseDTO> updateStock(@PathVariable Integer stockId,
                                                              @RequestBody @Valid UpdateStockRequestDTO updateStock) {
        stockHandler.updateStock(stockId, StockRequestMapper.toStock(updateStock));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/supply/{supplierId}")
    public ResponseEntity<Void> supplyStock(@PathVariable Integer supplierId,
      @RequestBody List<@Valid SupplyStockRequestDTO> supplyStockRequestDTOList) {
        stockHandler.supplyStock(supplyStockRequestDTOList.stream().map(StockRequestMapper::toModel).collect(Collectors.toList()), supplierId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.application.mapper.StockRequestMapper;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.DetailStockResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IBrandFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.ICategoryFeignClient;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<List<DetailStockResponseDTO>> getProductWithFilter(@RequestParam MultiValueMap<String, String> filters){
        return ResponseEntity.ok(stockHandler.allProducts(filters,
          StockRequestMapper.toProductList(productFeignClient.getProducts(0, 1000)),
          StockRequestMapper.toCategoryList(categoryFeignClient.getCategories(0, 1000)),
          StockRequestMapper.toBrandList(brandFeignClient.getBrands(0, 1000)))
          .stream().map(StockRequestMapper::toProductDTO).collect(Collectors.toList()));
    }
}

package co.acelerati.planetexpress.infraestructure.http.rest.feign.client;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORY-API1", url = "${feign-client.product.root.url}", configuration = FeignClientConfig.class)
public interface IProductFeignClient {

    @GetMapping(value = "/products?page={page}&itemsNumber={itemsNumber}")
    List<ProductResponseDTO> getProducts(@PathVariable Integer page, @PathVariable Integer itemsNumber);

  /*  @GetMapping(value = "/brands?page={page}&itemsNumber={itemsNumber}")
    List<BrandResponseDTO> getBrands(@PathVariable Integer page, @PathVariable Integer itemsNumber);

    @GetMapping(value = "/categories?page={page}&itemsNumber={itemsNumber}")
    List<CategoryResponseDTO> getCategories(@PathVariable Integer page, @PathVariable Integer itemsNumber);*/


}

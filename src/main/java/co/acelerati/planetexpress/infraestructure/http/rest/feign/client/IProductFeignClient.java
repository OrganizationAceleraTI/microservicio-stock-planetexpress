package co.acelerati.planetexpress.infraestructure.http.rest.feign.client;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-API1", url = "${feign-client.product.root.url}", configuration = FeignClientConfig.class)
public interface IProductFeignClient {

    @GetMapping(value = "/products/{id}")
    ProductResponseDTO getProduct(@PathVariable Integer id);

    @GetMapping(value = "/brands/{id}")
    ProductResponseDTO getBrands(@PathVariable Integer id);

    @GetMapping(value = "/categories/{id}")
    ProductResponseDTO getCategories(@PathVariable Integer id);

}

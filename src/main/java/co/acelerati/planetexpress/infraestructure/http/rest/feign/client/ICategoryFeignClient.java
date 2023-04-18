package co.acelerati.planetexpress.infraestructure.http.rest.feign.client;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.CategoryResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORY-API2", url = "${feign-client.category.root.url}", configuration = FeignClientConfig.class)
public interface ICategoryFeignClient {

    @GetMapping(value = "/categories?page={page}&itemsNumber={itemsNumber}")
    List<CategoryResponseDTO> getCategories(@PathVariable Integer page, @PathVariable Integer itemsNumber);


}

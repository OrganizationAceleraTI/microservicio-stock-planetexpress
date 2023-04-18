package co.acelerati.planetexpress.infraestructure.http.rest.feign.client;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.BrandResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "INVENTORY-API3", url = "${feign-client.brand.root.url}", configuration = FeignClientConfig.class)
public interface IBrandFeignClient {

    @GetMapping(value = "/brands?page={page}&itemsNumber={itemsNumber}")
    List<BrandResponseDTO> getBrands(@PathVariable Integer page, @PathVariable Integer itemsNumber);
}

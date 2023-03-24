package co.acelerati.planetexpress.infraestructure.http.rest.feign.client;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-API", url = "${feign-client.user.root.url}", configuration = FeignClientConfig.class)
public interface IUserFeignClient {

    @GetMapping(value = "/provider/{id}")
    ProviderResponseDTO getProvider(@PathVariable Integer id);

}

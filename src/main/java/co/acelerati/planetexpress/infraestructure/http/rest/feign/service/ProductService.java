package co.acelerati.planetexpress.infraestructure.http.rest.feign.service;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductFeignClient productFeignClient;

    public ProductResponseDTO getProduct(Integer id){
        return productFeignClient.getProduct(id);
    }

}

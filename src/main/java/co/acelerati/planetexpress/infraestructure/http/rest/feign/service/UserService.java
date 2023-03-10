package co.acelerati.planetexpress.infraestructure.http.rest.feign.service;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.feign.client.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserFeignClient userFeignClient;

    public ProviderResponseDTO getProvider(Integer id){
        return userFeignClient.getProvider(id);
    }

}

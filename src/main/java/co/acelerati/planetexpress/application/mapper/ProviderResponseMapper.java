package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.domain.model.Provider;

public class ProviderResponseMapper {

    public static ProviderResponseDTO toProviderResponse(Provider provider) {
        return new ProviderResponseDTO(provider.getName(),
                                       provider.getSurname(),
                                       provider.getIdPersonProvider());
    }

    private ProviderResponseMapper() {
    }
}

package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.application.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.application.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.Provider;

import java.util.List;
import java.util.stream.Collectors;

public class ProviderResponseMapper {

    public static ProviderResponseDTO toProviderResponse(Provider provider) {
        return new ProviderResponseDTO(provider.getName(), provider.getSurname(), provider.getIdPersonProvider());
    }

}

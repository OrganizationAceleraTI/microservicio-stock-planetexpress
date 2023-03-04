package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.application.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.domain.model.User;

public class ProviderResponseMapper {

    public static ProviderResponseDTO toProviderResponse(User user) {
        return new ProviderResponseDTO(user.getName(), user.getSurname(), user.getMail(),
                user.getPhone(), user.getAddress(), user.getIdDniType(), user.getDniNumber(),
                user.getIdPersonType());
    }

}

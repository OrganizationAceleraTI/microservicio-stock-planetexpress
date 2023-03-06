package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.application.dto.response.UserResponseDTO;
import co.acelerati.planetexpress.domain.model.User;

public class UserResponseMapper {

    public static UserResponseDTO toUserResponse(User user) {
        return new UserResponseDTO(user.getName(),
                                   user.getSurname(),
                                   user.getMail(),
                                   user.getPhone(),
                                   user.getAddress(),
                                   user.getIdDniType(),
                                   user.getDniNumber(),
                                   user.getIdPersonType());
    }

}

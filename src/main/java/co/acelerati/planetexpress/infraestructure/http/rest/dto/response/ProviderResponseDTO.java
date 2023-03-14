package co.acelerati.planetexpress.application.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponseDTO {

    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String address;
    private String idDniType;
    private String dniNumber;
    private String idPersonType;
}

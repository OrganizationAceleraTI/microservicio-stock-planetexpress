package co.acelerati.planetexpress.infraestructure.http.rest.dto.response;

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
    private Integer idPersonProvider;

}

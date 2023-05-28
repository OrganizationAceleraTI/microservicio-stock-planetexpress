package co.acelerati.planetexpress.infraestructure.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewCartRequestDTO {

    @NotNull
    private Integer stockId;

    @NotNull
    @Positive
    private Integer quantity;
}

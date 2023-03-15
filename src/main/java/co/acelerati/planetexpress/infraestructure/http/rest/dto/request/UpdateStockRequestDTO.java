package co.acelerati.planetexpress.infraestructure.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequestDTO {

    @NotNull
    private Integer inventoryId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer incomingPrice;
    @NotNull
    @Positive
    private Integer salePrice;
}

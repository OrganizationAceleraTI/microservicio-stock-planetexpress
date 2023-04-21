package co.acelerati.planetexpress.infraestructure.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Deprecated
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventorySupplyRequestDTO {

    @NotNull
    @Positive
    private Integer productId;
    @NotNull
    @Positive
    private Integer personSupplierId;
    @NotNull
    private Integer incomingPrice;
    @NotNull
    private Integer currentPrice;
    @NotNull
    private Integer quantity;

}

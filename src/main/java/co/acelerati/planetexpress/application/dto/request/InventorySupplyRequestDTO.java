package co.acelerati.planetexpress.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventorySupplyRequestDTO {

    @Pattern(regexp = "^[0-9]+$", message = "Valor entero no valido -> productId")
    private Integer productId;

    @Pattern(regexp = "^[0-9]+$", message = "Valor entero no valido -> personSupplierId")
    private Integer personSupplierId;

    @Pattern(regexp = "^[0-9]+$", message = "Valor entero no valido -> incomingPrice")
    private Integer incomingPrice;

    @Pattern(regexp = "^[0-9]+$", message = "Valor entero no valido -> currentPrice")
    private Integer currentPrice;

    @Pattern(regexp = "^[0-9]+$", message = "Valor entero no valido -> quantity")
    private Integer quantity;

}

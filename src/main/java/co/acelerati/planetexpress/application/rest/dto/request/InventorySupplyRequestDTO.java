package co.acelerati.planetexpress.infraestructure.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventorySupplyRequestDTO {

    private Integer productId;
    private Integer personSupplierId;
    private Integer incomingPrice;
    private Integer currentPrice;
    private Integer quantity;

}

package co.acelerati.planetexpress.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

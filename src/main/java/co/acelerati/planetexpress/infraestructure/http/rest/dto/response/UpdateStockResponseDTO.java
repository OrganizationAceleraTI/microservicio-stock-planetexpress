package co.acelerati.planetexpress.infraestructure.http.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockResponseDTO {

    private Integer inventoryId;
    private Integer personSupplierId;
    private Integer productId;
    private Integer incomingPrice;
    private Integer currentPrice;
    private Integer quantity;
}

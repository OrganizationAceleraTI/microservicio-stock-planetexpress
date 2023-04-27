package co.acelerati.planetexpress.infraestructure.http.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplyStockRequestDTO {

    private int productId;
    private int quantity;
    private double currentPrice;

}

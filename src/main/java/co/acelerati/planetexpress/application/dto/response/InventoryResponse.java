package co.acelerati.planetexpress.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InventoryResponse {

    private Integer currentPage;
    private Integer lastPage;
    private List<ProductInventory> products;
}

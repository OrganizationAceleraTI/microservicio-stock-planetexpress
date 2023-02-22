package co.acelerati.planetexpress.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProductInventory {
    private Long productId;
    private String productName;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;
    private Integer quantity;
    private BigDecimal price;
}

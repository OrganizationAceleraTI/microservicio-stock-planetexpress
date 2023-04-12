package co.acelerati.planetexpress.infraestructure.http.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;
    private int quantity;
    private int incomingPrice;
    private int currentPrice;
}

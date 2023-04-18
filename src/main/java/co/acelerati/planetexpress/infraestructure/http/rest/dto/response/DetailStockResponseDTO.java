package co.acelerati.planetexpress.infraestructure.http.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailStockResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String model;
    private String brand;
    private String category;
    private int quantity;
    private double currentPrice;
}

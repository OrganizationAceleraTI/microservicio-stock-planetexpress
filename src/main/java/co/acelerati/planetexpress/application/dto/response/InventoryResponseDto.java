package co.acelerati.planetexpress.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDto {

    private Integer currentPage;
    private Integer lastPage;
    private List<ProductInventoryDto> products;
}

package co.acelerati.planetexpress.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDto {

    private Integer limit;
    private String userId;
    private Integer page;

}

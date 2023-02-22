package co.acelerati.planetexpress.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryRequest {

    private Integer limit;
    private String userId;
    private Integer page;

}

package co.acelerati.planetexpress.application.mapper;

import co.acelerati.planetexpress.application.dto.response.InventoryResponse;
import co.acelerati.planetexpress.domain.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IInventoryResponseMapper {

    //List<InventoryResponse> toResponseList(List<Inventory> inventoryList);
}

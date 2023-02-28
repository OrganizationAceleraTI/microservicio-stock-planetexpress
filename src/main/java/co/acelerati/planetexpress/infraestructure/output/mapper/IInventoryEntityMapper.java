package co.acelerati.planetexpress.infraestructure.output.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IInventoryEntityMapper {

    List<Inventory> toInventoryList(List<InventoryEntity> inventoryEntityList);

    Inventory toInventoryModel(InventoryEntity inventoryEntity);

    InventoryEntity toInventoryEntity(Inventory inventory);
}

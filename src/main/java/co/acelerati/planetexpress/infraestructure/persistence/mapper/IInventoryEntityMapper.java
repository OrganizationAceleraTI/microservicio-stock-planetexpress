package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Deprecated
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IInventoryEntityMapper {

    Inventory toInventoryModel(InventoryEntity inventoryEntity);

    InventoryEntity toInventoryEntity(Inventory inventory);
}

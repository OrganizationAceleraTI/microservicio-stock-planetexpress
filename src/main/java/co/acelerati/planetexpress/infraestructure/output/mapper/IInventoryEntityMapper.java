package co.acelerati.planetexpress.infraestructure.output.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IInventoryEntityMapper {

    //IInventoryEntityMapper INSTANCE = Mappers.getMapper(IInventoryEntityMapper.class);
    List<Inventory> toInventoryList(Optional<List<Inventory>> inventoryEntityList);

    Inventory toInventoryModel(InventoryEntity inventoryEntity);

    InventoryEntity toInventoryEntity(Inventory inventory);
}

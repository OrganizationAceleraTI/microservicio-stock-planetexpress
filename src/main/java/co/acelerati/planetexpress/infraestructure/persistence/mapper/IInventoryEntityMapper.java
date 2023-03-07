package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, uses ={Inventory.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IInventoryEntityMapper {

    List<Inventory> toInventoryList(List<InventoryEntity> inventoryEntityList);

    Inventory toInventoryModel(InventoryEntity inventoryEntity);

    InventoryEntity toInventoryEntity(Inventory inventory);
}

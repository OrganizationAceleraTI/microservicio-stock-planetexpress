package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
  uses = {Inventory.class})
public interface IInventoryEntityMapper {

    @Mappings({
      @Mapping(source = "inventoryId", target = "inventoryId"),
      @Mapping(source = "currentPrice", target = "currentPrice"),
      @Mapping(source = "incomingPrice", target = "incomingPrice"),
      @Mapping(source = "personSupplierId", target = "personSupplierId"),
      @Mapping(source = "productId", target = "productId"),
      @Mapping(source = "quantity", target = "quantity")
    })

    List<Inventory> toInventoryList(List<InventoryEntity> inventoryEntityList);

    Inventory toInventoryModel(InventoryEntity inventoryEntity);

    @InheritInverseConfiguration
    InventoryEntity toInventoryEntity(Inventory inventory);
}

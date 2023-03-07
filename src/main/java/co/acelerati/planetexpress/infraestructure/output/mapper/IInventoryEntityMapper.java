package co.acelerati.planetexpress.infraestructure.output.mapper;

import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IInventoryEntityMapper {

    //IInventoryEntityMapper INSTANCE = Mappers.getMapper(IInventoryEntityMapper.class);
    List<Inventory> toInventoryList(List<InventoryEntity> inventoryEntityList);

    /*List<Inventory> toInventoryList(Page<InventoryEntity> inventoryEntityPage) {
        List<Inventory> respuesta = new ArrayList<>();
        inventoryEntityPage.forEach(entity -> respuesta.add(toInventoryModel(entity)));
        return respuesta;
    }*/

    Inventory toInventoryModel(InventoryEntity inventoryEntity);

    InventoryEntity toInventoryEntity(Inventory inventory);
}

package co.acelerati.planetexpress.infraestructure.output.repository;

import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    InventoryEntity findByPersonSupplierIdAndProductId(Integer personSupplierId, Integer productId);

}

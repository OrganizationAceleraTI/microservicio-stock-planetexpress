package co.acelerati.planetexpress.infraestructure.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;

import java.util.Optional;

public interface IInventoryRepository extends PagingAndSortingRepository<InventoryEntity, Integer> {

    InventoryEntity findByPersonSupplierIdAndProductId(Integer personSupplierId, Integer productId);

    Optional<Page<InventoryEntity>> findByCurrentPrice(int currentPrice, Pageable page);

    Optional<Page<InventoryEntity>> findByQuantityIsNull(int quantity, Pageable page);
 }

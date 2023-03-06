package co.acelerati.planetexpress.infraestructure.output.repository;

import co.acelerati.planetexpress.infraestructure.output.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IInventoryRepository extends PagingAndSortingRepository<InventoryEntity, Integer> {

    InventoryEntity findByPersonSupplierIdAndProductId(Integer personSupplierId, Integer productId);

    Optional<Page<InventoryEntity>> findByCurrentPriceIsNull(int currentPrice, Pageable page);

    Optional<Page<InventoryEntity>> findByQuantityIsNull(int quantity, Pageable page);
 }

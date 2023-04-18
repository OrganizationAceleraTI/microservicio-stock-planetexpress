package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
@Deprecated
public interface IInventoryRepository extends PagingAndSortingRepository<InventoryEntity, Integer> {

    InventoryEntity findByPersonSupplierIdAndProductId(Integer personSupplierId, Integer productId);
    Optional<Page<InventoryEntity>> findByCurrentPrice(int currentPrice, Pageable page);
    Optional<Page<InventoryEntity>> findByCurrentPriceLessThanEqual(int currentPrice, Pageable page);
    Optional<Page<InventoryEntity>> findByCurrentPriceGreaterThanEqual(int currentPrice, Pageable page);
    Optional<Page<InventoryEntity>> findByCurrentPriceBetween(int minPrice, int maxPrice, Pageable page);
    Page<InventoryEntity> findAll(Pageable page);
    Optional<Page<InventoryEntity>> findByQuantityIsNull(int quantity, Pageable page);
}

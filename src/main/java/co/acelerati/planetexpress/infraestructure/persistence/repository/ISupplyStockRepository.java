package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyStockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISupplyStockRepository extends PagingAndSortingRepository<SupplyStockEntity, UUID> {

    Optional<Page<SupplyStockEntity>> findByCurrentPrice(double currentPrice, Pageable page);

    Optional<Page<SupplyStockEntity>> findByCurrentPriceLessThanEqual(double currentPrice, Pageable page);

    Optional<Page<SupplyStockEntity>> findByCurrentPriceGreaterThanEqual(double currentPrice, Pageable page);

    Optional<Page<SupplyStockEntity>> findByCurrentPriceBetween(double minPrice, double maxPrice, Pageable page);

}

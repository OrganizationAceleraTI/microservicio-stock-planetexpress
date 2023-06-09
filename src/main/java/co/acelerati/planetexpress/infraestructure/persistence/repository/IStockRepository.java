package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IStockRepository extends PagingAndSortingRepository<StockEntity, Integer> {

    Optional<StockEntity> findByProductId(Integer productId);
    Optional<Page<StockEntity>> findByCurrentPrice(double currentPrice, Pageable page);
    Optional<Page<StockEntity>> findByCurrentPriceLessThanEqual(double currentPrice, Pageable page);
    Optional<Page<StockEntity>> findByCurrentPriceGreaterThanEqual(double currentPrice, Pageable page);
    Optional<Page<StockEntity>> findByCurrentPriceBetween(double minPrice, double maxPrice, Pageable page);
    Optional<Page<StockEntity>> findByProductIdInAndCurrentPrice(List<Integer> productId, double currentPrice, Pageable page);
    Optional<Page<StockEntity>> findByProductIdInAndCurrentPriceLessThanEqual(List<Integer> productId, double currentPrice, Pageable page);
    Optional<Page<StockEntity>> findByProductIdInAndCurrentPriceGreaterThanEqual(List<Integer> productId, double currentPrice, Pageable page);
    Optional<Page<StockEntity>> findByProductIdInAndCurrentPriceBetween(List<Integer> productId, double minPrice, double maxPrice, Pageable page);
    Page<StockEntity> findAll(Pageable page);
    Optional<Page<StockEntity>> findByProductIdInAndCurrentPriceGreaterThanAndQuantityGreaterThan(List<Integer> productId, double minCurrentPrice, int minQuantity, Pageable page);
}

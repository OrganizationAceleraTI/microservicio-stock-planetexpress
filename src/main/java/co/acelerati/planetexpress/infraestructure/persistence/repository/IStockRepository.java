package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IStockRepository extends PagingAndSortingRepository<StockEntity, Integer> {

    Optional<StockEntity> findByProductId(Integer productId);

}

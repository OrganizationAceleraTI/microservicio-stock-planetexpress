package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyStockEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ISupplyStockRepository extends PagingAndSortingRepository<SupplyStockEntity, UUID> {

}

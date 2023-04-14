package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ISupplyRepository extends PagingAndSortingRepository<SupplyEntity, UUID> {
}

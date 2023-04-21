package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.SupplyStockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SupplyStockJpaAdapter implements ISupplyStockPersistence {

    private final ISupplyStockRepository repository;

    @Override
    public Optional<SupplyStock> insertSupplyStock(SupplyStock supplyStock) {
        return Optional.of(SupplyStockMapper.toDomain(
          repository.save(SupplyStockMapper.toEntity(supplyStock))
        ));
    }

    @Override
    public Optional<SupplyStock> getById(UUID id) throws NotFoundException {
        return repository.findById(id)
          .map(SupplyStockMapper::toDomain);
    }
}

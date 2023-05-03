package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.SupplyMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SupplyJpaAdapter implements ISupplyPersistence {

    private final ISupplyRepository repository;

    @Override
    public Optional<Supply> insertSupply(Supply supply) {
        return Optional.of(SupplyMapper.toDomain(repository.save(SupplyMapper.toEntity(supply))));
    }

    @Override
    public Optional<Supply> getById(UUID id) throws NotFoundException {
        return repository.findById(id)
          .map(SupplyMapper::toDomain);
    }
}

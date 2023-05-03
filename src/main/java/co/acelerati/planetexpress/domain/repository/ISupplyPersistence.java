package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.Supply;

import java.util.Optional;
import java.util.UUID;

public interface ISupplyPersistence {

    Optional<Supply> insertSupply(Supply supply);

    Optional<Supply> getById(UUID id);

}

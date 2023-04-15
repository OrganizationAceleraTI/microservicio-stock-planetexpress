package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.SupplyStock;

import java.util.Optional;
import java.util.UUID;

public interface ISupplyStockPersistence {

    Optional<SupplyStock> insertSupplyStock(SupplyStock supplyStock);

    Optional<SupplyStock> getById(UUID id);

}

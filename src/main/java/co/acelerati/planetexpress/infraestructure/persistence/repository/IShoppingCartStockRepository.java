package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartStockEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface IShoppingCartStockRepository extends PagingAndSortingRepository<ShoppingCartStockEntity, String> {

    Optional<ShoppingCartStockEntity> findByStockIdAndShoppingCartId(Integer stockId, int shoppingCartId);
}

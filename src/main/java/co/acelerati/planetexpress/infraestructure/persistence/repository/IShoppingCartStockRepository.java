package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartStockEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IShoppingCartStockRepository extends PagingAndSortingRepository<ShoppingCartStockEntity, String> {
}

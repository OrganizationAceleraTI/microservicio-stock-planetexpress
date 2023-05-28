package co.acelerati.planetexpress.infraestructure.persistence.repository;

import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCartEntity, String> {

    Optional<ShoppingCartEntity> findByUserId(Integer userId);
}

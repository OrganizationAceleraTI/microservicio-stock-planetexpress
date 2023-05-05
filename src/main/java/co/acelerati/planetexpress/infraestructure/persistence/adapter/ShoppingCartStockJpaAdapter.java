package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.domain.repository.IShoppingCartStockPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.ShoppingCartStockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IShoppingCartStockRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ShoppingCartStockJpaAdapter implements IShoppingCartStockPersistence {

    private final IShoppingCartStockRepository cartStockRepository;

    @Override
    public ShoppingCartStock addItemToCart(ShoppingCartStock shoppingCartStock) {
        return ShoppingCartStockMapper.toModel
          (cartStockRepository.save(ShoppingCartStockMapper.toEntity(shoppingCartStock)));
    }

    @Override
    public Optional<ShoppingCartStock> findByStockIdAndCart(int stockId, UUID shoppingCartId) {
        return cartStockRepository
          .findByStockIdAndShoppingCartId(stockId, shoppingCartId)
          .map(ShoppingCartStockMapper::toModel);
    }
}

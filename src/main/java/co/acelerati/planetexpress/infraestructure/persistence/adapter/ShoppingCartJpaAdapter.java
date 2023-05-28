package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.domain.repository.IShoppingCartPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.ShoppingCartMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IShoppingCartRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ShoppingCartJpaAdapter implements IShoppingCartPersistence {

    private final IShoppingCartRepository shoppingCartRepository;

    @Override
    public Optional<ShoppingCart> createCart(ShoppingCart shoppingCart) {
        return Optional.of
          (ShoppingCartMapper.toModel(shoppingCartRepository.save(ShoppingCartMapper.toEntity(shoppingCart))));
    }

    @Override
    public Optional<ShoppingCart> getCartByUser(Integer userId) throws NotFoundException {
        return shoppingCartRepository.findByUserId(userId).map(ShoppingCartMapper::toModel);
    }
}

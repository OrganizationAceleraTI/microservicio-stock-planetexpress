package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.domain.repository.IShoppingCartPersistence;
import co.acelerati.planetexpress.domain.repository.IShoppingCartStockPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartStockUseCase implements IShoppingCartStockService {

    private final IShoppingCartPersistence shoppingCartPersistence;

    private final IShoppingCartStockPersistence cartStockPersistence;

    @Override
    public void addItemToCart(Integer userId, ShoppingCartStock itemToAdd) {

        itemToAdd.setShoppingCartStockId(UUID.randomUUID());
        shoppingCartPersistence.getCartByUser(userId).ifPresentOrElse(shoppingCart -> {
            itemToAdd.setShoppingCartId(shoppingCart.getShoppingCartId());
        }, () -> {
           ShoppingCart newCart = this.createShoppingCart(new ShoppingCart(UUID.randomUUID(),
             userId, LocalDateTime.now()));
           itemToAdd.setShoppingCartId(newCart.getShoppingCartId());
        });

        cartStockPersistence.addItemToCart(itemToAdd);
    }

    private ShoppingCart createShoppingCart(ShoppingCart newCart) {
        return ShoppingCartMapper.toModel(shoppingCartPersistence.createCart(newCart));
    }
}

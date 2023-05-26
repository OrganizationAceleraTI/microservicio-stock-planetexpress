package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.ShoppingCartStockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.ShoppingCartMapper;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.ShoppingCartStockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IShoppingCartRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IShoppingCartStockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartStockJpaAdapterTest {

    private IShoppingCartStockRepository cartStockRepository;
    private ShoppingCartStockJpaAdapter shoppingCartStockJpaAdapter;

    @BeforeEach
    void setUp() {
        cartStockRepository = mock(IShoppingCartStockRepository.class);
        shoppingCartStockJpaAdapter = new ShoppingCartStockJpaAdapter(cartStockRepository);
    }

    @Test
    @DisplayName("Testing saving a new Shopping Cart Stock")
    void whenSaveShoppingCart_ThenReturnTheSavedItem() {
        ShoppingCartStock shoppingCartStock = new ShoppingCartStock(1,
          1, 1, 20);

        when(cartStockRepository.save(any(ShoppingCartStockEntity.class))).thenReturn(
          ShoppingCartStockMapper.toEntity(shoppingCartStock));

        ShoppingCartStock response = shoppingCartStockJpaAdapter.addItemToCart(shoppingCartStock);
        Assertions.assertEquals(shoppingCartStock.getShoppingCartStockId(), response.getShoppingCartStockId());
    }

    @Test
    @DisplayName("Testing the search by Stock Id and Cart Id")
    void whenSearchingForStockIdAndCartId_returnAnOptionalWithTheValue() {
        int cartId = 1;
        Optional<ShoppingCartStock> shoppingCartStock = Optional.of(new ShoppingCartStock(1,
          1, cartId, 20));
        Optional<ShoppingCartStockEntity> entity = Optional.of(new ShoppingCartStockEntity(1,
          1, cartId, 20));

        when(cartStockRepository.findByStockIdAndShoppingCartId(1, cartId)).thenReturn(entity);

        Optional<ShoppingCartStock> response = shoppingCartStockJpaAdapter.findByStockIdAndCart(1, cartId);
        Assertions.assertEquals(entity.get().getStockId(), response.get().getStockId());
        Assertions.assertEquals(entity.get().getShoppingCartId(), response.get().getShoppingCartId());
    }
}
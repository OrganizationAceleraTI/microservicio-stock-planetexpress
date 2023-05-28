package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.application.handler.IShoppingCartStockHandler;
import co.acelerati.planetexpress.application.handler.impl.ShoppingCartStockHandler;
import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.exception.BadRequestException;
import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.repository.IShoppingCartPersistence;
import co.acelerati.planetexpress.domain.repository.IShoppingCartStockPersistence;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShoppingCartStockUseCaseTest {

    private IShoppingCartStockPersistence cartStockPersistence;
    private IShoppingCartPersistence cartPersistence;
    private IStockPersistence stockPersistence;
    private ShoppingCartStockUseCase cartStockUseCase;

    @BeforeEach
    void setUp() {
        cartStockPersistence = mock(IShoppingCartStockPersistence.class);
        cartPersistence = mock(IShoppingCartPersistence.class);
        stockPersistence = mock(IStockPersistence.class);
        cartStockUseCase = new ShoppingCartStockUseCase(cartPersistence, cartStockPersistence, stockPersistence);
    }

    @Test
    @DisplayName("Testing the creation of the cart if the cart doesn't exists")
    void whenAddAndItemToTheCart_CreateTheCartIfNotExits() {
        ShoppingCartStock cartStock = new ShoppingCartStock(5, 3);
        Optional<Stock> stock = Optional.of(new Stock(5 , 30, 100));

        when(stockPersistence.getStockById(5)).thenReturn(stock);
        when(cartPersistence.getCartByUser(2)).thenReturn(Optional.empty());

        cartStockUseCase.addItemToCart(2, cartStock);
        verify(cartPersistence).createCart(any(ShoppingCart.class));
    }

    @Test
    @DisplayName("Testing if the requested quantity is available")
    void whenTheProductQuantityIsLessThanTheRequested_ThrowException() {
        ShoppingCartStock itemToAdd = new ShoppingCartStock(1, 20);
        Optional<Stock> stock = Optional.of(new Stock(5 , 10, 100));

        when(stockPersistence.getStockById(1)).thenReturn(stock);

        Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
           cartStockUseCase.addItemToCart(1, itemToAdd);
        });

        Assertions.assertEquals("The requested quantity is out of stock.", exception.getMessage());
    }

    @Test
    @DisplayName("Testing the addition of quantity when the stock exists in the cart")
    void whenTheProductExistsInTheCart_addTheQuantity() {
        int cartId = 10;
        int cartStockId = 2;
        ShoppingCartStock itemToAdd = new ShoppingCartStock(1, 20);
        Optional<Stock> stock = Optional.of(new Stock(5 , 100, 100));
        Optional<ShoppingCart> cart = Optional.of(new ShoppingCart(cartId, 1, LocalDateTime.now()));
        Optional<ShoppingCartStock> cartStock = Optional.of(new ShoppingCartStock(cartStockId,
          1, cartId, 20));

        when(stockPersistence.getStockById(1)).thenReturn(stock);
        when(cartPersistence.getCartByUser(1)).thenReturn(cart);
        when(cartStockPersistence.findByStockIdAndCart(1, cartId)).thenReturn(cartStock);
        cartStock.get().setQuantity(120);
        ShoppingCartStock addedStock = cartStock.get();

        cartStockUseCase.addItemToCart(1, itemToAdd);

        verify(cartStockPersistence).addItemToCart(addedStock);
    }

    @Test
    @DisplayName("Testing the creation of the new product in the cart")
    void whenTheProductDoesNotExistsInTheCart_ThenSaveANewOne() {
        int cartId = 2;
        ShoppingCartStock itemToAdd = new ShoppingCartStock(1, 20);
        Optional<Stock> stock = Optional.of(new Stock(5 , 100, 100));
        Optional<ShoppingCart> cart = Optional.of(new ShoppingCart(cartId, 1, LocalDateTime.now()));

        when(stockPersistence.getStockById(1)).thenReturn(stock);
        when(cartPersistence.getCartByUser(1)).thenReturn(cart);
        when(cartStockPersistence.findByStockIdAndCart(1, cartId)).thenReturn(Optional.empty());

        cartStockUseCase.addItemToCart(1, itemToAdd);

        verify(cartStockPersistence).addItemToCart(any(ShoppingCartStock.class));
    }

    @Test
    @DisplayName("Testing the response when de product does not exists")
    void whenTheProductDoesNotExists_TheThrowAnException() {
        ShoppingCartStock cartStock = new ShoppingCartStock(5, 3);

        when(stockPersistence.getStockById(5)).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> {
            cartStockUseCase.addItemToCart(2, cartStock);
        });

        Assertions.assertEquals("The product doesn't exists.", exception.getMessage());
    }
}

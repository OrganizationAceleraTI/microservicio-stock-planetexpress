package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.exception.BadRequestException;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.domain.repository.IShoppingCartPersistence;
import co.acelerati.planetexpress.domain.repository.IShoppingCartStockPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.ShoppingCartMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
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

    private final IStockRepository stockRepository;

    @Override
    public void addItemToCart(int userId, ShoppingCartStock itemToAdd) throws BadRequestException {

        this.validateExistingProduct(itemToAdd.getStockId()).map(product -> {
            if (product.getQuantity() < itemToAdd.getQuantity()) {
                throw new BadRequestException("The requested quantity is out of stock.");
            }

            return this.validateShoppingCartByUser(userId, itemToAdd);
        });


    }

    private Optional<ShoppingCartStock> validateShoppingCartByUser(int userId, ShoppingCartStock itemToAdd) {
         return shoppingCartPersistence.getCartByUser(userId).map(shoppingCart ->
            this.getProductByCart(itemToAdd.getStockId(), itemToAdd.getShoppingCartId())
              .map(product -> this.updateQuantityToProduct(product, itemToAdd.getQuantity())
              )//.orElse(cartStockPersistence.addItemToCart(itemToAdd))
           ).orElse(
            this.createShoppingCart(new ShoppingCart(UUID.randomUUID(),
              userId, LocalDateTime.now())).map(newCart -> {
                itemToAdd.setShoppingCartId(newCart.getShoppingCartId());
                return cartStockPersistence.addItemToCart(itemToAdd);
            }));
    }

    private Optional<ShoppingCart> createShoppingCart(ShoppingCart newCart) {
        return shoppingCartPersistence.createCart(newCart);
    }

    private Optional<ShoppingCartStock> getProductByCart(int stockId, UUID cartId) {
        return cartStockPersistence.findByStockIdAndCart(stockId, cartId);
    }

    private ShoppingCartStock updateQuantityToProduct(ShoppingCartStock currentProduct, int newQuantity) {
        currentProduct.setQuantity(currentProduct.getQuantity() + newQuantity);
        return cartStockPersistence.addItemToCart(currentProduct);
    }

    private Optional<StockEntity> validateExistingProduct(Integer productId) {
        return stockRepository.findByProductId(productId);
    }
}

package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.exception.BadRequestException;
import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.repository.IShoppingCartPersistence;
import co.acelerati.planetexpress.domain.repository.IShoppingCartStockPersistence;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
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
    private final IStockPersistence stockPersistence;

    @Override
    public void addItemToCart(Integer userId, ShoppingCartStock itemToAdd) throws BadRequestException {
        this.validateExistingProduct(itemToAdd.getStockId()).map(product -> {
            if (product.getQuantity() < itemToAdd.getQuantity()) {
                throw new BadRequestException("The requested quantity is out of stock.");
            }

            this.getShoppingCartByUserOrCreate(userId, itemToAdd);
            return Optional.empty();
        }).orElseThrow(() -> new NotFoundException("The product doesn't exists."));
    }

    private void getShoppingCartByUserOrCreate(int userId, ShoppingCartStock itemToAdd) {
        shoppingCartPersistence.getCartByUser(userId).ifPresentOrElse(
          shoppingCart -> {
              itemToAdd.setShoppingCartId(shoppingCart.getShoppingCartId());
              this.getProductByCartOrCreate(itemToAdd);},
          () -> this.createShoppingCart(new ShoppingCart(userId, LocalDateTime.now()))
                  .map(newCart -> {
              itemToAdd.setShoppingCartId(newCart.getShoppingCartId());
              return cartStockPersistence.addItemToCart(itemToAdd);
          })
        );
    }


    private Optional<ShoppingCart> createShoppingCart(ShoppingCart newCart) {
        return shoppingCartPersistence.createCart(newCart);
    }

    private void getProductByCartOrCreate(ShoppingCartStock itemToAdd) {
            cartStockPersistence.findByStockIdAndCart(itemToAdd.getStockId(), itemToAdd.getShoppingCartId())
          .ifPresentOrElse(
            product -> this.updateQuantityToProduct(product, itemToAdd.getQuantity()),
            () -> cartStockPersistence.addItemToCart(new ShoppingCartStock(
            itemToAdd.getStockId(),
            itemToAdd.getShoppingCartId(),
            itemToAdd.getQuantity()
          )));
    }

    private void updateQuantityToProduct(ShoppingCartStock currentProduct, int newQuantity) {
        currentProduct.setQuantity(currentProduct.getQuantity() + newQuantity);
        cartStockPersistence.addItemToCart(currentProduct);
    }

    private Optional<Stock> validateExistingProduct(Integer productId) {
        return stockPersistence.getStockById(productId);
    }
}

package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IShoppingCartService;
import co.acelerati.planetexpress.domain.model.stock.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartUseCase implements IShoppingCartService {
    @Override
    public void createShoppingCart(ShoppingCart shoppingCart, int userId) {

    }
}

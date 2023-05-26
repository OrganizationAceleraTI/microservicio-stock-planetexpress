package co.acelerati.planetexpress.domain.model.stock;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShoppingCart {

    private int shoppingCartId;
    private int userId;
    private LocalDateTime lastUpdate;

    public ShoppingCart() {
    }

    public ShoppingCart(int shoppingCartId, int userId, LocalDateTime lastUpdate) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.lastUpdate = lastUpdate;
    }

    public ShoppingCart(int userId, LocalDateTime lastUpdate) {
        this.userId = userId;
        this.lastUpdate = lastUpdate;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

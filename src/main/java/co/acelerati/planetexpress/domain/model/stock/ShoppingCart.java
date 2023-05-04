package co.acelerati.planetexpress.domain.model.stock;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShoppingCart {

    private UUID shoppingCartId;
    private int userId;
    private LocalDateTime lastUpdate;

    public ShoppingCart() {
    }

    public ShoppingCart(UUID shoppingCartId, int userId, LocalDateTime lastUpdate) {
        this.shoppingCartId = shoppingCartId;
        this.userId = userId;
        this.lastUpdate = lastUpdate;
    }

    public UUID getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(UUID shoppingCartId) {
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

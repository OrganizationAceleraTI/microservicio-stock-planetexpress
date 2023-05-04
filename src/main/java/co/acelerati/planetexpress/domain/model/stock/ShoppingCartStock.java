package co.acelerati.planetexpress.domain.model.stock;

import java.util.UUID;

public class ShoppingCartStock {

    private UUID shoppingCartStockId;
    private int stockId;
    private UUID shoppingCartId;
    private int quantity;

    public ShoppingCartStock() {
    }

    public ShoppingCartStock(UUID shoppingCartStockId, int stockId, UUID shoppingCartId, int quantity) {
        this.shoppingCartStockId = shoppingCartStockId;
        this.stockId = stockId;
        this.shoppingCartId = shoppingCartId;
        this.quantity = quantity;
    }

    public UUID getShoppingCartStockId() {
        return shoppingCartStockId;
    }

    public void setShoppingCartStockId(UUID shoppingCartStockId) {
        this.shoppingCartStockId = shoppingCartStockId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public UUID getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(UUID shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

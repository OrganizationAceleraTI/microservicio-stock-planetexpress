package co.acelerati.planetexpress.domain.model.stock;

import java.util.UUID;

public class ShoppingCartStock {

    private int shoppingCartStockId;
    private int stockId;
    private int shoppingCartId;
    private int quantity;

    public ShoppingCartStock() {
    }

    public ShoppingCartStock(int stockId, int quantity) {
        this.stockId = stockId;
        this.quantity = quantity;
    }

    public ShoppingCartStock(int shoppingCartStockId, int stockId, int shoppingCartId, int quantity) {
        this.shoppingCartStockId = shoppingCartStockId;
        this.stockId = stockId;
        this.shoppingCartId = shoppingCartId;
        this.quantity = quantity;
    }

    public ShoppingCartStock(int stockId, int shoppingCartId, int quantity) {
        this.stockId = stockId;
        this.shoppingCartId = shoppingCartId;
        this.quantity = quantity;
    }

    public int getShoppingCartStockId() {
        return shoppingCartStockId;
    }

    public void setShoppingCartStockId(int shoppingCartStockId) {
        this.shoppingCartStockId = shoppingCartStockId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

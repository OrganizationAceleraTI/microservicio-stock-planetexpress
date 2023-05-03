package co.acelerati.planetexpress.domain.model;

import co.acelerati.planetexpress.domain.model.stock.Stock;

public class StockFactory {

    private int productId;
    private int quantity;
    private double currentPrice;

    public StockFactory() {
        productId = 1;
        quantity = 80;
        currentPrice = 500;
    }

    public Stock build() {
        return new Stock(productId, quantity, currentPrice);
    }

    public StockFactory withProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public StockFactory withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public StockFactory withCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }
}

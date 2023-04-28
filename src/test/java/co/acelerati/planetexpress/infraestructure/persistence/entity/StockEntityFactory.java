package co.acelerati.planetexpress.infraestructure.persistence.entity;

public class StockEntityFactory {

    private int productId;
    private int quantity;
    private double currentPrice;

    public StockEntityFactory() {
        productId = 1;
        quantity = 80;
        currentPrice = 500;
    }

    public StockEntity build() {
        return new StockEntity(productId, quantity, currentPrice);
    }

    public StockEntityFactory withProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public StockEntityFactory withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public StockEntityFactory withCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }
}

package co.acelerati.planetexpress.domain.model.stock;

public class Stock {

    private int productId;
    private int quantity;
    private double currentPrice;

    public Stock() {
    }

    public Stock(int productId, int quantity, double currentPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public Stock(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public Stock addQuantity(int quantity) {
        this.quantity += quantity;
        return duplicate();
    }

    public Stock setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
        return duplicate();
    }

    @Override
    public String toString() {
        return "Stock{" +
          "productId=" + productId +
          ", quantity=" + quantity +
          ", currentPrice=" + currentPrice +
          '}';
    }

    private Stock duplicate() {
        return new Stock(productId, quantity, currentPrice);
    }

}

package co.acelerati.planetexpress.domain.model.stock;

public class Stock {

    private int idProduct;
    private int quantity;
    private double currentPrice;

    public Stock() {
    }

    public Stock(int idProduct, int quantity, double currentPrice) {
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public int getIdProduct() {
        return idProduct;
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

    private Stock duplicate() {
        return new Stock(idProduct, quantity, currentPrice);
    }

}

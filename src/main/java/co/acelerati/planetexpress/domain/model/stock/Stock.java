package co.acelerati.planetexpress.domain.model.stock;

public class Stock {

    private String id;
    private int idProduct;
    private int quantity;
    private double currentPrice;

    public Stock() {
    }

    public Stock(String id, int idProduct, int quantity, double currentPrice) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public String getId() {
        return id;
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

}

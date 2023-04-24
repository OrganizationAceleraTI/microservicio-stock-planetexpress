package co.acelerati.planetexpress.domain.model.stock;

public class StockFactory {

    private int idProduct;
    private int quantity;
    private double currentPrice;

    public StockFactory() {
        idProduct = 1;
        quantity = 1;
        currentPrice = 1500.25;
    }

    public Stock build(){
        return new Stock(idProduct, quantity, currentPrice);
    }

    public StockFactory withAllArguments(int idProduct, int quantity, double currentPrice){
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
        return this;
    }

    public StockFactory withIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

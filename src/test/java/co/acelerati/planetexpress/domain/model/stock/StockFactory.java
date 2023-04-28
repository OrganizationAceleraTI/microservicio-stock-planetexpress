package co.acelerati.planetexpress.domain.model.stock;

import java.util.ArrayList;
import java.util.List;

public class StockFactory {

    private int idProduct;
    private int quantity;
    private double currentPrice;

    public StockFactory() {
        idProduct = 1;
        quantity = 52;
        currentPrice = 2859000.99;
    }

    public Stock build() {
        return new Stock(idProduct, quantity, currentPrice);
    }

    public List<Stock> buildList() {
        List<Stock> stocks = new ArrayList<>();
        stocks.add(new StockFactory().build());
        stocks.add(new StockFactory()
          .withIdProduct(2)
          .withQuantity(28)
          .withCurrentPrice(1087000.99)
          .build());
        stocks.add(new StockFactory()
          .withIdProduct(3)
          .withQuantity(251)
          .withCurrentPrice(69900.99)
          .build());
        return stocks;
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

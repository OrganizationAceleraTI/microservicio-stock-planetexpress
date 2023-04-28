package co.acelerati.planetexpress.infraestructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class StockEntityFactory {

    private Integer productId;

    private Integer quantity;

    private Double currentPrice;

    public StockEntityFactory() {
    }

    public StockEntity build() {
        return new StockEntity(productId, quantity, currentPrice);
    }

    public List<StockEntity> buildList() {
        List<StockEntity> stocksEntities = new ArrayList<>();

        stocksEntities.add(new StockEntityFactory()
          .withProductId(1)
          .withQuantity(52)
          .withCurrentPrice(2859000.99)
          .build());
        stocksEntities.add(new StockEntityFactory()
          .withProductId(2)
          .withQuantity(28)
          .withCurrentPrice(1087000.99)
          .build());
        stocksEntities.add(new StockEntityFactory()
          .withProductId(3)
          .withQuantity(251)
          .withCurrentPrice(69900.99)
          .build());

        return stocksEntities;
    }

    public StockEntityFactory withProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public StockEntityFactory withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public StockEntityFactory withCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }
}


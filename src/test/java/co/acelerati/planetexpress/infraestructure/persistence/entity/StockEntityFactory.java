package co.acelerati.planetexpress.infraestructure.persistence.entity;

public class StockEntityFactory {

    private Integer productId;

    private Integer quantity;

    private Double currentPrice;

    public StockEntityFactory(){ }

    public StockEntity build(){ return new StockEntity(productId, quantity, currentPrice); }

    public StockEntityFactory withAllArguments(Integer productId, Integer quantity, double currentPrice){
        this.productId = productId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
        return this;
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


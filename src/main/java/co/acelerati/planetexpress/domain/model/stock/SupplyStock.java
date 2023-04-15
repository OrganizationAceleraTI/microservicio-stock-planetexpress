package co.acelerati.planetexpress.domain.model.stock;

public class SupplyStock {

    private String id;
    private String supplyId;
    private int stockId;
    private int quantity;
    private double supplyPrice;

    public SupplyStock() {
    }

    public SupplyStock(String id, String supplyId, int stockId, int quantity, double supplyPrice) {
        this.id = id;
        this.supplyId = supplyId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
    }

    public String getId() {
        return id;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public int getStockId() {
        return stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSupplyPrice() {
        return supplyPrice;
    }
}

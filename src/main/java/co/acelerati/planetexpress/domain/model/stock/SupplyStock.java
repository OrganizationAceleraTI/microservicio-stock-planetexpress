package co.acelerati.planetexpress.domain.model.stock;

public class SupplyStock {

    private int id;
    private int supplyId;
    private int stockId;
    private int quantity;
    private double supplyPrice;

    public SupplyStock() {
    }

    public SupplyStock(int supplyId, int stockId, int quantity, double supplyPrice) {
        this.supplyId = supplyId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
    }
    public SupplyStock(int id, int supplyId, int stockId, int quantity, double supplyPrice) {
        this.id = id;
        this.supplyId = supplyId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.supplyPrice = supplyPrice;
    }

    public int getId() {
        return id;
    }

    public int getSupplyId() {
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

    @Override
    public String toString() {
        return "SupplyStock{" +
          "id='" + id + '\'' +
          ", supplyId='" + supplyId + '\'' +
          ", stockId=" + stockId +
          ", quantity=" + quantity +
          ", supplyPrice=" + supplyPrice +
          '}';
    }
}

package co.acelerati.planetexpress.domain.model;

public class Inventory {

    private Integer inventoryId;
    private Integer productId;
    private Integer personSupplierId;
    private Integer incomingPrice;
    private Integer currentPrice;
    private Integer quantity;

    public Inventory() {
    }

    public Inventory(Integer inventoryId, Integer productId, Integer personSupplierId, Integer incomingPrice, Integer currentPrice, Integer quantity) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.personSupplierId = personSupplierId;
        this.incomingPrice = incomingPrice;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
    }

    public Inventory(Integer productId, Integer personSupplierId, Integer incomingPrice, Integer currentPrice, Integer quantity) {
        this.productId = productId;
        this.personSupplierId = personSupplierId;
        this.incomingPrice = incomingPrice;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
    }

    public Inventory(Integer productId, Integer personSupplierId, Integer quantity) {
        this.productId = productId;
        this.personSupplierId = personSupplierId;
        this.incomingPrice = 0;
        this.currentPrice = 0;
        this.quantity = quantity;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getPersonSupplierId() {
        return personSupplierId;
    }

    public Integer getIncomingPrice() {
        return incomingPrice;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

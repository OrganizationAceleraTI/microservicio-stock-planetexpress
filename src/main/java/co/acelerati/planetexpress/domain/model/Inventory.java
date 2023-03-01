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

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPersonSupplierId() {
        return personSupplierId;
    }

    public void setPersonSupplierId(Integer personSupplierId) {
        this.personSupplierId = personSupplierId;
    }

    public Integer getIncomingPrice() {
        return incomingPrice;
    }

    public void setIncomingPrice(Integer incomingPrice) {
        this.incomingPrice = incomingPrice;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

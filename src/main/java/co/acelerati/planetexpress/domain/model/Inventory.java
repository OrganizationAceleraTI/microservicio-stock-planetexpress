package co.acelerati.planetexpress.domain.model;

public class Inventory { // TODO delete if it is not used

    private Integer inventoryId;
    private Integer personSupplierId;
    private Integer productId;
    private Integer incomingPrice;
    private Integer currentPrice;
    private Integer quantity;

    public Inventory() {
    }

    public Inventory(Integer inventoryId, Integer productId, Integer personSupplierId, Integer incomingPrice,
                     Integer currentPrice, Integer quantity) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.personSupplierId = personSupplierId;
        this.incomingPrice = incomingPrice;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
    }

    public Inventory(Integer productId, Integer personSupplierId, Integer incomingPrice, Integer currentPrice,
                     Integer quantity) {
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

    public Inventory(Integer inventoryId, Integer currentPrice) {
        this.inventoryId = inventoryId;
        this.currentPrice = currentPrice;
    }

    public Inventory(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getPersonSupplierId() {
        return personSupplierId;
    }

    public void setPersonSupplierId(Integer personSupplierId) {
        this.personSupplierId = personSupplierId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Inventory withCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

package co.acelerati.planetexpress.domain.model.stock;

public class ProductSale {

    private Long id;
    private String name;
    private String description;
    private Double currentPrice;
    private int quantity;
    private String brandName;
    private String categoryName;

    public ProductSale(Long id, String name, String description, Double currentPrice, int quantity, String brandName, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
        this.quantity = quantity;
        this.brandName = brandName;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

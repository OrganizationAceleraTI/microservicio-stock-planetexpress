package co.acelerati.planetexpress.domain.model;

public class Product {

    private Integer productId;
    private Integer brandId;
    private Integer categoryId;
    private String productName;
    private String description;
    private String model;

    public Product(Integer productId, Integer brandId, Integer categoryId, String productName,
                   String description, String model) {
        this.productId = productId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.model = model;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

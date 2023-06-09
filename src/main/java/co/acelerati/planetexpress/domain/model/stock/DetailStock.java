package co.acelerati.planetexpress.domain.model.stock;

public class DetailStock {
    private Long id;
    private String name;
    private String description;
    private String model;
    private String brand;
    private String category;
    private int quantity;
    private double currentPrice;

    public DetailStock(Long id, String name, String description, String model, String brand, String category, int quantity, double currentPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.model = model;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
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

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}

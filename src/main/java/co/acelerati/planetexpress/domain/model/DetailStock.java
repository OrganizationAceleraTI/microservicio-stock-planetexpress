package co.acelerati.planetexpress.domain.model;

public class DetailStock { // TODO delete if it is not used
    private Long id;
    private String name;
    private String description;
    private String model;
    private String brand;
    private String category;
    private int quantity;
    private int incomingPrice;
    private int currentPrice;

    public DetailStock(Long id, String name, String description, String model, String brand, String category, int quantity, int incomingPrice, int currentPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.model = model;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.incomingPrice = incomingPrice;
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

    public int getIncomingPrice() {
        return incomingPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }
}

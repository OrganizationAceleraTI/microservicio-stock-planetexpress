package co.acelerati.planetexpress.domain.model;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.StockFactory;

public class DetailStockFactory {

    private Long id;
    private String name;
    private String description;
    private String model;
    private String brand;
    private String category;
    private int quantity;
    private double currentPrice;

    public DetailStockFactory() {
        id = Long.getLong("1");
        name = "Televisor";
        description = "Televisor pantalla plana de 52 pulgadas 4K";
        model = "2023";
        brand = "Samsung";
        category = "Electrodomesticos";
        quantity = 5;
        currentPrice = 1800000.00;
    }

    public DetailStock build(){
        return new DetailStock(id, name, description, model, brand, category, quantity, currentPrice);
    }

    public DetailStockFactory withAllArguments(Long id, String name, String description, String model, String brand,
                                               String category, int quantity, double currentPrice){
        this.id = id;
        this.name = name;
        this.description = description;
        this.model = model;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
        return this;
    }

    public DetailStockFactory withId(Long id) {
        this.id = id;
        return this;
    }

    public DetailStockFactory withName(String name) {
        this.name = name;
        return this;
    }

    public DetailStockFactory withDescription(String description) {
        this.description = description;
        return this;
    }

    public DetailStockFactory withModel(String model) {
        this.model = model;
        return this;
    }

    public DetailStockFactory withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public DetailStockFactory withCategory(String category) {
        this.category = category;
        return this;
    }

    public DetailStockFactory withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public DetailStockFactory withCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }
}

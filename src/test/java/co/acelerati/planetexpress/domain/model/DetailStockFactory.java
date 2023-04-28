package co.acelerati.planetexpress.domain.model;

import co.acelerati.planetexpress.domain.model.stock.DetailStock;

import java.util.ArrayList;
import java.util.List;

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
        id = 0L;
        name = "Televisor";
        description = "Televisor pantalla plana de 52 pulgadas 4K";
        model = "2023";
        brand = "Samsung";
        category = "Electrodomesticos";
        quantity = 5;
        currentPrice = 1800000.00;
    }

    public DetailStock build() {
        return new DetailStock(id, name, description, model, brand, category, quantity, currentPrice);
    }

    public List<DetailStock> buildList() {
        List<DetailStock> detailStockList = new ArrayList<>();
        detailStockList.add(new DetailStockFactory()
          .withId(1L)
          .withName("Iphone 14 Pro Max")
          .withDescription("Teléfono célular apple")
          .withModel("PHOMX198974")
          .withBrand("Apple")
          .withCategory("Electronica")
          .withQuantity(52)
          .withCurrentPrice(2859000.99)
          .build());
        detailStockList.add(new DetailStockFactory()
          .withId(2L)
          .withName("Lavadora LG Carga Superior 19kg Negro")
          .withDescription("Lavadora LG Smart Inverter con 10 años de garantía en su motor, tiene un rendimiento de " +
            "lavado superior, más limpio, más higiénico, El Motor Smart Inverter es confiable, silencioso y duradero")
          .withModel("WT19BSB")
          .withBrand("Lg")
          .withCategory("Electrodomesticos")
          .withQuantity(28)
          .withCurrentPrice(1087000.99)
          .build());
        detailStockList.add(new DetailStockFactory()
          .withId(3L)
          .withName("Cargador Pared SAMSUNG USB-C 25W Carga Rápida")
          .withDescription("Samsung llego con el nuevo cargador rápido de pared que permite cargar todos tus " +
            "dispositivos a una velocidad increible para que puedas disfrutar de todo tu contenido con la mayor " +
            "energia.")
          .withModel("CP013681")
          .withBrand("Samsung")
          .withCategory("Accesorios")
          .withQuantity(251)
          .withCurrentPrice(69900.99)
          .build());

        return detailStockList;
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

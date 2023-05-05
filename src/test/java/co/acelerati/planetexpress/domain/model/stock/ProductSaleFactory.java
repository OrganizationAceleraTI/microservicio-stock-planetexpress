package co.acelerati.planetexpress.domain.model.stock;

import java.util.ArrayList;
import java.util.List;

public class ProductSaleFactory {

    private Long id;
    private String name;
    private String description;
    private Double currentPrice;
    private int quantity;
    private String brandName;
    private String categoryName;

    public ProductSaleFactory() {
        id = 1L;
        name = "Iphone 14 Pro Max";
        description = "Teléfono célular apple";
        quantity = 52;
        currentPrice = 2859000.99;
        brandName = "Apple";
        categoryName = "Electronica";
    }

    public ProductSale build() {
        return new ProductSale(id, name, description, currentPrice, quantity, brandName, categoryName);
    }

    public List<ProductSale> buildList() {
        List<ProductSale> productSales = new ArrayList<>();
        productSales.add(new ProductSaleFactory().build());
        productSales.add(new ProductSaleFactory()
          .withId(2L)
          .withName("Lavadora LG Carga Superior 19kg Negro")
          .withDescription("Lavadora LG Smart Inverter con 10 años de garantía en su motor, tiene un rendimiento de " +
            "lavado superior, más limpio, más higiénico, El Motor Smart Inverter es confiable, silencioso y duradero")
          .withQuantity(28)
          .withCurrentPrice(1087000.99)
          .withBrandName("Lg")
          .withCategoryName("Electrodomesticos")
          .build());
        productSales.add(new ProductSaleFactory()
          .withId(3L)
          .withName("Cargador Pared SAMSUNG USB-C 25W Carga Rápida")
          .withDescription("Samsung llego con el nuevo cargador rápido de pared que permite cargar todos tus " +
            "dispositivos a una velocidad increible para que puedas disfrutar de todo tu contenido con la mayor " +
            "energia.")
          .withQuantity(251)
          .withCurrentPrice(69900.99)
          .withBrandName("Samsung")
          .withCategoryName("Accesorios")
          .build());
        return productSales;
    }

    public ProductSaleFactory withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductSaleFactory withName(String name) {
        this.name = name;
        return this;
    }

    public ProductSaleFactory withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductSaleFactory withCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public ProductSaleFactory withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductSaleFactory withBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public ProductSaleFactory withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
}

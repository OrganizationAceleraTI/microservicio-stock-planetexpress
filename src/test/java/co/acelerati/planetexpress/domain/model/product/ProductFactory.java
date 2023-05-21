package co.acelerati.planetexpress.domain.model.product;

import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

    private Long id;
    private String name;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;

    public ProductFactory() {
        this.id = 1L;
        this.name = "Iphone 14 Pro Max";
        this.description = "Teléfono célular apple";
        this.model = "PHOMX198974";
        this.idBrand = 10L;
        this.idCategory = 20L;
    }

    public Product build() {
        return new Product(id, name, description, model, idBrand, idCategory);
    }

    public List<Product> buildList() {
        List<Product> products = new ArrayList<>();
        products.add(new ProductFactory().build());
        products.add(new ProductFactory().
          withId(2L)
          .withName("Lavadora LG Carga Superior 19kg Negro")
          .withDescription("Lavadora LG Smart Inverter con 10 años de garantía en su motor, tiene un rendimiento " +
            "de lavado superior, más limpio, más higiénico, El Motor Smart Inverter es confiable, silencioso y " +
            "duradero")
          .withModel("WT19BSB")
          .withIdBrand(11L)
          .withIdCategory(21L)
          .build());


        products.add(new ProductFactory()
          .withId(3L)
          .withName("Cargador Pared SAMSUNG USB-C 25W Carga Rápida")
          .withDescription("Samsung llego con el nuevo cargador rápido de pared que permite cargar todos tus " +
            "dispositivos a una velocidad increible para que puedas disfrutar de todo tu contenido con la mayor " +
            "energia.")
          .withModel("CP013681")
          .withIdBrand(12L)
          .withIdCategory(22L)
          .build());

        return products;
    }

    public ProductFactory withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductFactory withName(String name) {
        this.name = name;
        return this;
    }

    public ProductFactory withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductFactory withModel(String model) {
        this.model = model;
        return this;
    }

    public ProductFactory withIdBrand(Long idBrand) {
        this.idBrand = idBrand;
        return this;
    }

    public ProductFactory withIdCategory(Long idCategory) {
        this.idCategory = idCategory;
        return this;
    }

}

package co.acelerati.planetexpress.domain.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;

    public Product(Long id, String name, String description, String model, Long idBrand, Long idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.model = model;
        this.idBrand = idBrand;
        this.idCategory = idCategory;
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

    public Long getIdBrand() {
        return idBrand;
    }

    public Long getIdCategory() {
        return idCategory;
    }
}

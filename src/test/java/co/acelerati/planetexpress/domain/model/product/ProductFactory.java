package co.acelerati.planetexpress.domain.model.product;

public class ProductFactory {

    private Long id;
    private String name;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;

    public ProductFactory(){ }

    public Product build(){
        return new Product(id, name, description, model, idBrand, idCategory);
    }

    public ProductFactory withAllArguments(Long id, String name, String description, String model, Long idBrand, Long idCategory){
        this.id = id;
        this.name = name;
        this.description = description;
        this.model = model;
        this.idBrand = idBrand;
        this.idCategory = idCategory;
        return this;
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

    public ProductFactory withModel(String name) {
        this.name = name;
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

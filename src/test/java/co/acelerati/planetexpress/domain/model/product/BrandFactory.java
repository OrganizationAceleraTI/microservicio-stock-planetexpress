package co.acelerati.planetexpress.domain.model.product;

public class BrandFactory {

    private Long id;
    private String name;

    public BrandFactory(){ }

    public Brand build(){
        return new Brand(id, name);
    }

    public BrandFactory withAllArguments(Long id, String name){
        this.id = id;
        this.name = name;
        return this;
    }

    public BrandFactory withId(Long id) {
        this.id = id;
        return this;
    }

    public BrandFactory withName(String name) {
        this.name = name;
        return this;
    }
}

package co.acelerati.planetexpress.domain.model.product;

public class CategoryFactory {

    private Long id;
    private String name;

    public CategoryFactory(){ }

    public Category build(){
        return new Category(id, name);
    }

    public CategoryFactory withAllArguments(Long id, String name){
        this.id = id;
        this.name = name;
        return this;
    }

    public CategoryFactory withId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryFactory withName(String name) {
        this.name = name;
        return this;
    }
}

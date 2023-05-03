package co.acelerati.planetexpress.domain.model.product;

public class Brand {
    private Long id;
    private String name;

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

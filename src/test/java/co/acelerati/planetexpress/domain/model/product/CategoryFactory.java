package co.acelerati.planetexpress.domain.model.product;

import java.util.ArrayList;
import java.util.List;

public class CategoryFactory {

    private Long id;
    private String name;

    public CategoryFactory() {
        this.id = 20L;
        this.name = "Electronica";
    }

    public Category build() {
        return new Category(id, name);
    }

    public List<Category> buildList() {
        List<Category> categories = new ArrayList<>();
        categories.add(new CategoryFactory().build());
        categories.add(new CategoryFactory()
          .withId(21L)
          .withName("Electrodomesticos")
          .build());
        categories.add(new CategoryFactory()
          .withId(22L)
          .withName("Accesorios")
          .build());
        return categories;
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

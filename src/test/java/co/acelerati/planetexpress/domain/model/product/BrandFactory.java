package co.acelerati.planetexpress.domain.model.product;

import java.util.ArrayList;
import java.util.List;

public class BrandFactory {

    private Long id;
    private String name;

    public BrandFactory() {
        this.id = 10L;
        this.name = "Apple";
    }

    public Brand build() {
        return new Brand(id, name);
    }

    public List<Brand> buildList() {
        List<Brand> brands = new ArrayList<>();
        brands.add(new BrandFactory().build());
        brands.add(new BrandFactory()
          .withId(11L)
          .withName("Lg")
          .build());
        brands.add(new BrandFactory()
          .withId(12L)
          .withName("Samsung")
          .build());
        return brands;
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

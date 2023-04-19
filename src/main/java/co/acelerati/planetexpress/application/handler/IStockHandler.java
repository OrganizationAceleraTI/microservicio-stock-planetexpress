package co.acelerati.planetexpress.application.handler;

import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface IStockHandler {

    List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands);
    Stock updateStock(Integer stockId, Stock updateStock);

}

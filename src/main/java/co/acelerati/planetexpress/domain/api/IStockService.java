package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import co.acelerati.planetexpress.domain.model.stock.ProductSale;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

public interface IStockService {

    boolean supplyStock(List<Stock> productList, int idSupplier);

    boolean setCurrentPriceToStock(int productId, double currentPrice);

    List<DetailStock> getAllProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brand);

    Stock updateStock(Integer newSalePrice, Integer stockId);

    Optional<Stock> getStockById(Integer stockId);

    List<ProductSale> getProductsSale(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands);

}

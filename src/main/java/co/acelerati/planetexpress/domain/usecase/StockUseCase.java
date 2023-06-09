package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import co.acelerati.planetexpress.domain.model.stock.ProductSale;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StockUseCase implements IStockService {

    private final IStockPersistence stockPersistence;
    private final ISupplyPersistence supplyPersistence;
    private final ISupplyStockPersistence supplyStockPersistence;

    @Override
    public boolean supplyStock(List<Stock> productList, int idSupplier) {
        productList.forEach(product -> supplyStock(product, idSupplier));
        return true;
    }

    @Override
    public boolean setCurrentPriceToStock(int productId, double currentPrice) throws NotFoundException {
        return stockPersistence.getById(productId).map(stockOpt -> {
              stockOpt.setCurrentPrice(currentPrice);
              return stockPersistence.insertStock(stockOpt).isPresent();
          }
        ).orElseThrow();
    }

    @Override
    public List<DetailStock> getAllProducts(MultiValueMap<String, String> filters, List<Product> products
      ,List<Category> categories, List<Brand> brands) {

        final double minPrice = filters.containsKey("minPrice") ? Double.parseDouble(filters.getFirst("minPrice")): -1;
        final double maxPrice = filters.containsKey("maxPrice") ? Double.parseDouble(filters.getFirst("maxPrice")) : -1;
        final double price = filters.containsKey("price") ? Double.parseDouble(filters.getFirst("price")) : -1;
        final Long categoryId = filters.containsKey("category") ? Long.parseLong(filters.getFirst("category")) : 0L;
        final Long brandId = filters.containsKey("brand") ? Long.parseLong(filters.getFirst("brand")) : 0L;
        final int sizePage = filters.containsKey("sizePage") ? Integer.parseInt(filters.getFirst("sizePage")) : 0;
        final int page = filters.containsKey("page") ? Integer.parseInt(filters.getFirst("page")) : 0;
        final boolean filterComplement = categoryId > 0L || brandId > 0L;
        final Stream<Product> productStream = products.stream();
        List<Stock> stockList;
        List<Product> productsFilter;

        if(categoryId > 0L && brandId > 0L){
            productsFilter = productStream.filter(productFilter -> productFilter.getIdCategory() == categoryId
              && productFilter.getIdBrand() == brandId).collect(Collectors.toList());
        }else if(categoryId > 0L){
            productsFilter = productStream.filter(productFilter -> productFilter.getIdCategory() == categoryId)
              .collect(Collectors.toList());
        }else if(brandId > 0L){
            productsFilter = productStream.filter(productFilter -> productFilter.getIdBrand() == brandId)
              .collect(Collectors.toList());
        }else{
            productsFilter = productStream.collect(Collectors.toList());
        }

        List<Integer> porductIdFilterList = new ArrayList<>();

        if(filterComplement){
            porductIdFilterList = productsFilter.stream()
              .map(stock -> Integer.valueOf(stock.getId().intValue()))
              .collect(Collectors.toList());
        }

        if (price >= 0) {
            stockList = !filterComplement
              ? stockPersistence.getByCurrentPrice(price, page, sizePage)
              : stockPersistence.getByProductIdInAndCurrentPrice(porductIdFilterList, price, page, sizePage);
        } else if (minPrice >= 0 && maxPrice >= 0) {
            stockList = !filterComplement
              ? stockPersistence.getByCurrentPriceBetween(minPrice, maxPrice, page, sizePage)
              : stockPersistence.getByProductIdInAndCurrentPriceBetween(porductIdFilterList, minPrice, maxPrice, page, sizePage);
        } else if (minPrice >= 0) {
            stockList = !filterComplement
              ? stockPersistence.getByCurrentPriceGreaterThanEqual(minPrice, page, sizePage)
              : stockPersistence.getByProductIdInAndCurrentPriceGreaterThanEqual(porductIdFilterList, minPrice, page, sizePage);
        } else if (maxPrice >= 0) {
            stockList = !filterComplement
              ? stockPersistence.getByCurrentPriceLessThanEqual(maxPrice, page, sizePage)
              : stockPersistence.getByProductIdInAndCurrentPriceLessThanEqual(porductIdFilterList, maxPrice, page, sizePage);
        } else {
            stockList = stockPersistence.getAll(page, sizePage);
        }

        return stockList.stream()
          .map(stock -> buildDetailStock(stock, productsFilter, categories, brands))
          .collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public Stock updateStock(Integer newSalePrice, Integer stockId) {
        return stockPersistence.updateStock(this.getStockById(stockId).map(stock ->
          stock.setCurrentPrice(newSalePrice)).orElseThrow());
    }

    @Override
    public Optional<Stock> getStockById(Integer stockId) {
        return stockPersistence.getStockById(stockId);
    }

    @Override
    public List<ProductSale> getProductsSale(MultiValueMap<String, String> filters, List<Product> products,
                                             List<Category> categories, List<Brand> brands) {

        final String nameProduct = filters.containsKey("productName") ? filters.getFirst("productName"): "";
        final String nameBrand = filters.containsKey("brandName") ? filters.getFirst("brandName"): "";
        final String nameCategory = filters.containsKey("categoryName") ? filters.getFirst("categoryName"): "";
        final int sizePage = filters.containsKey("sizePage") ? Integer.parseInt(filters.getFirst("sizePage")) : 0;
        final int page = filters.containsKey("page") ? Integer.parseInt(filters.getFirst("page")) : 0;
        List<Stock> stockList;
        List<ProductSale> productSaleList = buildProductSaleList(products, categories, brands);
        List<ProductSale> productSaleListFilter = (nameProduct.isBlank() && nameBrand.isBlank() && nameCategory.isBlank())
                                                  ? new ArrayList<>(productSaleList)
                                                  : new ArrayList<>();

        if(!nameProduct.isBlank()){
            productSaleListFilter =
              Stream.of(productSaleList.stream()).findAny().get()
                .filter(nameFilter -> nameFilter.getName().toUpperCase().equals(nameProduct.toUpperCase()))
                .collect(Collectors.toList());
            productSaleList = new ArrayList<>(productSaleListFilter);

        }

        if(!nameBrand.isBlank()){
            productSaleListFilter =
              Stream.of(productSaleList.stream()).findAny().get()
                .filter(brandFilter -> brandFilter.getBrandName().toUpperCase().equals(nameBrand.toUpperCase()))
                .collect(Collectors.toList());
            productSaleList = new ArrayList<>(productSaleListFilter);
        }

        if(!nameCategory.isBlank()){
            productSaleListFilter =
              Stream.of(productSaleList.stream()).findAny().get()
                .filter(categoryFilter -> categoryFilter.getCategoryName().toUpperCase().equals(nameCategory.toUpperCase()))
                .collect(Collectors.toList());
        }

        List<Integer> idProducts = productSaleListFilter.stream()
          .map(stock -> Integer.valueOf(stock.getId().intValue()))
          .collect(Collectors.toList());

        stockList = stockPersistence
          .getByProductIdInAndCurrentPriceGreaterThanAndQuantityGreaterThan(
            idProducts,
            0,
            0,
            page,
            sizePage);

       return productSaleListFilter.stream()
          .filter(
            stockIsPresent -> stockList.stream()
            .filter(value -> value.getProductId() == stockIsPresent.getId().intValue()).findFirst().isPresent())
          .map(productSale -> {
            Stock stock = stockList.stream()
              .filter(value -> value.getProductId() == productSale.getId().intValue()).findFirst().get();
            productSale.setCurrentPrice(stock.getCurrentPrice());
            productSale.setQuantity(stock.getQuantity());
              return productSale;
          }).collect(Collectors.toList());

    }

    private void supplyStock(Stock stock, int idSupplier) {
        stockPersistence.getById(stock.getProductId())
          .map(stockOpt -> updateQuantity(stockOpt, stock.getQuantity(), idSupplier).isPresent())
          .orElseGet(() -> registerStock(stock, stock.getCurrentPrice(), idSupplier).isPresent());
    }

    private Optional<SupplyStock> updateQuantity(Stock stock, int quantity, int idSupplier) {
        stock.addQuantity(quantity);
        return registerStock(stock, stock.getCurrentPrice(), idSupplier);
    }

    private Optional<SupplyStock> registerStock(Stock stock, double currentPrice, int idSupplier) {
        stock.setCurrentPrice(currentPrice);
        return stockPersistence.insertStock(stock)
          .flatMap(stockUpdated -> supplyPersistence.insertSupply(
            new Supply(
              idSupplier,
              LocalDateTime.now()
            )).flatMap(supplyOpt -> supplyStockPersistence.insertSupplyStock(
            new SupplyStock(
              supplyOpt.getId(),
              stockUpdated.getProductId(),
              stock.getQuantity(), // TODO Validate
              stock.getCurrentPrice() // TODO Validate
            )))
          );
    }

    private DetailStock buildDetailStock(Stock stock, List<Product> products, List<Category> categories, List<Brand> brands){
        Product product = products.stream()
          .filter(prd -> stock.getProductId() == (prd.getId().intValue())).findFirst().get();
        Category category = categories.stream()
          .filter(cat -> product.getIdCategory().equals(cat.getId())).findFirst().get();
        Brand brand = brands.stream()
          .filter(bra -> product.getIdBrand().equals(bra.getId())).findFirst().get();

        return new DetailStock(
          product.getId(),
          product.getName(),
          product.getDescription(),
          product.getModel(),
          brand.getName(),
          category.getName(),
          stock.getQuantity(),
          stock.getCurrentPrice()
        );
    }

    private List<ProductSale> buildProductSaleList(List<Product> products,
                                                   List<Category> categories,
                                                   List<Brand> brands){
        return products.stream().map(product -> new ProductSale(
          product.getId(),
          product.getName(),
          product.getDescription(),
          0.0,
          0,
          brands.stream()
            .filter(bra -> product.getIdBrand().equals(bra.getId())).findFirst().get().getName(),
          categories.stream()
            .filter(cat -> product.getIdCategory().equals(cat.getId())).findFirst().get().getName()
        )).collect(Collectors.toList());
    }

}

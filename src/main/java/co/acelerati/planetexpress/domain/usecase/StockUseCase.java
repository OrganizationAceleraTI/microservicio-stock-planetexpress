package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
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

@Service
@RequiredArgsConstructor
public class StockUseCase implements IStockService {

    private static final double ZERO = 0;

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
    public List<DetailStock> getAllProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands) {
        List<Stock> stockList;
        List<DetailStock> detailStockList = new ArrayList<>();
        double minPrice = filters.containsKey("minPrice") ? Double.parseDouble(filters.getFirst("minPrice")): -1;
        double maxPrice = filters.containsKey("maxPrice") ? Double.parseDouble(filters.getFirst("maxPrice")) : -1;
        int page = Integer.parseInt(filters.getFirst("page"));

        if (minPrice >= 0 && maxPrice >= 0) {
            stockList = stockPersistence.getByCurrentPriceBetween(minPrice, maxPrice, page);
        }
        else if (minPrice >= 0) {
            stockList = stockPersistence.getByCurrentPriceLessThanEqual(minPrice, page);
        }
        else if (maxPrice >= 0) {
            stockList = stockPersistence.getByCurrentPriceGreaterThanEqual(maxPrice, page);
        } else {
            stockList = stockPersistence.getAll(page);
        }

        return stockList.stream().map( stock -> buildDetailStock(stock,products, categories, brands))
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

    private void supplyStock(Stock stock, int idSupplier) {
        stockPersistence.getById(stock.getIdProduct())
          .map(stockOpt -> updateQuantity(stockOpt, stock.getQuantity(), idSupplier).isPresent())
          .orElseGet(() -> registerStock(stock, ZERO, idSupplier).isPresent());
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
              UUID.randomUUID().toString(),
              idSupplier,
              LocalDateTime.now()
            )).flatMap(supplyOpt -> supplyStockPersistence.insertSupplyStock(
            new SupplyStock(
              UUID.randomUUID().toString(),
              supplyOpt.getId(),
              stockUpdated.getIdProduct(),
              stock.getQuantity(),
              stock.getCurrentPrice()
            )))
          );
    }

    private DetailStock buildDetailStock(Stock stock, List<Product> products, List<Category> categories, List<Brand> brands){
        Product product = products.stream()
          .filter(prd -> stock.getIdProduct() == (prd.getId().intValue())).findFirst().get();
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

}

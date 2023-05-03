package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockPersistence {

    Optional<Stock> insertStock(Stock stock);

    Optional<Stock> getById(int productId);

    boolean updateStockPrice(Stock stock);

    List<Stock> getAll(int page, int pageSize);

    List<Stock> getByCurrentPrice(double currentPrice, int page, int pageSize);

    List<Stock> getByCurrentPriceLessThanEqual(double currentPrice, int page, int pageSize);

    List<Stock> getByCurrentPriceGreaterThanEqual(double currentPrice, int page, int pageSize);

    List<Stock> getByCurrentPriceBetween(double minPrice, double maxPrice, int page, int pageSize);

    List<Stock> getByProductIdInAndCurrentPrice(List<Integer> productId, double currentPrice, int page, int pageSize);

    List<Stock> getByProductIdInAndCurrentPriceLessThanEqual(List<Integer> productId, double currentPrice, int page, int pageSize);

    List<Stock> getByProductIdInAndCurrentPriceGreaterThanEqual(List<Integer> productId, double currentPrice, int page, int pageSize);

    List<Stock> getByProductIdInAndCurrentPriceBetween(List<Integer> productId, double minPrice, double maxPrice, int page, int pageSize);

    Stock updateStock(Stock inventory);

    Optional<Stock> getStockById(Integer stockId);

}

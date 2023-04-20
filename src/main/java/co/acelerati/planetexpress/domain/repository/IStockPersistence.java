package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockPersistence {

    Optional<Stock> insertStock(Stock stock);

    Optional<Stock> getById(int productId);

    boolean updateStockPrice(Stock stock);

    List<Stock> getAll(int page);

    List<Stock> getByCurrentPrice(double currentPrice, int page);

    List<Stock> getByCurrentPriceLessThanEqual(double currentPrice, int page);

    List<Stock> getByCurrentPriceGreaterThanEqual(double currentPrice, int page);

    List<Stock> getByCurrentPriceBetween(double minPrice, double maxPrice, int page);

}

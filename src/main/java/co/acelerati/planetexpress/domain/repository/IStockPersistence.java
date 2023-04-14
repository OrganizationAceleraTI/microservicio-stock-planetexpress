package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.stock.Stock;

import java.util.List;

public interface IStockPersistence {

    boolean insertStock(Stock stock);

    Stock getById(int productId);

    boolean updateStockPrice(Stock stock);

    List<Stock> getAll(int page, int pageSize);

    List<Stock> getByCurrentPrice(int currentPrice, int page, int pageSize);

    List<Stock> getByCurrentPriceLessThanEqual(int currentPrice, int page, int pageSize);

    List<Stock> getByCurrentPriceGreaterThanEqual(int currentPrice, int page, int pageSize);

    List<Stock> getByCurrentPriceBetween(int minPrice, int maxPrice, int page, int pageSize);

}

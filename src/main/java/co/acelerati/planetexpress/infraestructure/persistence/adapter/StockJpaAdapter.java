package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.StockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class StockJpaAdapter implements IStockPersistence {

    private final IStockRepository repository;

    @Override
    public Optional<Stock> insertStock(Stock stock) {
        return Optional.of(StockMapper.toDomain(repository.save(StockMapper.toEntity(stock))));
    }

    @Override
    public Optional<Stock> getById(int productId) throws NotFoundException {
        return repository.findByProductId(productId)
          .map(StockMapper::toDomain);
    }

    @Override
    public boolean updateStockPrice(Stock stock) {
        return false;
    }

    @Override
    public List<Stock> getAll(int page, int pageSize) {
        return null;
    }

    @Override
    public List<Stock> getByCurrentPrice(int currentPrice, int page, int pageSize) {
        return null;
    }

    @Override
    public List<Stock> getByCurrentPriceLessThanEqual(int currentPrice, int page, int pageSize) {
        return null;
    }

    @Override
    public List<Stock> getByCurrentPriceGreaterThanEqual(int currentPrice, int page, int pageSize) {
        return null;
    }

    @Override
    public List<Stock> getByCurrentPriceBetween(int minPrice, int maxPrice, int page, int pageSize) {
        return null;
    }
}

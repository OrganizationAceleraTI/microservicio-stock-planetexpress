package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.exception.NotFoundException;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.StockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StockJpaAdapter implements IStockPersistence {

    private final IStockRepository repository;

    private final int SIZE_PAGE = 25;

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
    public List<Stock> getAll(int page) {
        return repository.findAll(PageRequest.of(page, SIZE_PAGE))
          .map(StockMapper::toDomain).toList();
    }

    @Override
    public List<Stock> getByCurrentPrice(double currentPrice, int page) {
        return repository.findByCurrentPrice(currentPrice, PageRequest.of(page, SIZE_PAGE))
                .map(pages -> pages.map(StockMapper::toDomain)).get().toList();
    }

    @Override
    public List<Stock> getByCurrentPriceLessThanEqual(double currentPrice, int page) {
        return repository.findByCurrentPriceLessThanEqual(currentPrice, PageRequest.of(page, SIZE_PAGE))
                     .map(pages -> pages.map(StockMapper::toDomain)).get().toList();
    }

    @Override
    public List<Stock> getByCurrentPriceGreaterThanEqual(double currentPrice, int page) {
        return repository.findByCurrentPriceGreaterThanEqual(currentPrice, PageRequest.of(page, SIZE_PAGE))
                     .map(pages -> pages.map(StockMapper::toDomain)).get().toList();
    }

    @Override
    public List<Stock> getByCurrentPriceBetween(double minPrice, double maxPrice, int page) {
        return repository.findByCurrentPriceBetween(minPrice, maxPrice, PageRequest.of(page, SIZE_PAGE))
                     .map(pages -> pages.map(StockMapper::toDomain)).get().toList();
    }

    @Override
    public Stock updateStock(Stock stock) {
        StockEntity stockEntity = StockMapper.toEntity(stock);
        return StockMapper.toDomain(repository.save(stockEntity));
    }

    @Override
    public Optional<Stock> getStockById(Integer stockId) {
        return StockMapper.toModelOptional(repository.findById(stockId));
    }

}

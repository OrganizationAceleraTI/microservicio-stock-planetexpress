package co.acelerati.planetexpress.infraestructure.persistence.mapper;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;

import java.util.Optional;

public final class StockMapper {

    private StockMapper() {
    }

    public static Stock toDomain(StockEntity entity) {
        return new Stock(
          entity.getProductId(),
          entity.getQuantity(),
          entity.getCurrentPrice()
        );
    }

    public static StockEntity toEntity(Stock stock) {
        return new StockEntity(
          stock.getProductId(),
          stock.getQuantity(),
          stock.getCurrentPrice()
        );
    }

    public static Optional<Stock> toModelOptional(Optional<StockEntity> stockEntity) {
        return stockEntity.map(stock ->
          new Stock(stock.getProductId(),
            stock.getQuantity(),
            stock.getCurrentPrice()));
    }

}

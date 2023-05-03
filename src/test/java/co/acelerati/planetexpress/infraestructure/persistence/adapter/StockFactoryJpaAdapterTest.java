package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.StockFactory;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntityFactory;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockFactoryJpaAdapterTest {

    private IStockRepository stockRepository;
    private StockJpaAdapter stockJpaAdapter;

    @BeforeEach
    void setUp() {
        stockRepository = mock(IStockRepository.class);
        stockJpaAdapter = new StockJpaAdapter(stockRepository);
    }

    @Test
    void whenGetStockByIdIsCalled_ThenReturnStock() {
        Optional<StockEntity> stockEntity = Optional.of(new StockEntityFactory().build());
        when(stockRepository.findById(anyInt())).thenReturn(stockEntity);

        Optional<Stock> response = stockJpaAdapter.getStockById(1);

        assertEquals(stockEntity.get().getProductId(), response.get().getProductId());
    }

    @Test
    void whenUpdateStockIsCalled_ThenReturnStock() {
        Stock stock = new StockFactory().build();
        StockEntity stockEntity = new StockEntityFactory().build();
        when(stockRepository.save(any(StockEntity.class))).thenReturn(stockEntity);

        Stock response = stockJpaAdapter.updateStock(stock);

        assertEquals(stock.getProductId(), response.getProductId());
    }
}
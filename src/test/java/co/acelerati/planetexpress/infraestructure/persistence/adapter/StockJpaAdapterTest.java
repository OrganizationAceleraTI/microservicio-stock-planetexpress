package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.StockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StockJpaAdapterTest {

    private IStockRepository stockRepository;
    private StockJpaAdapter stockJpaAdapter;

    @BeforeEach
    void setUp() {
        stockRepository = mock(IStockRepository.class);
        stockJpaAdapter = new StockJpaAdapter(stockRepository);
    }

    @Test
    void whenInsertStock_thenReturnStockEntity(){
        Stock stock = new Stock(1,10,10000);

        when(stockRepository.save(any(StockEntity.class))).thenReturn(StockMapper.toEntity(stock));

        Stock stockActual = stockJpaAdapter.insertStock(stock).get();
        assertEquals(stockActual.getCurrentPrice(), stock.getCurrentPrice());
    }
}

package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.entity.StockEntityFactory;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockJpaAdapterTest {

    private IStockRepository stockRepository;
    private StockJpaAdapter stockJpaAdapter;

    @BeforeEach
    void setUp() {
        stockRepository = mock(IStockRepository.class);
        stockJpaAdapter = new StockJpaAdapter(stockRepository);
    }

    @Test
    void whenCallGetByCurrentPriceBetween_thenReturnAListStock() {
        final double minPrice = 50000.98;
        final double maxPrice = 1159000.98;
        final int page = 0;

        Optional<Page<StockEntity>> stockEntityPage = Optional.of(new PageImpl<>(new StockEntityFactory().buildList().stream()
          .filter(se -> se.getCurrentPrice() >= minPrice && se.getCurrentPrice() <= maxPrice)
          .collect(Collectors.toList())));
        when(stockRepository.findByCurrentPriceBetween(anyDouble(), anyDouble(), any())).thenReturn(stockEntityPage);

        List<StockEntity> stockEntityList = stockEntityPage.get().get().collect(Collectors.toList());
        List<Stock> stockListResponse = stockJpaAdapter.getByCurrentPriceBetween(minPrice, maxPrice, page);

        assertEquals(stockEntityList.get(0).getProductId(), stockListResponse.get(0).getProductId());
        assertEquals(stockEntityList.get(0).getQuantity(), stockListResponse.get(0).getQuantity());
        assertEquals(stockEntityList.get(0).getCurrentPrice(), stockListResponse.get(0).getCurrentPrice());

    }

    @Test
    void whenCallGetByCurrentPriceGreaterThanEqual_thenReturnAListStock() {
        final double minPrice = 89999.98;
        final int page = 0;

        Optional<Page<StockEntity>> stockEntityPage = Optional.of(new PageImpl<>(new StockEntityFactory().buildList().stream()
          .filter(se -> se.getCurrentPrice() >= minPrice).collect(Collectors.toList())));
        when(stockRepository.findByCurrentPriceGreaterThanEqual(anyDouble(), any())).thenReturn(stockEntityPage);

        List<StockEntity> stockEntityList = stockEntityPage.get().get().collect(Collectors.toList());
        List<Stock> stockListResponse = stockJpaAdapter.getByCurrentPriceGreaterThanEqual(minPrice, page);

        assertEquals(stockEntityList.get(1).getProductId(), stockListResponse.get(1).getProductId());
        assertEquals(stockEntityList.get(1).getQuantity(), stockListResponse.get(1).getQuantity());
        assertEquals(stockEntityList.get(1).getCurrentPrice(), stockListResponse.get(1).getCurrentPrice());
    }

    @Test
    void whenCallGetByCurrentPriceLessThanEqual_thenReturnAListStock() {
        final double maxPrice = 1899000.98;
        final int page = 0;

        Optional<Page<StockEntity>> stockEntityPage = Optional.of(new PageImpl<>(new StockEntityFactory().buildList().stream()
          .filter(se -> se.getCurrentPrice() <= maxPrice).collect(Collectors.toList())));
        when(stockRepository.findByCurrentPriceLessThanEqual(anyDouble(), any())).thenReturn(stockEntityPage);

        List<StockEntity> stockEntityList = stockEntityPage.get().get().collect(Collectors.toList());
        List<Stock> stockListResponse = stockJpaAdapter.getByCurrentPriceLessThanEqual(maxPrice, page);

        assertEquals(stockEntityList.get(0).getProductId(), stockListResponse.get(0).getProductId());
        assertEquals(stockEntityList.get(0).getQuantity(), stockListResponse.get(0).getQuantity());
        assertEquals(stockEntityList.get(0).getCurrentPrice(), stockListResponse.get(0).getCurrentPrice());
    }

    @Test
    void whenCallGetAll_thenReturnAListStock() {
        final int page = 0;

        Page<StockEntity> stockEntityPage = new PageImpl<>(new StockEntityFactory().buildList());
        doReturn(stockEntityPage).when(stockRepository).findAll(any(Pageable.class));

        List<StockEntity> stockEntityList = stockEntityPage.get().collect(Collectors.toList());
        List<Stock> stockListResponse = stockJpaAdapter.getAll(page);

        assertEquals(stockEntityList.get(0).getProductId(), stockListResponse.get(0).getProductId());
        assertEquals(stockEntityList.get(0).getQuantity(), stockListResponse.get(0).getQuantity());
        assertEquals(stockEntityList.get(0).getCurrentPrice(), stockListResponse.get(0).getCurrentPrice());

        assertEquals(stockEntityList.get(1).getProductId(), stockListResponse.get(1).getProductId());
        assertEquals(stockEntityList.get(1).getQuantity(), stockListResponse.get(1).getQuantity());
        assertEquals(stockEntityList.get(1).getCurrentPrice(), stockListResponse.get(1).getCurrentPrice());

        assertEquals(stockEntityList.get(2).getProductId(), stockListResponse.get(2).getProductId());
        assertEquals(stockEntityList.get(2).getQuantity(), stockListResponse.get(2).getQuantity());
        assertEquals(stockEntityList.get(2).getCurrentPrice(), stockListResponse.get(2).getCurrentPrice());
    }

}

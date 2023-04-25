package co.acelerati.planetexpress;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import co.acelerati.planetexpress.domain.usecase.StockUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockApplicationTests {

	private IStockPersistence stockPersistence;
	private ISupplyPersistence supplyPersistence;
	private ISupplyStockPersistence supplyStockPersistence;
	private StockUseCase stockUseCase;

	@BeforeEach
	void setUp() {
		stockPersistence = mock(IStockPersistence.class);
		supplyPersistence = mock(ISupplyPersistence.class);
		supplyStockPersistence = mock(ISupplyStockPersistence.class);
		stockUseCase = new StockUseCase(stockPersistence, supplyPersistence, supplyStockPersistence);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void insertStock(){
		List<Stock> stockList = new ArrayList<>();
		Stock stock = new Stock(1,10,10000);
		stockList.add(stock);
		Optional<Stock> insertOp = Optional.of(new Stock(1, 10, 10000));
		when(stockPersistence.insertStock(any(Stock.class))).thenReturn(Optional.of(stock));

		boolean insert = stockUseCase.supplyStock(stockList, 1203);
		assertTrue(insert);
	}

	@Test
	void insertSupplyStock(){
		List<Stock> stockList = new ArrayList<>();
		Stock stock = new Stock(1,10,10000);
		stockList.add(stock);
		SupplyStock supplyStock = new SupplyStock("1", "213", 12, 1500, 10500.5);
		when(supplyStockPersistence.insertSupplyStock(any(SupplyStock.class))).thenReturn(Optional.of(supplyStock));

		boolean insert = stockUseCase.supplyStock(stockList, 1203);
		assertTrue(insert);

	}

}

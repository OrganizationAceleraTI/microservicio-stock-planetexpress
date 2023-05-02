package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockUseCaseTest {

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
	void whenInsertStock_thenReturnInsertValueConfirmation(){
		List<Stock> stockList = new ArrayList<>();
		Stock stock = new Stock(1,10,10000);
		stockList.add(stock);

		when(stockPersistence.insertStock(any(Stock.class))).thenReturn(Optional.of(stock));

		boolean insertedValue = stockUseCase.supplyStock(stockList, 1203);
		assertTrue(insertedValue);
	}

	@Test
	void whenInsertSupplyStock_thenReturnInsertValueConfirmation(){
		List<Stock> stockList = new ArrayList<>();
		Stock stock = new Stock(1,10,10000);
		stockList.add(stock);
		SupplyStock supplyStock = new SupplyStock("1", "213", 12, 1500, 10500.5);

		when(supplyStockPersistence.insertSupplyStock(any(SupplyStock.class))).thenReturn(Optional.of(supplyStock));

		boolean insertedValue = stockUseCase.supplyStock(stockList, 1203);
		assertTrue(insertedValue);
	}

	@Test
	void whenInsertSupply_thenReturnInsertValueConfirmation(){
		List<Stock> stockList = new ArrayList<>();
		Stock stock = new Stock(1,10,10000);
		stockList.add(stock);
		Supply supply = new Supply(UUID.randomUUID().toString(), 1203, LocalDateTime.now());

		when(supplyPersistence.insertSupply(any(Supply.class))).thenReturn(Optional.of(supply));

		boolean insertedValue = stockUseCase.supplyStock(stockList, 1203);
		assertTrue(insertedValue);
	}
}

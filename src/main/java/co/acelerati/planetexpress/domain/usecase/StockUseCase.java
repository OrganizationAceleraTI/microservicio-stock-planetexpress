package co.acelerati.planetexpress.domain.usecase;

import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class StockUseCase implements IStockService {

    private static final double ZERO = 0;

    private final IStockPersistence stockPersistence;
    private final ISupplyPersistence supplyPersistence;
    private final ISupplyStockPersistence supplyStockPersistence;

    @Override
    public boolean supplyStock(List<Stock> productList, int idSupplier) {
        productList.forEach(product -> supplyStock(product, idSupplier));
        return true;
    }

    private boolean supplyStock(Stock stock, int idSupplier) {
        return stockPersistence.getById(stock.getIdProduct())
          .map(stockOpt -> updateQuantity(stockOpt, stock.getQuantity(), idSupplier).isPresent())
          .orElseGet(() -> registerStock(stock, ZERO, idSupplier).isPresent());
    }

    private Optional<SupplyStock> updateQuantity(Stock stock, int quantity, int idSupplier) {
        stock.addQuantity(quantity);
        return registerStock(stock, stock.getCurrentPrice(), idSupplier);
    }

    private Optional<SupplyStock> registerStock(Stock stock, double currentPrice, int idSupplier) {
        stock.setCurrentPrice(currentPrice);
        return stockPersistence.insertStock(stock)
          .flatMap(stockUpdated -> supplyPersistence.insertSupply(
            new Supply(
              UUID.randomUUID().toString(),
              idSupplier,
              LocalDateTime.now()
            )).flatMap(supplyOpt -> supplyStockPersistence.insertSupplyStock(
            new SupplyStock(
              UUID.randomUUID().toString(),
              supplyOpt.getId(),
              stockUpdated.getIdProduct(),
              stock.getQuantity(),
              stock.getCurrentPrice()
            )))
          );
    }

}

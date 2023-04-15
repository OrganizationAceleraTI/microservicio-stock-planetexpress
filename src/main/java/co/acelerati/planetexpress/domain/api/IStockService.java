package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.stock.Stock;
import co.acelerati.planetexpress.domain.model.stock.Supply;

public interface IStockService {

    boolean supplyStock(Stock stock, int idSupplier);

}

package co.acelerati.planetexpress.domain.api;

import co.acelerati.planetexpress.domain.model.stock.Stock;

import java.util.List;

public interface IStockService {

    boolean supplyStock(List<Stock> productList, int idSupplier);

}

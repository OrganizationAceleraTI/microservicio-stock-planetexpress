package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StockHandler implements IStockHandler {

    private static final Logger logger = LoggerFactory.getLogger(StockHandler.class);
    private IStockService stockService;

    @Override
    public List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands) {
        return stockService.getAllProducts(filters, products, categories, brands);
    }

    @Override
    public boolean supplyStock(List<Stock> stockList, int idSupplier) {
        return stockService.supplyStock(stockList, idSupplier);
    }
}

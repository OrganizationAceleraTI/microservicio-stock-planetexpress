package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.application.handler.IStockHandler;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.product.Product;
import co.acelerati.planetexpress.domain.model.stock.DetailStock;
import co.acelerati.planetexpress.domain.model.stock.ProductSale;
import co.acelerati.planetexpress.domain.model.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class StockHandler implements IStockHandler {

    private static final Logger logger = LoggerFactory.getLogger(StockHandler.class);

    private final IStockService stockService;

    @Override
    public List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands) {
        return stockService.getAllProducts(filters, products, categories, brands);
    }

    @Override
    public Stock updateStock(Integer stockId, Stock updateStock) {
        return stockService.updateStock((int) updateStock.getCurrentPrice(), stockId);
    }

    @Override
    public boolean supplyStock(List<Stock> stockList, int idSupplier) {
        return stockService.supplyStock(stockList, idSupplier);
    }

    @Override
    public List<ProductSale> productsSale(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands) {
        return stockService.getProductsSale(filters, products, categories, brands);
    }
}

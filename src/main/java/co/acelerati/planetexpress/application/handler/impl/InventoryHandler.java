package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.model.product.Brand;
import co.acelerati.planetexpress.domain.model.product.Category;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.product.Product;
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
public class InventoryHandler implements IInventoryHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(InventoryHandler.class);

    private final IInventoryService inventoryService;

    @Override
    public Inventory updateStock(Integer stockId, Inventory updateStock) {
        return inventoryService.updateStock(updateStock.getCurrentPrice(), stockId);
    }
    
    public void inventorySupply(List<Inventory> inventoryList) {
        inventoryService.inventorySupply(inventoryList);
    }

    @Override
    public List<Inventory> getAllInventory(int page) {
        return inventoryService.getAllInventory(page);
    }

    @Override
    public List<DetailStock> allProducts(MultiValueMap<String, String> filters, List<Product> products, List<Category> categories, List<Brand> brands) {
        return inventoryService.allProducts(filters, products, categories, brands);
    }

    @Override
    public List<Inventory> getInventoryByPrice(Integer currentPrice, int page) {
        System.out.println("currentPrice = " + currentPrice + ", page = " + page);
        return inventoryService.getInventoryByPrice(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, int page) {
        return inventoryService.getByCurrentPriceLessThanEqual(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, int page) {
        return inventoryService.getByCurrentPriceGreaterThanEqual(currentPrice, page);
    }

    @Override
    public List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, int page) {
        return inventoryService.getByCurrentPriceBetween(minPrice, maxPrice, page);
    }
}

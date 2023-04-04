package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.domain.model.Brand;
import co.acelerati.planetexpress.domain.model.Category;
import co.acelerati.planetexpress.domain.model.DetailStock;
import co.acelerati.planetexpress.domain.model.Inventory;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Product;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InventoryHandler implements IInventoryHandler {

    private final IInventoryService inventoryService;

    @Override
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

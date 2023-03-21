package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;

import java.util.List;

public interface IInventoryPersistence {

    Integer saveInventory(Inventory inventory);

    void updateInventory(Inventory inventory);

    Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productID);

    List<Inventory> getAllInventory(Integer page);

    List<Inventory> getByCurrentPrice(Integer currentPrice, Integer page);
    List<Inventory> getByCurrentPriceLessThanEqual(Integer currentPrice, Integer page);
    List<Inventory> getByCurrentPriceGreaterThanEqual(Integer currentPrice, Integer page);
    List<Inventory> getByCurrentPriceBetween(Integer minPrice, Integer maxPrice, Integer page);
}

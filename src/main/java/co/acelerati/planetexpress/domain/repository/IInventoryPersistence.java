package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;

public interface IInventoryPersistence {

    Integer saveInventory(Inventory inventory);

    void updateInventory(Inventory inventory);

    Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productID);

    List<Inventory> getByCurrentPriceIsNull (Integer currentPrice);
    List<Inventory> getByQuantityIsnull( Integer quantity);

    List<Inventory> getByCurrentPriceIsNull(Integer currentPrice, int page);
}

package co.acelerati.planetexpress.domain.repository;

import co.acelerati.planetexpress.domain.model.Inventory;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;

import java.util.List;

public interface IInventoryPersistence {

    Integer saveInventory(Inventory inventory);

    void updateInventory(Inventory inventory);

    Inventory getInventoryOfSupplier(Integer personSupplierId, Integer productID);

    List<Inventory> getByQuantityIsnull( Integer quantity, Integer page);

    List<Inventory> getByCurrentPriceIsNull(Integer currentPrice, Integer page);
}

package co.acelerati.planetexpress.infraestructure.configuration;

import co.acelerati.planetexpress.domain.api.IShoppingCartStockService;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.repository.IShoppingCartPersistence;
import co.acelerati.planetexpress.domain.repository.IShoppingCartStockPersistence;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import co.acelerati.planetexpress.domain.usecase.ShoppingCartStockUseCase;
import co.acelerati.planetexpress.domain.usecase.StockUseCase;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.ShoppingCartJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.ShoppingCartStockJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.StockJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.SupplyJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.SupplyStockJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IShoppingCartRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IShoppingCartStockRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IStockRepository stockRepository;
    private final ISupplyRepository supplyRepository;
    private final ISupplyStockRepository supplyStockRepository;
    private final IShoppingCartRepository shoppingCartRepository;
    private final IShoppingCartStockRepository shoppingCartStockRepository;

    @Bean
    public IStockPersistence stockPersistence() {
        return new StockJpaAdapter(stockRepository);
    }

    @Bean
    public ISupplyPersistence supplyPersistence() {
        return new SupplyJpaAdapter(supplyRepository);
    }

    @Bean
    public ISupplyStockPersistence supplyStockPersistence() {
        return new SupplyStockJpaAdapter(supplyStockRepository);
    }

    @Bean
    public IStockService stockService() {
        return new StockUseCase(stockPersistence(), supplyPersistence(), supplyStockPersistence());
    }

    @Bean
    public IShoppingCartPersistence shoppingCartPersistence() {
        return new ShoppingCartJpaAdapter(shoppingCartRepository);
    }

    @Bean
    public IShoppingCartStockPersistence shoppingCartStockPersistence() {
        return new ShoppingCartStockJpaAdapter(shoppingCartStockRepository);
    }
}

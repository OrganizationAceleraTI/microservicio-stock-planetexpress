package co.acelerati.planetexpress.infraestructure.configuration;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.api.IStockService;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.domain.repository.IStockPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.domain.repository.ISupplyStockPersistence;
import co.acelerati.planetexpress.domain.usecase.InventoryUseCase;
import co.acelerati.planetexpress.domain.usecase.StockUseCase;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.InventoryJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.StockJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.SupplyJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.SupplyStockJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IInventoryRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IStockRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyRepository;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    // TODO: 17/04/2023 Hay que borrar la declaración de estas interfaces despues del refactor
    private final IInventoryRepository inventoryRepository;
    private final IInventoryEntityMapper inventoryEntityMapper;

    private final IStockRepository stockRepository;
    private final ISupplyRepository supplyRepository;
    private final ISupplyStockRepository supplyStockRepository;

    @Bean
    public IInventoryPersistence inventoryPersistence() {
        return new InventoryJpaAdapter(inventoryRepository, inventoryEntityMapper);
    }

    @Bean
    public IInventoryService inventoryService() {
        return new InventoryUseCase(inventoryPersistence());
    }

    @Bean
    public IStockPersistence stockPersistence(){
        return new StockJpaAdapter(stockRepository);
    }

    @Bean
    public ISupplyPersistence supplyPersistence(){
        return new SupplyJpaAdapter(supplyRepository);
    }

    @Bean
    public ISupplyStockPersistence supplyStockPersistence(){
        return new SupplyStockJpaAdapter(supplyStockRepository);
    }

    @Bean
    public IStockService stockService(){return new StockUseCase(stockPersistence(),supplyPersistence(),supplyStockPersistence()); }
}

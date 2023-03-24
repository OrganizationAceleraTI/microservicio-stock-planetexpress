package co.acelerati.planetexpress.infraestructure.configuration;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.domain.usecase.InventoryUseCase;
import co.acelerati.planetexpress.infraestructure.persistence.adapter.InventoryJpaAdapter;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IInventoryRepository inventoryRepository;

    @Bean
    public IInventoryPersistence inventoryPersistence() {
        return new InventoryJpaAdapter(inventoryRepository);
    }

    @Bean
    public IInventoryService inventoryService() {
        return new InventoryUseCase(inventoryPersistence());
    }
}

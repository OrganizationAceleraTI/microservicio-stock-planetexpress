package co.acelerati.planetexpress.infraestructure.configuration;

import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.repository.IInventoryPersistence;
import co.acelerati.planetexpress.domain.usecase.InventoryUseCase;
import co.acelerati.planetexpress.infraestructure.output.adapter.InventoryJpaAdapter;
import co.acelerati.planetexpress.infraestructure.output.mapper.IInventoryEntityMapper;
import co.acelerati.planetexpress.infraestructure.output.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IInventoryRepository inventoryRepository;

    private final IInventoryEntityMapper inventoryEntityMapper;

    @Bean
    public IInventoryPersistence inventoryPersistence() {
        return new InventoryJpaAdapter(inventoryRepository, inventoryEntityMapper);
    }

    @Bean
    public IInventoryService inventoryService() {
        return new InventoryUseCase(inventoryPersistence());
    }
}

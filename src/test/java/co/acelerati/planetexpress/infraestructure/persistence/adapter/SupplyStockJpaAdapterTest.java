package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.stock.SupplyStock;
import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyStockEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.SupplyStockMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupplyStockJpaAdapterTest {

    private ISupplyStockRepository supplyStockRepository;
    private SupplyStockJpaAdapter supplyStockJpaAdapter;

    @BeforeEach
    void setUp(){
        supplyStockRepository = mock(ISupplyStockRepository.class);
        supplyStockJpaAdapter = new SupplyStockJpaAdapter(supplyStockRepository);
    }

    @Test
    void whenInsertSupplyStock_thenReturnSupplyStockEntity(){
        SupplyStock supplyStock = new SupplyStock(1, 123, 1, 1500, 10500.65);

        when(supplyStockRepository.save(any(SupplyStockEntity.class))).thenReturn(SupplyStockMapper.toEntity(supplyStock));

        SupplyStock supplyStockRetorno = supplyStockJpaAdapter.insertSupplyStock(supplyStock).get();
        assertEquals(supplyStockRetorno.getSupplyPrice(), supplyStock.getSupplyPrice());
    }
}

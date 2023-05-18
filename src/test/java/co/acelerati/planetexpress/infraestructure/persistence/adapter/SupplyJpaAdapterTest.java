package co.acelerati.planetexpress.infraestructure.persistence.adapter;

import co.acelerati.planetexpress.domain.model.stock.Supply;
import co.acelerati.planetexpress.domain.repository.ISupplyPersistence;
import co.acelerati.planetexpress.infraestructure.persistence.entity.SupplyEntity;
import co.acelerati.planetexpress.infraestructure.persistence.mapper.SupplyMapper;
import co.acelerati.planetexpress.infraestructure.persistence.repository.ISupplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupplyJpaAdapterTest {

    private ISupplyRepository supplyRepository;
    private SupplyJpaAdapter supplyJpaAdapter;

    @BeforeEach
    void setUp(){
        supplyRepository = mock(ISupplyRepository.class);
        supplyJpaAdapter = new SupplyJpaAdapter(supplyRepository);
    }

    @Test
    void whenInsertSupply_thenReturnSupplyEntity(){
        Supply supply = new Supply(1, 2013, LocalDateTime.now());

        when(supplyRepository.save(any(SupplyEntity.class))).thenReturn(SupplyMapper.toEntity(supply));

        Supply supplyRetorno = supplyJpaAdapter.insertSupply(supply).get();
        assertEquals(supplyRetorno.getIdSupplier(), supply.getIdSupplier());
    }
}

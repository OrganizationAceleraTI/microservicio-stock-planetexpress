package co.acelerati.planetexpress.application.handler.impl;

import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.InventorySupplyRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.response.ProviderResponseDTO;
import co.acelerati.planetexpress.application.handler.IInventoryHandler;
import co.acelerati.planetexpress.application.mapper.InventorySupplyRequestMapper;
import co.acelerati.planetexpress.application.mapper.ProviderResponseMapper;
import co.acelerati.planetexpress.domain.api.IInventoryService;
import co.acelerati.planetexpress.domain.model.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InventoryHandler implements IInventoryHandler {

    private final IInventoryService inventoryService;

    @Override
    public ProviderResponseDTO inventorySupply(List<InventorySupplyRequestDTO> inventorySupplyRequestDTO) {
        Provider provider = inventoryService.inventorySupply(InventorySupplyRequestMapper.toInventoryModelList(inventorySupplyRequestDTO));
        return ProviderResponseMapper.toProviderResponse(provider);
    }
}

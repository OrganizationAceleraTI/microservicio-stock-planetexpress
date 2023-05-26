package co.acelerati.planetexpress.infraestructure.http.rest.mapper;

import co.acelerati.planetexpress.domain.model.stock.ShoppingCartStock;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.NewCartRequestDTO;

public class ShoppingCartRequestMapper {

    public static ShoppingCartStock toModel(NewCartRequestDTO request) {
        return new ShoppingCartStock(
          request.getStockId(),
          request.getQuantity());
    }
}

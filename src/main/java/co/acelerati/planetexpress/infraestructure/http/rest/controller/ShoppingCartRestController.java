package co.acelerati.planetexpress.infraestructure.http.rest.controller;

import co.acelerati.planetexpress.application.handler.IShoppingCartStockHandler;
import co.acelerati.planetexpress.infraestructure.http.rest.dto.request.NewCartRequestDTO;
import co.acelerati.planetexpress.infraestructure.http.rest.mapper.ShoppingCartRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartRestController {

    private final IShoppingCartStockHandler shoppingCartHandler;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createShoppingCart(@PathVariable Integer userId,
                                                   @RequestBody @Valid NewCartRequestDTO newCart) {
        shoppingCartHandler.addItemsToCart(userId, ShoppingCartRequestMapper.toModel(newCart));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

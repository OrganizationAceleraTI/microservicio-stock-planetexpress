package co.acelerati.planetexpress.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart_stock")
public class ShoppingCartStockEntity {

    @Id
    @Column(name = "shopping_cart_stock_id")
    private UUID shoppingCartStockId;

    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "shopping_cart_id")
    private UUID shoppingCartId;

    @Column(name = "quantity")
    private Integer quantity;
}

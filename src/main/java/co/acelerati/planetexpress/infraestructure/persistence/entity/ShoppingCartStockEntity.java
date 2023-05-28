package co.acelerati.planetexpress.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartStockId;

    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "shopping_cart_id")
    private Integer shoppingCartId;

    @Column(name = "quantity")
    private Integer quantity;
}

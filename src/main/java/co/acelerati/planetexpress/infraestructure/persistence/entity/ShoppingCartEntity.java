package co.acelerati.planetexpress.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCartEntity {

    @Id
    @Column(name = "shopping_cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shoppingCartId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}

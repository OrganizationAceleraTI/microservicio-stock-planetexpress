package co.acelerati.planetexpress.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private UUID shoppingCartId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}

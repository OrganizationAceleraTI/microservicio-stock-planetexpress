package co.acelerati.planetexpress.infraestructure.output.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryEntity {

    @Id
    @Column(name = "inventory_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(name = "quantity")
    private Integer quantity;

}

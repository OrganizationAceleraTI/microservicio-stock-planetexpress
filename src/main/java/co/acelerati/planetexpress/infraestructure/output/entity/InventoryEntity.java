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
    @Column(name = "inventoryid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @Column(name = "productid")
    private Integer productId;

    @Column(name = "warehouseid")
    private Integer warehouseId;

    @Column(name = "quantity")
    private Integer quantity;

}

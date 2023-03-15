package co.acelerati.planetexpress.infraestructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryEntity {

    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @Column(name = "person_supplier_id")
    private Integer personSupplierId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "incoming_price")
    private Integer incomingPrice;

    @Column(name = "current_price")
    private Integer currentPrice;

    @Column(name = "quantity")
    private Integer quantity;


}

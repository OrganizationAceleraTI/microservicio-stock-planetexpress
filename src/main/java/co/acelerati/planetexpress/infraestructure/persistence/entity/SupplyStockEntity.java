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
@Table(name = "supply_stock")
public class SupplyStockEntity {

    @Id
    @Column(name = "supply_stock_id")
    private UUID id;

    @Column(name = "supply_id")
    private UUID supplyId;

    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "supply_price", columnDefinition = "NUMERIC(16, 2)")
    private Double supplyPrice;

}

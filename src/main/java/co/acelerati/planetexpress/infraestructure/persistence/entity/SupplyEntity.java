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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supply")
public class SupplyEntity {

    @Id
    @Column(name = "supply_id")
    private Integer id;

    @Column(name = "supplier_id")
    private Integer idSupplier;

    @Column(name = "date_supply")
    private LocalDateTime date;

}

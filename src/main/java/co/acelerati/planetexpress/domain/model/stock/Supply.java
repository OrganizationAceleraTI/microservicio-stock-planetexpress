package co.acelerati.planetexpress.domain.model.stock;

import java.time.LocalDateTime;

public class Supply {

    private String id;
    private int idSupplier;
    private LocalDateTime date;

    public Supply() {
    }

    public Supply(String id, int idSupplier, LocalDateTime date) {
        this.id = id;
        this.idSupplier = idSupplier;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

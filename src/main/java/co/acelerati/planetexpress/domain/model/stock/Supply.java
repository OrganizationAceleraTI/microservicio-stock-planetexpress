package co.acelerati.planetexpress.domain.model.stock;

import java.time.LocalDateTime;

public class Supply {

    private int id;
    private int idSupplier;
    private LocalDateTime date;

    public Supply() {
    }

    public Supply(int idSupplier, LocalDateTime date) {
        this.idSupplier = idSupplier;
        this.date = date;
    }

    public Supply(int id, int idSupplier, LocalDateTime date) {
        this.id = id;
        this.idSupplier = idSupplier;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Supply setDate(LocalDateTime date) {
        this.date = date;
        return duplicate();
    }

    private Supply duplicate() {
        return new Supply(id, idSupplier, date);
    }

}

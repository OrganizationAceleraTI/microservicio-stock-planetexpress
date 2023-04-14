package co.acelerati.planetexpress.domain.model.stock;

import java.time.LocalDateTime;

public class Sale {

    private String id;
    private int clientId;
    private int salesmanId;
    private LocalDateTime date;
    private String state; // TODO validate if is better get an enum

    public Sale() {
    }

    public Sale(String id, int clientId, int salesmanId, LocalDateTime date, String state) {
        this.id = id;
        this.clientId = clientId;
        this.salesmanId = salesmanId;
        this.date = date;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getState() {
        return state;
    }
}

package co.acelerati.planetexpress.domain.model;

public class Provider {

    private String name;
    private String surname;
    private Integer idPersonProvider;

    public Provider(String name, String surname, Integer idPersonProvider) {
        this.name = name;
        this.surname = surname;
        this.idPersonProvider = idPersonProvider;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getIdPersonProvider() {
        return idPersonProvider;
    }
}

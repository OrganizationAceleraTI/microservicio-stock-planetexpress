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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getIdPersonProvider() {
        return idPersonProvider;
    }

    public void setIdPersonProvider(Integer idPersonProvider) {
        this.idPersonProvider = idPersonProvider;
    }

}

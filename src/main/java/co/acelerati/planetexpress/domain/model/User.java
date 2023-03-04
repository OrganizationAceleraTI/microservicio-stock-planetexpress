package co.acelerati.planetexpress.domain.model;

public class User {

    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String address;
    private String idDniType;
    private String dniNumber;
    private String idPersonType;

    public User(String name, String surname, String mail, String phone, String address, String idDniType, String dniNumber, String idPersonType) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
        this.idDniType = idDniType;
        this.dniNumber = dniNumber;
        this.idPersonType = idPersonType;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdDniType() {
        return idDniType;
    }

    public void setIdDniType(String idDniType) {
        this.idDniType = idDniType;
    }

    public String getDniNumber() {
        return dniNumber;
    }

    public void setDniNumber(String dniNumber) {
        this.dniNumber = dniNumber;
    }

    public String getIdPersonType() {
        return idPersonType;
    }

    public void setIdPersonType(String idPersonType) {
        this.idPersonType = idPersonType;
    }
}

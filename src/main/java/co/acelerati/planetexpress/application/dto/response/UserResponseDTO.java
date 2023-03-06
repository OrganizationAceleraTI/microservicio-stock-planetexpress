package co.acelerati.planetexpress.application.dto.response;

public class UserResponseDTO {

    private String name;
    private String surname;
    private String mail;
    private String phone;
    private String address;
    private String idDniType;
    private String dniNumber;
    private String idPersonType;

    public UserResponseDTO(String name, String surname, String mail, String phone, String address, String idDniType, String dniNumber, String idPersonType) {
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

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getIdDniType() {
        return idDniType;
    }

    public String getDniNumber() {
        return dniNumber;
    }

    public String getIdPersonType() {
        return idPersonType;
    }
}

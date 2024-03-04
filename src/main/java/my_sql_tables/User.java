package my_sql_tables;

public class User {
    private int id;
    private String email;
    private String password;
    private String address;
    private String cnp;
    private String iban;
    private String telephone;
    private String type;
    private String nume;
    private String prenume;

    public void setUser(String email, String password, String address, String cnp, String iban, String telephone, String nume, String prenume, String type) {
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.nume = nume;
        this.prenume = prenume;
        this.address = address;
        this.cnp = cnp;
        this.iban = iban;
        this.type = type;
    }

    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCnp() {
        return cnp;
    }
    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

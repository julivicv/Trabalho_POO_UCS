package main;

public class Adress {
    protected String street;
    protected String number;
    protected String complement;
    protected String neighborhood;
    protected String zipCode;
    protected String city;
    protected String state;

    public Adress(String street, String number, String complement, String neighborhood, String zipCode, String city, String state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return " Rua: " + street + "\n" +
               " Número: " + number + "\n" +
               " Complemento: " + complement + "\n" +
               " Bairro: " + neighborhood + "\n" +
               " CEP: " + zipCode + "\n" +
               " Cidade: " + city + "\n" +
               " Estado: " + state;
    }
}

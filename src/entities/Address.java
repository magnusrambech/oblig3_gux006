package entities;

public class Address {
    int id;
    String streetName;
    String streetNumber;
    String postalCode;
    String postalTown;

    public Address(){

    }

    public int getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPostalTown() {
        return postalTown;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPostalTown(String postalTown) {
        this.postalTown = postalTown;
    }
}

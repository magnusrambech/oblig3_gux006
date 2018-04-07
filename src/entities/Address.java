package entities;

public class Address {
    int id;
    String streetName;
    String streetNumber;
    String postalCode;
    String postalTown;

    public Address(int id, String sName, String sNum, String pCode, String pTown){
        this.id = id;
        this.streetName = sName;
        this.streetNumber = sNum;
        this.postalCode = pCode;
        this.postalTown = pTown;
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
}

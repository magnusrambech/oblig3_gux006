package entities;

public class Customer {
    int custId;
    String name;
    int adressId;
    String phoneNr;
    String billingAcc;

    public Customer(int custId, String name, int adressId, String phoneNr, String billingAcc) {
        this.custId = custId;
        this.name = name;
        this.adressId = adressId;
        this.phoneNr = phoneNr;
        this.billingAcc = billingAcc;
    }

    public int getCustId() {
        return custId;
    }

    public String getName() {
        return name;
    }

    public int getAdressId() {
        return adressId;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public String getBillingAcc() {
        return billingAcc;
    }
}

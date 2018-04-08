package entities;

public class Customer {
    int custId;
    String name;
    int adressId;
    String phoneNr;
    String billingAcc;

    public Customer() {

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

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setBillingAcc(String billingAcc) {
        this.billingAcc = billingAcc;
    }
}

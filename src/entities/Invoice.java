package entities;

public class Invoice {
    int id;
    int custId;
    String date;

    public Invoice() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public int getCustId() {
        return custId;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package entities;

public class Product {
    int id;
    String name;
    String desc;
    float price;
    int category;


    public Product(int id, String name, String desc, float price, int category) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public float getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }
}

package DAO;

import entities.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    Connection conn;

    public ProductDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Lager et produkt-objekt basert på ID i database
     * @param id id til produkt
     * @return produkt-objekt
     */
    public Product createProductEntitiesFromID(int id){

        Product currProd = new Product();
        try{
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM PRODUCT WHERE PRODUCT_ID="+ id);
            if(rs.next()){
                currProd.setId(rs.getInt("product_id"));
                currProd.setName(rs.getString("product_name"));
                currProd.setDesc(rs.getString("description"));
                currProd.setPrice(rs.getInt("price"));
                currProd.setCategory(rs.getInt("category"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return currProd;
    }

    /**
     * Legger til et nytt produkt i databasen
     * @param p produktet som skal legges til
     */
    public void insertNewProduct(Product p){
        String name = p.getName();
        int id = p.getId();
        String desc = p.getDesc();
        float price = p.getPrice();
        int cat = p.getCategory();

        try{
            String sql = "INSERT OR IGNORE INTO product (product_id, product_name, description, price, category)" +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2, name);
            statement.setString(3,desc);
            statement.setFloat(4,price);
            statement.setInt(5,cat);
            statement.executeUpdate();
            System.out.println("Lagt til i database!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Henter ut alle produkter i databasen
     * @return ArrayList over alle produktene
     */
    public ArrayList<Product> findAllProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product");

            while(rs.next()){
                Product currProd = new Product();
                currProd.setId(rs.getInt("product_id"));
                currProd.setName(rs.getString("product_name"));
                currProd.setDesc(rs.getString("description"));
                currProd.setPrice(rs.getFloat("price"));
                currProd.setCategory(rs.getInt("category"));
                products.add(currProd);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Enderer et produkt i databasen
     * @param p produktet som skal endres
     */
    public void alterProduct(Product p){
        String sql = "UPDATE product SET " +
                "product_id = ? , " +
                "product_name = ? , " +
                "description = ? , " +
                "price = ? , " +
                "category = ? WHERE product_id=" + p.getId();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,p.getId());
            preparedStatement.setString(2, p.getName());
            preparedStatement.setString(3, p.getDesc());
            preparedStatement.setFloat(4,p.getPrice());
            preparedStatement.setInt(5, p.getCategory());

            preparedStatement.executeUpdate();
            System.out.println("Updated product with ID "+ p.getId() );
        }catch (SQLException e){

        }

    }
}

package DAO;

import entities.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    Connection conn;

    public ProductDAO(Connection conn){
        this.conn = conn;
    }
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
}

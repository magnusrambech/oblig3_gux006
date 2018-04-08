package DAO;

import entities.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}

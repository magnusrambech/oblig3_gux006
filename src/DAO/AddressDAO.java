package DAO;

import entities.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressDAO {
    private Connection conn;
    public AddressDAO(Connection connection){
        this.conn = connection;
    }


    public void createAddressEntities() {

        try{
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM ADDRESS");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

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


    public Address createAddressEntityFromId(int id) {
        Address currAdd = new Address();
        try{
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM ADDRESS WHERE ADDRESS_ID="+id);
            if(rs.next()){
                currAdd.setId(rs.getInt("address_id"));
                currAdd.setPostalCode(rs.getString("postal_code"));
                currAdd.setPostalTown(rs.getString("postal_town"));
                currAdd.setStreetName(rs.getString("street_name"));
                currAdd.setStreetNumber(rs.getString("street_number"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return currAdd;
    }
}

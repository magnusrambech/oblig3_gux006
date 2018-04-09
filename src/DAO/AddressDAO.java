package DAO;

import entities.Address;

import java.sql.*;

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
    public void insertNewAddress(Address a){
        String streetName = a.getStreetName();
        int id = a.getId();
        String streetNum = a.getStreetNumber();
        String pCode = a.getPostalCode();
        String pTown = a.getPostalTown();

        try{
            String sql = "INSERT OR IGNORE INTO address (address_id, street_number, street_name, postal_code, postal_town)" +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2, streetNum);
            statement.setString(3,streetName);
            statement.setString(4,pCode);
            statement.setString(5,pTown);
            statement.executeUpdate();
            System.out.println("Lagt til i database!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

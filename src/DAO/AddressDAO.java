package DAO;

import entities.Address;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class AddressDAO {
    private Connection conn;
    public AddressDAO(Connection connection){
        this.conn = connection;
    }

    /**
     * Lager et nytt Address-objekt basert på Id
     * @param id til addressen
     * @return Address-objekt
     */
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

    /**
     * Legger til en en addresse i databasen
     * @param a Addressen som skal legges i databasen
     */
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

    /**
     * Henter ut alle addressene i databasen og lager addresse-objekter.
     * @return ArrayList over alle addressene
     */
    public ArrayList<Address> findAllAddresses(){
        ArrayList<Address> addresses = new ArrayList<Address>();

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM address");
            while(rs.next()){
                Address currAdd = new Address();
                currAdd.setId(rs.getInt("address_id"));
                currAdd.setStreetName(rs.getString("street_name"));
                currAdd.setStreetNumber(rs.getString("street_number"));
                currAdd.setPostalCode(rs.getString("postal_code"));
                currAdd.setPostalTown(rs.getString("postal_town"));
                addresses.add(currAdd);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return addresses;
    }

    /**
     * Endrer en adresse i databasen
     * @param a addressen som endres
     */
    public void alterAddress(Address a){
        String sql ="UPDATE address SET " +
                "address_id = ? , " +
                "street_number = ? , " +
                "street_name = ? , " +
                "postal_code = ? , " +
                "postal_town = ? WHERE address_id="+a.getId();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, a.getId());
            preparedStatement.setString(2,a.getStreetNumber());
            preparedStatement.setString(3,a.getStreetName());
            preparedStatement.setString(4,a.getPostalCode());
            preparedStatement.setString(5, a.getPostalTown());

            preparedStatement.executeUpdate();
            System.out.println("Updated address with ID " +  a.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

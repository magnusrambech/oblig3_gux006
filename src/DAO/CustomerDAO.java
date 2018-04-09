package DAO;

import entities.Address;
import entities.Customer;
import entities.Invoice;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CustomerDAO {
    Connection conn;
    public CustomerDAO(Connection conn){
        this.conn = conn;
    }


    public Customer findCustomerById(int id){
        Customer custToReturn = new Customer();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE customer_id=" + id);

            if(rs.next()){
                custToReturn.setName(rs.getString("customer_name"));
                custToReturn.setPhoneNr(rs.getString("phone_number"));
                custToReturn.setCustId(rs.getInt("customer_id"));
                custToReturn.setAdressId(rs.getInt("address"));
                custToReturn.setBillingAcc(rs.getString("billing_account"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return custToReturn;
    }

    public void insertNewCustomer(Customer c){
        String name = c.getName();
        int id = c.getCustId();
        int addressId = c.getAdressId();
        String phone = c.getPhoneNr();
        String billingAcc = c.getBillingAcc();

        try{
            String sql = "INSERT OR IGNORE INTO customer (customer_id, customer_name, address, phone_number, billing_account)" +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2, name);
            statement.setInt(3,addressId);
            statement.setString(4,phone);
            statement.setString(5,billingAcc);
            statement.executeUpdate();
            System.out.println("Lagt til i database!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

package DAO;

import entities.Address;
import entities.Customer;
import entities.Invoice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            ResultSet rs = statement.executeQuery("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=" + id);

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

}

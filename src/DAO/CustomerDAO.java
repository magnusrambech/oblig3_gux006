package DAO;

import entities.Address;
import entities.Customer;
import entities.Invoice;

import java.sql.*;
import java.util.ArrayList;
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

    /**
     * Legger til en ny kunde i databasen
     * @param c kunden som skal legges til
     */
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

    /**
     * Henter alle kundene i databasen
     * @return ArrayList som inneholder alle kunder.
     */
    public ArrayList<Customer> findAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM customer");
            while(rs.next()){
                Customer currCust = new Customer();
                currCust.setName(rs.getString("customer_name"));
                currCust.setCustId(Integer.parseInt(rs.getString("customer_id")));
                currCust.setAdressId(Integer.parseInt(rs.getString("address")));
                currCust.setPhoneNr(rs.getString("phone_number"));
                currCust.setBillingAcc(rs.getString("billing_account"));
                customers.add(currCust);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * Endrer en kunde i databasen
     * @param c kunden som endres
     */
    public void alterCustomer(Customer c){
        String sql = "UPDATE customer  SET " +
                "customer_name = ? , " +
                "address = ? , " +
                "phone_number = ? , " +
                "billing_account = ? WHERE customer_id="+c.getCustId();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,c.getName());
            preparedStatement.setInt(2,c.getAdressId());
            preparedStatement.setString(3,c.getPhoneNr());
            preparedStatement.setString(4,c.getBillingAcc());

            preparedStatement.executeUpdate();
            System.out.println("Updated customer with ID and name" + c.getCustId() + " " + c.getName());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

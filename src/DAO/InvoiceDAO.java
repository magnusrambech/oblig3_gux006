package DAO;

import entities.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InvoiceDAO {
    private Connection conn;

    public InvoiceDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Invoice> createInvoiceEntities() {
        List<Invoice> invoices = new LinkedList<Invoice>();
        Invoice currInvoice;
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM invoice");

            while (rs.next()) {
                currInvoice = new Invoice();

                currInvoice.setId(rs.getInt("invoice_id"));
                currInvoice.setCustId(rs.getInt("customer"));
                currInvoice.setDate(rs.getString("dato"));

                invoices.add(currInvoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public Invoice createInvoiceFromId(int id) {
        Invoice currInvoice = new Invoice();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM invoice WHERE invoice_id=" + id);

            if (rs.next()) {
                currInvoice.setId(rs.getInt("invoice_id"));
                currInvoice.setCustId(rs.getInt("customer"));
                currInvoice.setDate(rs.getString("dato"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currInvoice;
    }
    public void insertNewInvoice(Invoice i){
        int id = i.getId();
        int custId = i.getCustId();
        String date = i.getDate();

        try{
            String sql = "INSERT OR IGNORE INTO invoice (invoice_id, customer, dato)" +
                    "VALUES (?, ?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setInt(2, custId);
            statement.setString(3, date);
            statement.executeUpdate();
            System.out.println("Lagt til i database!");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public ArrayList<Invoice> findAllInvoices(){
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM invoice");
            while(rs.next()){
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("invoice_id"));
                invoice.setCustId(rs.getInt("customer"));
                invoice.setDate(rs.getString("dato"));
                invoices.add(invoice);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return invoices;
    }
    public void alterInvoice(Invoice i){
        String sql = "UPDATE invoice SET " +
                "invoice_id = ? , " +
                "customer = ? , " +
                "dato = ? WHERE invoice_id=" + i.getId();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,i.getId());
            preparedStatement.setInt(2, i.getCustId());
            preparedStatement.setString(3,i.getDate());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}

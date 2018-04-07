package DAO;

import entities.Invoice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class InvoiceDAO {
    private Connection conn;

    public InvoiceDAO(Connection conn){
        this.conn = conn;
    }
    public List<Invoice> createInvoiceEntities(){
        List<Invoice> invoices = new LinkedList<Invoice>();
        Invoice currInvoice;
        try{
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM INVOICE");

            while(rs.next()){
                currInvoice = new Invoice();

                currInvoice.setId(rs.getInt("invoice_id"));
                currInvoice.setCustId(rs.getInt("customer"));
                currInvoice.setDate(rs.getString("dato"));

                invoices.add(currInvoice);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return invoices;
    }

}

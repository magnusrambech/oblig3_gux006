package DAO;

import entities.InvoiceItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InvoiceItemsDAO {
    Connection conn;

    public InvoiceItemsDAO(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Integer> findProductsOnInvoice(int invoiceID){
        ArrayList<Integer> prodIDList = new ArrayList<Integer>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT PRODUCT FROM INVOICE_ITEMS WHERE INVOICE = " + invoiceID);
            while(rs.next()){
                prodIDList.add(rs.getInt("product"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodIDList;
    }

    public void insertNewInvoiceItems(InvoiceItem i){
        int id = i.getInvoiceId();
        int prodId = i.getProductId();

        try{
            String sql = "INSERT OR IGNORE INTO invoice_items (invoice, product)" +
                    "VALUES (?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setInt(2, prodId);
            statement.executeUpdate();
            System.out.println("Lagt til i database!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

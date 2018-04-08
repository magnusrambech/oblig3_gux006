package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}

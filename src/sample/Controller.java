package sample;

import DAO.ConnectionDAO;
import DAO.InvoiceDAO;
import entities.Invoice;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


public class Controller {
    public Button fakturaButton;
    public TextField invoiceIdText;
    public TextField customerIdText;
    public TextField datoText;

    public void loadFaktura(ActionEvent actionEvent) throws FileNotFoundException, SQLException {
        //Henter ut alle invoicene og skriver de ut
        ConnectionDAO connDao = new ConnectionDAO("ryddig.db");
        Connection conn = connDao.getConnection();
        InvoiceDAO invoices = new InvoiceDAO(conn);
        List<Invoice> invoicesList = invoices.createInvoiceEntities();
        Iterator it = invoicesList.iterator();

        if(it.hasNext()){
            Invoice inv = (Invoice)it.next();
            datoText.setText(inv.getDate());
            // String idText = inv.getId();
           // invoiceIdText.setText(inv.getId());
        }
    }
}

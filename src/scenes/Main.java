package scenes;

import DAO.ConnectionDAO;
import DAO.InvoiceDAO;
import DAO.SQLExecutor;
import entities.Invoice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Scenes/sample.fxml"));
        primaryStage.setTitle("Fakturaleser");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException, SQLException {
        launch(args);
        ConnectionDAO connDao = new ConnectionDAO("ryddig.db");
        Connection conn = connDao.getConnection();
        String fileToRead =  "oblig3v1_database.sql";
        SQLExecutor readSql = new SQLExecutor(fileToRead, conn);


        //Henter ut alle invoicene og skriver de ut
        InvoiceDAO invoices = new InvoiceDAO(conn);
        List<Invoice> invoicesList = invoices.createInvoiceEntities();
        Iterator it = invoicesList.iterator();

        while(it.hasNext()){
            Invoice inv = (Invoice)it.next();
            System.out.println("Invoice id: "+ inv.getId());
            System.out.println("Customer id: " + inv.getCustId());
            System.out.println("Dato: " +inv.getDate());
        }
        conn.close();
    }



}

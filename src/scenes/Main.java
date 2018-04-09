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
   public static Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/scenes/sample.fxml"));
        primaryStage.setTitle("Fakturaleser");
        primaryStage.setScene(new Scene(root, 630, 530));
        primaryStage.show();
    }


    public static void main(String[] args) throws FileNotFoundException, SQLException {

        // Kobler opp mot sqlite database og skriver sqlfilen til database.
        ConnectionDAO connDao = new ConnectionDAO("ryddig.db");
        conn = connDao.getConnection();
        String fileToRead =  "oblig3v1_database.sql";
        SQLExecutor readSql = new SQLExecutor(fileToRead, conn);

        launch(args);
    }

    public Connection getConnection(){
        return conn;
    }

}

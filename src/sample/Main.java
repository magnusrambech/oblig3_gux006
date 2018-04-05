package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void CreateAndConnectToDB(String dbname) throws FileNotFoundException, SQLException {
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:C:/SQLite/db/" + dbname;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established!");
            System.out.println(conn.getMetaData());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        File schema = new File( "oblig3v1_database.sql");
        InputStream in = new FileInputStream(schema);
        importSQL(conn,in);


    }

    public static void importSQL(Connection conn, InputStream in) throws SQLException
    {
        Scanner s = new Scanner(in);
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        Statement st = null;
        try
        {
            st = conn.createStatement();
            while (s.hasNext())
            {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/"))
                {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0)
                {
                    st.execute(line);
                }
            }
        }
        finally
        {
            if (st != null) st.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        boolean beenRun = false;
        launch(args);
        CreateAndConnectToDB("Test4.db");

    }



}

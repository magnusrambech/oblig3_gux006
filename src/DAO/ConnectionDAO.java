package DAO;

import interfaces.ConnectionDAOIF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnectionDAO implements ConnectionDAOIF{
    Connection conn = null;

    /**
     * Oppretter en tilkobling mot databasen.
     * @param dbname databasen som skal kobles opp.
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public ConnectionDAO(String dbname) throws FileNotFoundException, SQLException {
        try {
            String url = "jdbc:sqlite:C:/SQLite/db/" + dbname;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public Connection getConnection() {
        return conn;
    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }
}

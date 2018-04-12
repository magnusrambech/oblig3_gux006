package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLExecutor {
    File file;
    Connection conn = null;
    InputStream in = null;

    public SQLExecutor(String fileName, Connection conn) throws FileNotFoundException, SQLException {
        this.file = new File(fileName);
        this.conn = conn;
        in = new FileInputStream(file);
        createDBfromFile();
    }

    /**
     * Leser fra SQL-fil og utfører statements mot sqlite database. Utføres ikke hvis databasen allerede er lest inn
     * @throws SQLException
     */
    public void createDBfromFile() throws SQLException {
        //Sjekker om databasen allerede er laget. Hviss, return
        if(alreadyFilled()){
            System.out.println("Tables already inserted");
            return;
        }

        //Skriver fra fil til database
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
    /**
     * Sjekker om databasen allerede er lastet inn.
     * @return
     */
    public boolean alreadyFilled(){
        try{
            String sql = "SELECT * FROM address,category,customer,invoice,invoice_items,product";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        }catch (SQLException e){
            return false;
        }
        return true;
    }
}

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

    public void createDBfromFile() throws SQLException {
        if(alreadyFilled()){
            System.out.println("Tables already inserted");
            return;
        }
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

    public boolean alreadyFilled() throws SQLException {
        String sql = "SELECT * FROM address,category,customer,invoice,invoice_items,product";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if(!rs.next()){
            return false;
        }
        return true;
    }
}

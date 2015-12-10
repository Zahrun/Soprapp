package webapp.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 * A singleton service available at the business tier in order to insert and
 * retrieve data from a Java DB Derby database
 *
 * @author ernestoexposito
 */
public class DataService {
    // DB connection URL specification includes the JDBC protocol, 
    // the server address and port, the database name that will be created if it does not exist
    // and the username and password

    private String dbURL = "jdbc:mysql://192.168.0.102/SopraBD";
    // the name of the table to be used to insert/select data
    private String tableName = "Users";
    // jdbc Connection
    private Connection conn = null;
    private Statement stmt = null;
    private static DataService instance = null;

    private DataService() {
        createConnection();
    }

    /**
     * Returns the service instance
     *
     * @return DataService already created of freshly instantiated
     */
    public static DataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        return instance;
    }

    /**
     * Establishment of the connection with the database server
     */
    public void createConnection() {
        try {
            //Get a connection based on the db URL
            conn = DriverManager.getConnection(dbURL, "root", "BDandroid1");
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    /**
     * Insert data into the database
     *
     * @param name to be inserted
     * @param age to be inserted
     * @return a 'Data inserted!' message or any thrown exception
     */
    public synchronized String insertData(String name, int admin) {
        String r = "Data inserted!";
        try {
            // creates a SQL Statement object in order to execute the SQL insert command
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " (name,admin) values ('"
                    + name + "'," + admin + ")");
            stmt.close();
        } catch (SQLException sqlExcept) {
            r = sqlExcept.toString();
        }
        return r;
    }

    /**
     * Return all the available data for the name and age columns
     *
     * @return a String containing headers and data rows
     */
    public String selectData() {
        String r = "";
        try {
            // creates a SQL Statement object in order to execute the SQL select command
            stmt = conn.createStatement();
            // the SQL select command will provide a ResultSet containing the query results
            ResultSet results = stmt.executeQuery("select * from " + "Users");
            // the ResultSetMetaData object will provide information about the columns
            // for instance the number of columns, their labels, etc.
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                r += rsmd.getColumnLabel(i) + "  --  ";
            }
            r += "</br>";
            while (results.next()) {
                // the name and age values are retrieved from the appropiate column
                String name = results.getString(results.findColumn("name"));
                r += name + "</br>";
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            r = sqlExcept.toString();
        }
        return r;
    }
    
    public JSONObject selectUser(String mail, String pwd){
        JSONObject result = new JSONObject();
        try{
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from Users where 'mailAddress'='"+mail+"' and 'password'='"+pwd+"'");
            
            while (results.next()) {
                // the name and age values are retrieved from the appropiate column
                String name = results.getString(results.findColumn("name"));
                
            }
            results.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Service shutdown. Be careful, if the DataService singleton is shutdown,
     * it won't be available anymore for other service clients.
     */
    public void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
}

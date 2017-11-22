package Infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection = null;

    private final static String HOST = "127.0.0.1";
    private final static String USER = "userShopDB";
    private final static String PASSWORD = "test";
    private final static String DATABASE = "shopDB";
    private final static int PORT = 3306;


    private Database()
    {

    }

    /**
     * Returns an instance to the Connection Object of the Database
     * @return instance
     */
    public static Connection getDatabaseConnectionInstance()
    {
        if (connection == null)
        {
            loadDriver();
            openConnection();
        }

        return connection;
    }

    /**
     * Opens a connection to the Database
     */
    private static void openConnection()
    {
        try{
            connection = DriverManager.getConnection(getConnectionString(), USER, PASSWORD);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
    }

    /**
     * Closes the connection to the Database
     */
    public static void closeConnection()
    {
        try {
            if(connection == null){
                System.out.println("There is no connection open.");
            }else{
                connection.close();
                connection = null;
            }
        } catch (SQLException e) { /* ignored */}
    }

    /**
     * Loads the mysql Driver
     */
    private static void loadDriver()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            System.out.println("Could not load driver");
        }
    }

    /**
     * Returns a connection string used
     * to connect to the Database
     *
     * @return connection String used to connect to the Database
     */
    private static String getConnectionString()
    {
        String connectionString = "jdbc:mysql://"+HOST + ":" + PORT + "/" + DATABASE +"?user="+ USER + "&password=" + PASSWORD;
        return connectionString;
    }




}

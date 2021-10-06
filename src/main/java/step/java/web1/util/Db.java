package step.java.web1.util;

import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
    final private static String SUFFIX = "_0" ;
    private static Connection connection ;

    public static boolean setConnection( JSONObject connectionData ) {
        if( connectionData == null ) {
            connection = null ;
            return false ;
        }
        String connectionString ;
        try {
            connectionString = String.format (
                    "jdbc:oracle:thin:%s/%s@%s:%s:XE",
                    connectionData.get( "user" ),
                    connectionData.get( "pass" ),
                    connectionData.get( "host" ),
                    connectionData.get( "port" )
            ) ;
            DriverManager.registerDriver(
                    new oracle.jdbc.driver.OracleDriver()
            ) ;
            connection =
                    DriverManager.getConnection( connectionString ) ;
        } catch( Exception ex ) {
            System.err.println( ex.getMessage() ) ;
            return false ;
        }
        return true ;
    }

    public static Connection getConnection() {
        return connection ;
    }

    /**
     * Creates table for gallery
     */
    public static void createGallery() {
        if( connection == null ) return ;
        String query = null ;
        try( Statement statement = connection.createStatement() ) {
            query = "CREATE TABLE Pictures" + SUFFIX +
                    "(Id          RAW(16) DEFAULT SYS_GUID() PRIMARY KEY, " +
                    " Name        NVARCHAR2(256) NOT NULL, " +
                    " Description NVARCHAR2(400) NULL, " +
                    " Moment      DATE DEFAULT CURRENT_TIMESTAMP )" ;
            statement.executeUpdate( query ) ;
        } catch( SQLException ex ) {
            System.err.println(
                "createGallery: " + ex.getMessage() + " " + query ) ;
        }
    }
}

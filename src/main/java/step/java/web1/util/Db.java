package step.java.web1.util;

import org.json.simple.JSONObject;
import step.java.web1.models.Picture;

import java.sql.*;
import java.util.ArrayList;

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

    public static void closeConnection() {
        if( connection != null )
            try { connection.close() ; }
            catch( Exception ignored ) {}
    }

    /**
     * Creates table for gallery
     */
    private static void createGallery() {
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

    /**
     * Inserts Picture in DB
     */
    public static boolean addPicture( Picture pic ) {
        if( connection == null ) return false ;
        String query = "INSERT INTO Pictures" + SUFFIX +
                "(Name, Description) VALUES(?, ?)" ;
        try( PreparedStatement statement =
                    connection.prepareStatement( query ) ) {
            statement.setString( 1, pic.getName() ) ;
            statement.setString( 2, pic.getDescription() ) ;
            statement.executeUpdate() ;
            return true ;
        } catch( SQLException ex ) {
            System.err.println(
                    "addPicture: " + ex.getMessage() + " " + query ) ;
            return false ;
        }
    }

    /**
     * Loads picture(s) list (gallery)
     */
    public static ArrayList<Picture> getPictures() {
        ArrayList<Picture> res = null ;
        try ( Statement statement = connection.createStatement() ) {
            String query = "SELECT * FROM Pictures" + SUFFIX ;
            ResultSet answer = statement.executeQuery( query ) ;
            res = new ArrayList<>() ;
            while( answer.next() ) {
                res.add( new Picture(
                    answer.getString( "ID" ),
                    answer.getString( "NAME" ),
                    answer.getString( "DESCRIPTION" ),
                    answer.getString( "MOMENT" )
                ) ) ;
            }
        } catch( Exception ex ) {
            System.err.println( "getPictures: " + ex.getMessage() ) ;
        }
        return res ;
    }
}

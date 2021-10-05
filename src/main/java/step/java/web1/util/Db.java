package step.java.web1.util;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Connection connection ;

    public static Connection getConnection() {
        if( connection == null ) {
            File config = new File(
                "../webapps/web1_war/WEB-INF/config/db.json") ;
            if( ! config.exists() ) {
                System.err.println( "config/db.json not found" ) ;
                return null ;
            }
            String connectionString ;
            try( InputStream reader = new FileInputStream( config ) ) {
                byte[] buf = new byte[ (int) config.length() ] ;
                reader.read( buf ) ;
                JSONObject configData = (JSONObject)
                        new JSONParser().parse( new String( buf ) ) ;
                connectionString = String.format (
                        "jdbc:oracle:thin:%s/%s@%s:%s:XE",
                        configData.get( "user" ),
                        configData.get( "pass" ),
                        configData.get( "host" ),
                        configData.get( "port" )
                ) ;
            } catch( Exception ex ) {
                System.err.println( ex.getMessage() ) ;
                return null ;
            }

            try {
                DriverManager.registerDriver(
                    new oracle.jdbc.driver.OracleDriver()
                ) ;
                connection =
                    DriverManager.getConnection( connectionString ) ;
            } catch( SQLException ex ) {
                System.err.println( ex.getMessage() ) ;
                return null ;
            }
        }
        return connection ;
    }
}

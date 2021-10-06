package step.java.web1.filters;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import step.java.web1.util.Db;

import javax.servlet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class DbFilter implements Filter {

    private  FilterConfig filterConfig ;

    @Override
    public void init(FilterConfig filterConfig)  {
        this.filterConfig = filterConfig ;
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // System.out.println( "Filter works!" ) ;
        Db.setConnection( null ) ;
        File config = new File(
                filterConfig
                    .getServletContext()
                    .getRealPath("/WEB-INF/config/" )
                + "/" + "db.json" ) ;
        if( ! config.exists() ) {
            System.err.println( "config/db.json not found" ) ;
        } else {
            try (InputStream reader = new FileInputStream(config)) {
                int fileLength = (int) config.length();
                byte[] buf = new byte[fileLength];
                if (reader.read(buf) != fileLength)
                    throw new IOException("File read integrity falls");
                JSONObject configData = (JSONObject)
                        new JSONParser().parse(new String(buf));
                if( ! Db.setConnection( configData ) )
                    throw new SQLException( "Db connection error" ) ;
            } catch( Exception ex ) {
                System.err.println( ex.getMessage() ) ;
            }
        }
        // Checking connection to be opened
        if( Db.getConnection() == null ) {
            // No connection - use static mode
            servletRequest
                .getRequestDispatcher( "/static.jsp" )
                .forward( servletRequest, servletResponse ) ;
        } else {
            filterChain.doFilter( servletRequest, servletResponse ) ;
            Db.closeConnection() ;
        }
    }

    @Override
    public void destroy() {
        filterConfig = null ;
    }
}
/*
    Сервлетные фильтры
    Реализация концепции Middleware:
    - Создание "цепочки" последовательных вызовов
        методов разных классов.
    - Механизм автоматического встраивания
        новых классов в эту цепочку
    Для создания фильтра:
    1. Реализуем интерфейс javax.servlet.Filter
    2. Встраиваем фильтр
        а) через аннотацию @WebFilter
        б) через web.xml
 */
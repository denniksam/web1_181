package step.java.web1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException
    {
        try {
            request
            .getRequestDispatcher( "hello_view.jsp" )
            .forward( request, response ) ;
        } catch( ServletException ex ) {
            System.out.println( ex.getMessage() ) ;
            // Hello
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}
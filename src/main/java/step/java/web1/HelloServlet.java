package step.java.web1;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Проверяем наличие в сессии атрибутов-сообщений
            HttpSession session = request.getSession() ;
            String cellphoneMessage = (String)
                    session.getAttribute( "cellphoneMessage" ) ;
            if( cellphoneMessage != null ) {  // есть в сессии
                // удаляем из сессии, иначе сообщение будет
                // с каждым обновлением страницы
                session.removeAttribute( "cellphoneMessage" ) ;
            } else {
                cellphoneMessage = "" ;
            }

            String usernameMessage = (String) session.getAttribute("usernameMessage");
            if( usernameMessage != null ) {
                session.removeAttribute("usernameMessage");
            } else {
                usernameMessage = "" ;
            }

            // включаем сообщение в атрибуты запроса (для View)
            request.setAttribute(
                    "cellphoneMessage", cellphoneMessage ) ;
            request.setAttribute(
                    "usernameMessage", usernameMessage ) ;

            // Загружаем представление (View)
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cellphone = req.getParameter( "cellular" ) ;
        String username  = req.getParameter( "username" ) ;

        // Задание: реализовать валидацию данных:
        // телефон - содержит только цифры
        // имя - не содержит цифры
        // Сформировать соотв. сообщения
        String cellphoneMessage = "" ;
        if(cellphone == null || cellphone.isEmpty() ) {
            cellphoneMessage = "Телефон не может быть пустым" ;
        }
        // Задание: реализовать обработку username, вывод сообщения
        String usernameMessage = "";
        if( username == null || username.isEmpty() ) {
            usernameMessage = "User name can not be empty" ;
        }

        // НТТР сессия - способ хранения данных между запросами
        HttpSession session = req.getSession() ;
        session.setAttribute(
                "cellphoneMessage", cellphoneMessage ) ;
        session.setAttribute(
                "usernameMessage", usernameMessage ) ;

        resp.sendRedirect( req.getRequestURI() ) ;  /*
            Клиент получит ответ со статусом 30х и
            Location: тот же адрес
            - Browser отправит запрос на этот адрес
              !! методом GET */
        /*
        // !! На запросы POST нельзя строить View
        req.setAttribute(
                "cellphoneMessage",
                cellphoneMessage ) ;
        try {
            req.getRequestDispatcher( "hello_view.jsp" )
               .forward( req, resp ) ;
        } catch( ServletException ex ) {
            System.out.println( ex.getMessage() ) ;
        }
         */
    }

    public void destroy() {
    }
}
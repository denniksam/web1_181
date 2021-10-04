package step.java.web1;

import step.java.web1.util.Hasher;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class GalleryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
            Загрузка файла:
                Upload - от клиента к серверу
                Download - от сервера к клиенту
            Uploading:
                1. <form> <input type="file" ... />
                2. <form method="post" enctype="multipart/form-data"
                3. конфиг. сервлета: а) <multipart-config />
                                     б) @MultipartConfig
                4. прием файла: в методе doPost через req.getPart
                5. извлечение имени файла !! по безопасности
                   крайне не рекомендуется сохранять файлы под
                   переданными именами
        */
        req.setCharacterEncoding( "UTF-8" ) ;

        HttpSession session = req.getSession() ;
        String uploadMessage = (String)
                session.getAttribute( "uploadMessage" ) ;
        if( uploadMessage != null ) {
            session.removeAttribute( "uploadMessage" ) ;
        } else {
            uploadMessage = "" ;
        }
        req.setAttribute( "uploadMessage", uploadMessage ) ;

        req.getRequestDispatcher( "gallery.jsp" )
                .forward( req, resp ) ;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding( "UTF-8" ) ;

        Part filePart = req.getPart( "galleryfile" ) ;  // имя  <input type="file"
        HttpSession session = req.getSession() ;

        /*
            Submitted file name
            a) .getSubmittedFileName()
            b) extract from header:
                content-disposition: form-data; name="galleryfile"; filename="img.png"
         */
        String attachedFilename = null ;
        String contentDisposition =
                filePart.getHeader("content-disposition" ) ;
        if( contentDisposition != null ) {
            for( String part : contentDisposition.split( "; ") ) {
                if( part.startsWith( "filename" ) ) {
                    attachedFilename = part.substring( 10, part.length() - 1 ) ;
                    break ;
                }
            }
        }
        if( attachedFilename != null ) {  // имя успешно извлечено
            // Задача: отделить имя и расширение
            String extension ;
            int dotPosition = attachedFilename.lastIndexOf( "." ) ;
            if( dotPosition > -1 ) {
                extension = attachedFilename.substring( dotPosition ) ;
                // формируем случайное имя файла, сохраняем расширение
                String savedFilename = Hasher.hash( attachedFilename ) + extension ;
                // Определяем путь в файловой системе
                String path = req.getServletContext().getRealPath( "/uploads" ) ;
                // Полное имя файла
                String filename = path + "\\" + savedFilename ;

                File destination = new File( filename ) ;
                Files.copy(
                        filePart.getInputStream(),  // source (Stream)
                        destination.toPath(),       // destination (Path)
                        StandardCopyOption.REPLACE_EXISTING
                ) ;
                attachedFilename = savedFilename ;
            }
            else {  // no file extension
                attachedFilename = "no file extension" ;
            }
        }

        session.setAttribute(
                "uploadMessage",
                ( attachedFilename == null ) ? "Name error" : attachedFilename ) ;

        resp.sendRedirect( req.getRequestURI() ) ;
    }
}
/*
    Галерея
    Отображает картинки и описания к ним
    Поддерживает возможность загрузки новых изображений.
 */
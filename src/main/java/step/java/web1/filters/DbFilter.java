package step.java.web1.filters;

import javax.servlet.*;
import java.io.IOException;

public class DbFilter implements Filter {

    private  FilterConfig filterConfig ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig ;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException
    {
        System.out.println( "Filter works!" ) ;

        filterChain.doFilter(servletRequest, servletResponse) ;
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
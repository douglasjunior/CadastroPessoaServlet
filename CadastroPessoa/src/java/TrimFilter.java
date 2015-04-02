import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Douglas
 */
public class TrimFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TrimFilter:init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TrimFilter:doFilter");
        String nome = request.getParameter("nome");
        if (nome != null) {
            String novoNome = nome.trim();
            request.setAttribute("nome", novoNome);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("TrimFilter:destroy");
    }

}

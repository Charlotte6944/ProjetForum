package fr.m2i.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({"/forum", "/comment"})
public class LoginFilter extends HttpFilter implements Filter {
	
	private static String PAGE = "http://localhost:8080/Base/home";
       
    public LoginFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest resp = (HttpServletRequest) request;
				
		if(resp.getSession().getAttribute("auth") == null) {
			HttpServletResponse httpresponse = (HttpServletResponse) response;
			httpresponse.sendRedirect(PAGE);
		} else {
			chain.doFilter(request, response);			
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

package fr.m2i.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.m2i.models.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String FORUMPAGE = "/home";
	private static final String HOMEPAGE = "/WEB-INF/pages/welcome.jsp";
	
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isLogoff = request.getParameter("logoff");
		System.out.println(isLogoff);
		
		if(isLogoff != null & !isLogoff.isEmpty() & isLogoff.equals("true")) {
			logoff(request);
			this.getServletContext().getRequestDispatcher(FORUMPAGE).forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher(HOMEPAGE).forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
		EntityManager em = factory.createEntityManager();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User user = em.createNamedQuery("findUser", User.class)
				.setParameter("lo", login)
				.setParameter("up", password)
				.getSingleResult();
		
		int idUser = user.getId();
				
		session.setAttribute("auth", true);
		session.setAttribute("user", user);
		session.setAttribute("id", idUser);
						
		response.sendRedirect(request.getContextPath() + FORUMPAGE);
		
		em.close();
	}
	
	protected void logon(String login, String mdp, HttpServletRequest request) {
		/* RAJOUTER UNE VERIF */
		HttpSession session = request.getSession();
		
		session.setAttribute("auth", false);
	}
	
	protected void logoff(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("auth");
		session.removeAttribute("user");
	}

}

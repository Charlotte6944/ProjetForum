package fr.m2i.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.m2i.models.Topic;

@WebServlet("/forum")
public class Forum extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PAGEFORUM = "/WEB-INF/pages/forum.jsp";

    public Forum() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Liaison with DBB
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
		EntityManager em = factory.createEntityManager();
		
		// Method READ
		@SuppressWarnings("unchecked")
		List<Topic> topics = em.createNativeQuery("SELECT * FROM topic", Topic.class).getResultList();
		request.setAttribute("topics", topics);
		
		em.close();
				
		this.getServletContext()
		.getRequestDispatcher(PAGEFORUM)
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String req = request.getParameter("req");
		
		// Method CREATE
		if(req.equalsIgnoreCase("add")) {
			addTopic(request);
		}
		
		// Method UPDATE
		if(req.equalsIgnoreCase("update")) {
			updateTopic(request);
		}
		
		// Method DELETE
		if(req.equalsIgnoreCase("delete")) {	
			deleteTopic(request);
		}
		
		// Method CREATE Comment
		if(req.equalsIgnoreCase("addComment")) {
			addComment(request);
		}
		
		// Method DELETE Comment
		if(req.equalsIgnoreCase("deleteComment")) {
			deleteComment(request);
		}
		
		doGet(request, response);
	}

	private void addTopic(HttpServletRequest request) {
		// Liaison with DBB
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
		
		boolean transac = false;

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String date_created = date();
		
		HttpSession session = request.getSession();
		int idUser = (int)session.getAttribute("id");
		
		em.createNativeQuery("INSERT INTO topic (title, content, date_created, id_user) VALUES(?1, ?2, ?3, ?4)")
			.setParameter(1, title)
			.setParameter(2, content)
			.setParameter(3, date_created)
			.setParameter(4, idUser)
			.executeUpdate();
		try {
			transac = true;
		} finally {
			if(transac) {
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		}
		em.close();
	}
	
	private void addComment(HttpServletRequest request) {
		// Liaison with DBB
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
		
		boolean transac = false;

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		String comment = request.getParameter("comment");
		String date_created = date();
		
		HttpSession session = request.getSession();
		int idUser = (int)session.getAttribute("id");
		int idTopic = Integer.parseInt(request.getParameter("idTopic"));
		
		em.createNativeQuery("INSERT INTO comment (comment, date_created, id_user, id_topic) VALUES(?1, ?2, ?3, ?4)")
			.setParameter(1, comment)
			.setParameter(2, date_created)
			.setParameter(3, idUser)
			.setParameter(4, idTopic)
			.executeUpdate();
		try {
			transac = true;
		} finally {
			if(transac) {
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		}
		em.close();
	}
	
	private void updateTopic(HttpServletRequest request) {
		// Liaison with DBB
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
				
		boolean transac = false;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Topic topic = em.find(Topic.class, id);
		topic.setContent(request.getParameter("content"));
		topic.setTitle(request.getParameter("title"));
		
		em.persist(topic);
		
		try {
			transac = true;
		} finally {
			if(transac) {
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		}
		em.close();
	}
	
	private void deleteTopic(HttpServletRequest request) {
		// Liaison with DBB
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
		
		boolean transac = false;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Topic topic = (Topic) em.createNativeQuery("SELECT * FROM topic WHERE id = ?", Topic.class)
		.setParameter(1, id)
		.getSingleResult();
		
		em.remove(topic);
		
		try {
			transac = true;
		} finally {
			if(transac) {
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		}
		em.close();
	}
	
	private void deleteComment(HttpServletRequest request) {
		// Liaison with DBB
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Forum");
		
		boolean transac = false;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		int id = Integer.parseInt(request.getParameter("id"));
		em.createNativeQuery("DELETE FROM comment WHERE id = ? ")
		.setParameter(1, id)
		.executeUpdate();
		try {
			transac = true;
		} finally {
			if(transac) {
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		}
		em.close();

	}

	private String date() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String StrDate = dateFormat.format(cal.getTime());
		return StrDate;
	}

}

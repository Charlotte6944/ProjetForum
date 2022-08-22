package fr.m2i.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.m2i.models.Topic;


@Path("/topic")
public class TopicApi {
	EntityManagerFactory factory;
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Topic> listTopics() {
		factory = Persistence.createEntityManagerFactory("Forum");
		em = factory.createEntityManager();
		
		List<Topic> topics;

		Query allTopicQuery = em.createNamedQuery("SelectAllTopic", Topic.class);

		try {
			topics = allTopicQuery.getResultList();
		} catch (NoResultException nre) {
			topics = null;
		} finally {
			em.close();
			factory.close();
		}
		System.out.println(topics);
		return topics;
	}
	
}

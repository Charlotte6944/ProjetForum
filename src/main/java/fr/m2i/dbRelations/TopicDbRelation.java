package fr.m2i.dbRelations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.m2i.models.Topic;

public class TopicDbRelation {
	EntityManagerFactory factory;
	EntityManager em;
	
	// Chercher la liste des Topics
	@SuppressWarnings("unchecked")
	public List<Topic> findAll() {
		factory = Persistence.createEntityManagerFactory("Forum");
		em = factory.createEntityManager();
		
		List<Topic> topics;
		Query allTopicQuery = em.createNamedQuery("SELECT * FROM topic", Topic.class);
		System.out.println(allTopicQuery + "Hello");

		try {
			topics = allTopicQuery.getResultList();
		} catch (NoResultException nre) {
			topics = null;
		} finally {
			em.close();
			factory.close();
		}
		return topics;
	}
	
	
	
	  // Chercher un seul chien
	  //public Dog findById(final String id) {
	  //}
	  // Mettre à jour un chien
	  //public Dog update(final Dog dog) {
	  //}
	  // Effacer un chien
	  //public void remove(final String id) {
	  //}
	  // Ajouter un chien
	  //public Dog add(final Dog dog) {
	  //}
	
}

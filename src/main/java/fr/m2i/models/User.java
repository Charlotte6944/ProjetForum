package fr.m2i.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="findUser", query="select u from User u where u.login = :lo and u.password = :up"),
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToMany
	private List<Topic> topics = new ArrayList<>();
	
	@OneToMany
	private List<Comment> comments = new ArrayList<>();
	
	@Basic
	@Column(name="nickname")
	private String nickname;
	
	@Basic
	@Column(name="email")
	private String email;
	
	@Basic
	@Column(name="login")
	private String login;
	
	@Basic
	@Column(name="password")
	private String password;
	
	// constructeur vide
	public User() {
		
	}
	
	// constructeur
	public User(String nickname, String email, String login, String password) {
		this.setEmail(email);
		this.setLogin(login);
		this.setNickname(nickname);
		this.setPassword(password);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}

package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
/*@Entity
@Table(name = "users", catalog = "cookbook", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Users implements java.io.Serializable {

	private String username;
	private String password;
	private String firstname;
	private String secondname;
	private String email;
	private String name;
	private String surname;
	private Set<Comments> commentses = new HashSet<Comments>(0);
	private Set<Recipes> recipeses = new HashSet<Recipes>(0);

	public Users() {
	}

	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Users(String username, String password, String firstname, String secondname, String email, String name,
			String surname, Set<Comments> commentses, Set<Recipes> recipeses) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.secondname = secondname;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.commentses = commentses;
		this.recipeses = recipeses;
	}

	@Id

	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "firstname", length = 40)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "secondname", length = 40)
	public String getSecondname() {
		return this.secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	@Column(name = "email", unique = true, length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname")
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Comments> getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set<Comments> commentses) {
		this.commentses = commentses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Recipes> getRecipeses() {
		return this.recipeses;
	}

	public void setRecipeses(Set<Recipes> recipeses) {
		this.recipeses = recipeses;
	}

}*/

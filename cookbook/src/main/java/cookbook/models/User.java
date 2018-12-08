package cookbook.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	private String username;
	
	private String password;
	
	private String firstname;
	
	private String secondname;
	
	private String email;
	
	private Set<Comments> commentses = new HashSet<Comments>(0);
	
	private Set<Recipes> recipeses = new HashSet<Recipes>(0);

	public String role = "ROLE_USER";

	
	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String firstname, String secondname, String email,
			Set<Comments> commentses, Set<Recipes> recipeses) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.secondname = secondname;
		this.email = email;
		this.commentses = commentses;
		this.recipeses = recipeses;
	}

	public User(String username, String password, String email, String firstName, String secondName) {

		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstName;
		this.secondname = secondName;
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

	@Transient
	public String getRole() {
		return role;
	}

	@Transient
	public void setRole(String role) {
		this.role = role;
	}

}

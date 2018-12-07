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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	private String username;
	private String password;
	private String firstname;
	private String secondname;
	private String email;
	private String name;
	private String surname;
	private Set<Comments> commentses = new HashSet<Comments>(0);
	private Set<Recipes> recipeses = new HashSet<Recipes>(0);
	
	public static String role = "ROLE_USER";

	
	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String firstname, String secondname, String email, String name,
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
	
	public User(String username, String password, String email, String name, String surname) {

		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
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

	
	/*@Id
	@Size(max = 30)
	private String username;

	@NotBlank
	private String password;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	private String name;

	private String surname;
	
	@Transient
	public static String role = "ROLE_USER";

	public User() {

	}

	public User(String username, String password, String email, String name, String surname) {

		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}*/

	@Transient
	public String getRole() {
		return role;
	}
	
	@Transient
	public void setRole(String role) {
		this.role = role;
	}


	
	

}

package cookbook.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Size(max = 30)
	private String username;

	@NotBlank
	@Size(max = 100)
	private String password;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	private String name;

	private String surname;

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
	}

}

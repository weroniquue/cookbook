package cookbook.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cookbook.models.User;

public class UserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String username;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String firstName;

	@JsonIgnore
	private String secondName;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String username, String email, String password, String firstName, String secondName,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.secondName =secondName;
		this.authorities = authorities;

	}

	public static UserPrincipal create(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new UserPrincipal(user.getUsername(), user.getEmail(),user.getPassword(), user.getFirstname(),
				user.getSecondname(), authorities);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}


	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(username, that.username);
	}

	@Override
	public int hashCode() {

		return Objects.hash(username);
	}

}

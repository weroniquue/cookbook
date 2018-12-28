package cookbook.models.audit;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(value = { "createdBy" }, allowGetters = true)
public abstract class UserDateAudit extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@CreatedBy
	@Column(updatable = false)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

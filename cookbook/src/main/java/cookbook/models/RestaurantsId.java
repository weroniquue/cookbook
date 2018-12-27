package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RestaurantsId generated by hbm2java
 */
@Embeddable
public class RestaurantsId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String city;

	public RestaurantsId() {
	}

	public RestaurantsId(String name, String city) {
		this.name = name;
		this.city = city;
	}

	@Column(name = "name", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "city", nullable = false, length = 40)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RestaurantsId))
			return false;
		
		RestaurantsId castOther = (RestaurantsId) other;
		

		return ((this.getName() == castOther.getName()) || (this.getName() != null && castOther.getName() != null
				&& this.getName().equals(castOther.getName())))
				&& ((this.getCity() == castOther.getCity()) || (this.getCity() != null && castOther.getCity() != null
						&& this.getCity().equals(castOther.getCity())));
		
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (getCity() == null ? 0 : this.getCity().hashCode());
		return result;
	}

}
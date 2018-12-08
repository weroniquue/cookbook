package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Restaurants generated by hbm2java
 */
@Entity
@Table(name = "restaurants")
public class Restaurants implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RestaurantsId id;
	private String address;
	private String code;
	private Set<Recipes> recipeses = new HashSet<Recipes>(0);

	public Restaurants() {
	}

	public Restaurants(RestaurantsId id) {
		this.id = id;
	}

	public Restaurants(RestaurantsId id, String address, String code, Set<Recipes> recipeses) {
		this.id = id;
		this.address = address;
		this.code = code;
		this.recipeses = recipeses;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, length = 40)),
			@AttributeOverride(name = "city", column = @Column(name = "city", nullable = false, length = 40)) })
	public RestaurantsId getId() {
		return this.id;
	}

	public void setId(RestaurantsId id) {
		this.id = id;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "code", length = 10)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipesinrestaurant", joinColumns = {
			@JoinColumn(name = "restaurants_name", nullable = false, updatable = false),
			@JoinColumn(name = "restaurants_city", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "recipes_id", nullable = false, updatable = false) })
	public Set<Recipes> getRecipeses() {
		return this.recipeses;
	}

	public void setRecipeses(Set<Recipes> recipeses) {
		this.recipeses = recipeses;
	}
}

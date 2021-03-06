package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * Recipes generated by hbm2java
 */
@Entity
@Table(name = "recipes")
public class Recipes implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Category category;
	private Cuisine cuisine;
	private User user;
	private String title;
	private String description;
	private Set<Photos> photoses = new HashSet<Photos>(0);
	private Set<Restaurants> restaurants = new HashSet<Restaurants>(0);
	private Set<Amountingredients> amountingredientses = new HashSet<Amountingredients>(0);
	private Set<Steps> stepses = new HashSet<Steps>(0);
	private Set<Comments> commentses = new HashSet<Comments>(0);

	public Recipes() {
	}

	public Recipes(Cuisine cuisine, User user, String title, String description) {
		this.cuisine = cuisine;
		this.user = user;
		this.title = title;
		this.description = description;
	}

	public Recipes(Category category, Cuisine cuisine, User user, String title, String description,
			Set<Photos> photoses, Set<Restaurants> restaurants, Set<Amountingredients> amountingredientses,
			Set<Steps> stepses, Set<Comments> commentses) {
		this.category = category;
		this.cuisine = cuisine;
		this.user = user;
		this.title = title;
		this.description = description;
		this.photoses = photoses;
		this.restaurants = restaurants;
		this.amountingredientses = amountingredientses;
		this.stepses = stepses;
		this.commentses = commentses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_name",nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuisine_name")
	public Cuisine getCuisine() {
		return this.cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_username", nullable = false)
	public User getUsers() {
		return this.user;
	}

	public void setUsers(User users) {
		this.user = users;
	}

	@Column(name = "title", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", nullable = false, length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recipes", cascade =CascadeType.REMOVE)
	public Set<Photos> getPhotoses() {
		return this.photoses;
	}

	public void setPhotoses(Set<Photos> photoses) {
		this.photoses = photoses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipesinrestaurant",
		joinColumns = {
			@JoinColumn(name = "recipes_id", nullable = false, updatable = false) },
			inverseJoinColumns = {
					@JoinColumn(name = "restaurants_name",referencedColumnName="name", nullable = false, updatable = false),
					@JoinColumn(name = "restaurants_city",referencedColumnName="city", nullable = false, updatable = false)
			})
	public Set<Restaurants> getRestaurants() {
		return this.restaurants;
	}

	public void setRestaurants(Set<Restaurants> restaurants) {
		this.restaurants = restaurants;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recipes", cascade = CascadeType.REMOVE)
	public Set<Amountingredients> getAmountingredientses() {
		return this.amountingredientses;
	}

	public void setAmountingredientses(Set<Amountingredients> amountingredientses) {
		this.amountingredientses = amountingredientses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recipes", cascade = CascadeType.REMOVE)
	@OrderBy("id ASC")
	public Set<Steps> getStepses() {
		return this.stepses;
	}

	public void setStepses(Set<Steps> stepses) {
		this.stepses = stepses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recipes", cascade = CascadeType.REMOVE)
	public Set<Comments> getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set<Comments> commentses) {
		this.commentses = commentses;
	}

}

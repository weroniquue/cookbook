package cookbook.payloads.recipes;

import java.util.List;
import java.util.Set;

import cookbook.models.Ingredients;
import cookbook.models.Restaurants;
import cookbook.payloads.comments.CommentResponse;
import cookbook.payloads.users.UserProfile;

public class RecipeResponse {

	private Integer id;
	private String category;
	private String cuisine;
	private String tittle;
	private String description;
	private UserProfile createdBy;
	
	//Kroki inaczej
	private Set<String> steps;
	private Set<String> photos;
	private Set<Restaurants> restaurants;
	//Składniki inaczej
	private Set<Ingredients> ingredients;
	
	private List<CommentResponse> comments;

	public RecipeResponse() {
	}

	public RecipeResponse(Integer id, String category, String cuisine, String tittle, String description,
			Set<Restaurants> restaurants) {
		super();
		this.id = id;
		this.category = category;
		this.cuisine = cuisine;
		this.tittle = tittle;
		this.description = description;
		this.restaurants = restaurants;
	}

	public RecipeResponse(Integer id, String category, String cuisine, String tittle, String description) {
		super();
		this.id = id;
		this.category = category;
		this.cuisine = cuisine;
		this.tittle = tittle;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}



	public Set<String> getSteps() {
		return steps;
	}

	public void setSteps(Set<String> steps) {
		this.steps = steps;
	}

	public Set<String> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<String> photos) {
		this.photos = photos;
	}

	public Set<Restaurants> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurants> restaurants) {
		this.restaurants = restaurants;
	}

	public Set<Ingredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}

	public UserProfile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserProfile createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
	

}

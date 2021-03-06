package cookbook.payloads.recipes;

import java.util.List;
import java.util.Set;

import cookbook.models.IngredientResponse;
import cookbook.payloads.comments.CommentResponse;
import cookbook.payloads.restaurants.RestaurantResponse;
import cookbook.payloads.users.UserProfile;

public class RecipeResponse {

	private Integer id;
	private String category;
	private String cuisine;
	private String title;
	private String description;
	private UserProfile createdBy;

	private List<StepsRequest> steps;
	private Set<String> photos;

	private Set<RestaurantResponse> restaurants;
	private List<CommentResponse> comments;

	private Set<IngredientResponse> ingredients;

	public RecipeResponse() {
	}

	public RecipeResponse(Integer id, String category, String cuisine, String title, String description) {
		super();
		this.id = id;
		this.category = category;
		this.cuisine = cuisine;
		this.title = title;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<StepsRequest> getSteps() {
		return steps;
	}

	public void setSteps(List<StepsRequest> steps) {
		this.steps = steps;
	}

	public List<CommentResponse> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponse> comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public Set<String> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<String> photos) {
		this.photos = photos;
	}

	public Set<RestaurantResponse> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<RestaurantResponse> restaurants) {
		this.restaurants = restaurants;
	}

	public Set<IngredientResponse> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientResponse> ingredients) {
		this.ingredients = ingredients;
	}

	public UserProfile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserProfile createdBy) {
		this.createdBy = createdBy;
	}

}

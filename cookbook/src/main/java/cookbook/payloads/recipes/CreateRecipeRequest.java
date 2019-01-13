package cookbook.payloads.recipes;

import java.util.HashSet;
import java.util.Set;

import cookbook.payloads.ingredients.AddIngredientToRecipeRequest;

public class CreateRecipeRequest {

	String title;
	String description;
	String cuisineName;
	String category;

	private Set<AddIngredientToRecipeRequest> ingredients = new HashSet<>();
	private Set<String> photos = new HashSet<>();
	private Set<StepsRequest> steps = new HashSet<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<AddIngredientToRecipeRequest> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<AddIngredientToRecipeRequest> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<String> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<String> photos) {
		this.photos = photos;
	}

	public Set<StepsRequest> getSteps() {
		return steps;
	}

	public void setSteps(Set<StepsRequest> steps) {
		this.steps = steps;
	}

}

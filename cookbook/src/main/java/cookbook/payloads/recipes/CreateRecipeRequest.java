package cookbook.payloads.recipes;

import java.util.HashSet;
import java.util.Set;

public class CreateRecipeRequest {

	String tittle;
	String description;
	String cuisineName;
	String category;

	private Set<IngredientsRequest> ingredients = new HashSet<>();
	private Set<String> photos = new HashSet<>();
	private Set<StepsRequest> steps = new HashSet<>();

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCousineName() {
		return cuisineName;
	}

	public void setCousineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<IngredientsRequest> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientsRequest> ingredients) {
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

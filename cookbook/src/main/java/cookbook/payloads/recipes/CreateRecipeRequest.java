package cookbook.payloads.recipes;

public class CreateRecipeRequest {

	String tittle;
	String description;
	String cousineName;
	String category;
	
	
	//steps
	//ingrednients;
	
	

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
		return cousineName;
	}

	public void setCousineName(String cousineName) {
		this.cousineName = cousineName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}

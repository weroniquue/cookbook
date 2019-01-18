package cookbook.payloads.ingredients;

public class AddIngredientToRecipeRequest {

	private String name;
	private Float amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}

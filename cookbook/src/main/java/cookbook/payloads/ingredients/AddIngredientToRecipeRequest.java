package cookbook.payloads.ingredients;

public class AddIngredientToRecipeRequest {

	private String name;
	private Long amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}

package cookbook.models;

public class IngredientResponse {
	
	private float amount;
	private String name;
	private String unit;
	
	public IngredientResponse(float amount, String name, String unit) {
		super();
		this.amount = amount;
		this.name = name;
		this.unit = unit;
	}

	
	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	

}

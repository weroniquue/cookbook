package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmountingredientsId generated by hbm2java
 */
@Embeddable
public class AmountingredientsId implements java.io.Serializable {

	private float amount;
	private String ingredientsName;
	private int recipesId;

	public AmountingredientsId() {
	}

	public AmountingredientsId(float amount, String ingredientsName, int recipesId) {
		this.amount = amount;
		this.ingredientsName = ingredientsName;
		this.recipesId = recipesId;
	}

	@Column(name = "amount", nullable = false, precision = 10)
	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Column(name = "ingredients_name", nullable = false, length = 30)
	public String getIngredientsName() {
		return this.ingredientsName;
	}

	public void setIngredientsName(String ingredientsName) {
		this.ingredientsName = ingredientsName;
	}

	@Column(name = "recipes_id", nullable = false)
	public int getRecipesId() {
		return this.recipesId;
	}

	public void setRecipesId(int recipesId) {
		this.recipesId = recipesId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AmountingredientsId))
			return false;
		AmountingredientsId castOther = (AmountingredientsId) other;

		return (this.getAmount() == castOther.getAmount())
				&& ((this.getIngredientsName() == castOther.getIngredientsName())
						|| (this.getIngredientsName() != null && castOther.getIngredientsName() != null
								&& this.getIngredientsName().equals(castOther.getIngredientsName())))
				&& (this.getRecipesId() == castOther.getRecipesId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAmount();
		result = 37 * result + (getIngredientsName() == null ? 0 : this.getIngredientsName().hashCode());
		result = 37 * result + this.getRecipesId();
		return result;
	}

}

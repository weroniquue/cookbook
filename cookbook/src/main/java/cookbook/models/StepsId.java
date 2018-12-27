package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * StepsId generated by hbm2java
 */
@Embeddable
public class StepsId implements java.io.Serializable {

	private int number;
	private String description;
	private int recipesId;

	public StepsId() {
	}

	public StepsId(int number, String description, int recipesId) {
		this.number = number;
		this.description = description;
		this.recipesId = recipesId;
	}

	@Column(name = "number", nullable = false)
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "description", nullable = false, length = 80)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		if (!(other instanceof StepsId))
			return false;
		StepsId castOther = (StepsId) other;

		return (this.getNumber() == castOther.getNumber())
				&& ((this.getDescription() == castOther.getDescription())
						|| (this.getDescription() != null && castOther.getDescription() != null
								&& this.getDescription().equals(castOther.getDescription())))
				&& (this.getRecipesId() == castOther.getRecipesId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getNumber();
		result = 37 * result + (getDescription() == null ? 0 : this.getDescription().hashCode());
		result = 37 * result + this.getRecipesId();
		return result;
	}

}
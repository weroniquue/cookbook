package cookbook.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "recipes")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private FoodCategory category;
	
	@NotBlank
	private String shotDescription;
	
	@NotBlank
	private String description;

	/*
	 * Dodac składniki tylko nie wiadomo w jakiej funkcji
	 * 
	 * */
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FoodCategory getCategory() {
		return category;
	}

	public void setCategory(FoodCategory category) {
		this.category = category;
	}

	public String getShotDescription() {
		return shotDescription;
	}

	public void setShotDescription(String shotDescription) {
		this.shotDescription = shotDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
}

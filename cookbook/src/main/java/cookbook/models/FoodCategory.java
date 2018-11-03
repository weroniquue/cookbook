package cookbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import cookbook.models.FoodCategoryEnum;

@Entity
@Table(name = "food_category")
public class FoodCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category_name")
	private FoodCategoryEnum categoryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodCategoryEnum getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(FoodCategoryEnum categoryName) {
		this.categoryName = categoryName;
	}
	
	

}

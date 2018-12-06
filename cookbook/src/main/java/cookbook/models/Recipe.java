package cookbook.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import cookbook.models.audit.DateAudit;

@Entity
@Table(name = "recipes")
public class Recipe extends DateAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String title;


	//SSSSSS
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "username", nullable=false)
	private User author;
	
	@ManyToOne()
	@JoinColumn(name = "category_name")
	private FoodCategory category;

	@NotBlank
	private String description;

	
	
	


}

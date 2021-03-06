package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Steps generated by hbm2java
 */
@Entity
@Table(name = "steps")
public class Steps implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StepsId id;
	private Recipes recipes;

	public Steps() {
	}

	public Steps(StepsId id, Recipes recipes) {
		this.id = id;
		this.recipes = recipes;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "number", column = @Column(name = "number", nullable = false)),
			@AttributeOverride(name = "description", column = @Column(name = "description", nullable = false, length = 400)),
			@AttributeOverride(name = "recipesId", column = @Column(name = "recipes_id", nullable = false)) })
	public StepsId getId() {
		return this.id;
	}

	public void setId(StepsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipes_id", nullable = false, insertable = false, updatable = false)
	public Recipes getRecipes() {
		return this.recipes;
	}

	public void setRecipes(Recipes recipes) {
		this.recipes = recipes;
	}

}

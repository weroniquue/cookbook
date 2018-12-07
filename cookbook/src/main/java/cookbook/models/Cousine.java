package cookbook.models;
// Generated 2018-12-07 22:21:43 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cousine generated by hbm2java
 */
@Entity
@Table(name = "cousine")
public class Cousine implements java.io.Serializable {

	private String name;
	private Set<Recipes> recipeses = new HashSet<Recipes>(0);

	public Cousine() {
	}

	public Cousine(String name) {
		this.name = name;
	}

	public Cousine(String name, Set<Recipes> recipeses) {
		this.name = name;
		this.recipeses = recipeses;
	}

	@Id

	@Column(name = "name", unique = true, nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cousine")
	public Set<Recipes> getRecipeses() {
		return this.recipeses;
	}

	public void setRecipeses(Set<Recipes> recipeses) {
		this.recipeses = recipeses;
	}

}

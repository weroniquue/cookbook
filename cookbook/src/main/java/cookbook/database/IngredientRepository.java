package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cookbook.models.Ingredients;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredients, String>{

	Optional<Ingredients> findByName(String name);
	
	boolean existsByName(String name);
	
	@Query(nativeQuery = true,value = "call ingredientsProcedure")
	List<Ingredients> getAllIngredients();
	

	
	
}

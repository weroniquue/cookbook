package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Recipes;
import cookbook.models.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipes, Integer>{
	
	Optional<Recipes> findById(Integer id);
	
	List<Recipes> findAll();
	
	List<Recipes> findByUser(User user);
	
	//List<Recipes> findByCategoryName(String categoryName);
	
	List<Recipes> findByTittle(String tittle);
	
	List<Recipes> findByCousineName(String name);
	
}

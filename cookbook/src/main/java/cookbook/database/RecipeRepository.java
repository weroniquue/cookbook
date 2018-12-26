package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Category;
import cookbook.models.Cuisine;
import cookbook.models.Recipes;
import cookbook.models.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipes, Integer>{
	
	Optional<Recipes> findById(Integer id);
	
	List<Recipes> findAll();
	
	List<Recipes> findByUsers(User user);
	
	Page<Recipes> findByCategory(Category category, Pageable pageable);
	
	Page<Recipes> findByCuisine(Cuisine cuisine, Pageable pageable);
	
	List<Recipes> findByTittle(String tittle);
	
	List<Recipes> findByCuisineName(String name);
	
	long countByCuisineName(String name);
	
	long countByUsers(User user);
	
	
	
	
}

package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Photos;
import cookbook.models.Recipes;

@Repository
public interface PhotoRepository extends JpaRepository<Photos, String>{
	
	Optional<Photos> findByPath(String path);
	
	List<Photos> findAllByRecipes(Recipes recipe);
}

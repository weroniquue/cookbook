package cookbook.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	
	boolean existsById(String name);
	
	Optional<Category> findByName(String name);
}

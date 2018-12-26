package cookbook.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, String>{

	boolean existsById(String name);
	
	Optional<Cuisine> findByName(String name);
	
}

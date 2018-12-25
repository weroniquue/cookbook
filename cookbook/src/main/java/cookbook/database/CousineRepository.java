package cookbook.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Cousine;

@Repository
public interface CousineRepository extends JpaRepository<Cousine, String>{

	boolean existsById(String name);
	
	Optional<Cousine> findByName(String name);
	
}

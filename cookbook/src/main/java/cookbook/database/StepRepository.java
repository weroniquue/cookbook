package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Recipes;
import cookbook.models.Steps;
import cookbook.models.StepsId;

@Repository
public interface StepRepository extends JpaRepository<Steps, StepsId>{
	
	Optional<Steps> findById(StepsId id);
	
	List<Steps> findByRecipes(Recipes recipe);

}

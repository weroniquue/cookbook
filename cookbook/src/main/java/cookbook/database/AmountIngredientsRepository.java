package cookbook.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Amountingredients;
import cookbook.models.AmountingredientsId;
import cookbook.models.Recipes;

@Repository
public interface AmountIngredientsRepository extends JpaRepository<Amountingredients, AmountingredientsId>{
	
	List<Amountingredients> findByRecipes(Recipes recipe);
	

}

package cookbook.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Amountingredients;
import cookbook.models.AmountingredientsId;

@Repository
public interface AmountIngredientsRepository extends JpaRepository<Amountingredients, AmountingredientsId>{
	
	

}

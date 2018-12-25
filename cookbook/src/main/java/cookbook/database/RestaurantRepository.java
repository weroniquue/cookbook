package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Recipes;
import cookbook.models.Restaurants;
import cookbook.models.RestaurantsId;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurants, RestaurantsId>{
	
	Optional<Restaurants> findById(RestaurantsId id);
	
	List<Restaurants> findByIdName(String name);
	
	List<Restaurants> findByIdCity(String city);
	
	List<Recipes> findByRecipes_Id(Integer id);
	
	Boolean existsByIdName(String name);

}

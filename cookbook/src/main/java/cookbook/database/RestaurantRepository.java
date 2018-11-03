package cookbook.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Restaurant;


@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
	
	Optional<Restaurant> findById(Long restaurantId);
	
	List<Restaurant> findByCity(String city);
	
	
	
}

package cookbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cookbook.database.RestaurantRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Restaurants;
import cookbook.models.RestaurantsId;
import cookbook.payloads.ObjectAvailability;
import cookbook.payloads.restaurants.RestaurantDetails;

@RestController
@RequestMapping("/api")
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	
	@GetMapping("restaurant/chceckRestaurantAvailability")
	public ObjectAvailability checkRestaurantAvailability(@RequestParam(value = "restaurants") String name) {
		Boolean isAvailable = !restaurantRepository.existsByIdName(name);
		return new ObjectAvailability(isAvailable);
	}
	
	@GetMapping("/restaurants/{city}/{name}")
	public RestaurantDetails getRestaurantDetails(@PathVariable(value = "city") String city
			,@PathVariable(value = "name") String name) {
		
		Restaurants restaurant = restaurantRepository.findById(new RestaurantsId(name, city))
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant", "name and city", name + " " + city));
	
		return new RestaurantDetails();
	}
	

}

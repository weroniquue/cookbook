package cookbook.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.RestaurantRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Restaurants;
import cookbook.models.RestaurantsId;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.ObjectAvailability;
import cookbook.payloads.restaurants.createRestaurantRequest;

@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping("restaurants/chceckRestaurantAvailability")
	public ObjectAvailability checkRestaurantAvailability(@RequestParam(value = "restaurants") String name) {
		Boolean isAvailable = !restaurantRepository.existsByIdName(name);
		return new ObjectAvailability(isAvailable);
	}

	@GetMapping("/restaurants/{city}/{name}")
	public Restaurants getRestaurantDetails(@PathVariable(value = "city") String city,
			@PathVariable(value = "name") String name) {

		Restaurants restaurant = restaurantRepository.findById(new RestaurantsId(name, city))
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant", "name and city", name + " " + city));

		return restaurant;
	}

	@PostMapping("/restaurants/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addRestautant(@Valid @RequestBody createRestaurantRequest createRestaurantRequest) {
		RestaurantsId id = new RestaurantsId(createRestaurantRequest.getName(), createRestaurantRequest.getCity());
		if (restaurantRepository.existsById(id)) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		Restaurants restaurant = new Restaurants(id, createRestaurantRequest.getAddress(),
				createRestaurantRequest.getCode());

		Restaurants results = restaurantRepository.save(restaurant);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/restaurants/{city}")
				.buildAndExpand(results.getId().getCity()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Restaurant created successfully"));

	}

	@DeleteMapping("/restaurants/{city}/{name}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeRestaurants(@PathVariable(value = "city") String city,
			@PathVariable(value = "name") String name) {

		RestaurantsId id = new RestaurantsId(name, city);

		Restaurants restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Such restaurant doesn't exist", "city and name",
						city + " " + name));

		restaurantRepository.delete(restaurant);

		return new ResponseEntity<>(new ApiResponse(true, "Restaurant removed successfully"), HttpStatus.OK);

	}
	
	@GetMapping("/restaurants")
	public List<Restaurants> getAllRestaurant(){
		return restaurantRepository.findAll();
	}

}

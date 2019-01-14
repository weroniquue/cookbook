package cookbook.controllers;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.RecipeRepository;
import cookbook.database.RestaurantRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Restaurants;
import cookbook.models.RestaurantsId;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.ObjectAvailability;
import cookbook.payloads.restaurants.CreateRestaurantRequest;
import cookbook.payloads.restaurants.RestaurantResponse;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RecipeRepository recipeRepostory;

	@GetMapping("/chceckRestaurantAvailability")
	public ObjectAvailability checkRestaurantAvailability(@RequestParam(value = "restaurants") String name) {
		Boolean isAvailable = !restaurantRepository.existsByIdName(name);
		return new ObjectAvailability(isAvailable);
	}

	@GetMapping("/{city}/{name}")
	public RestaurantResponse getRestaurantDetails(@PathVariable(value = "city") String city,
			@PathVariable(value = "name") String name) {

		Restaurants restaurant = restaurantRepository.findById(new RestaurantsId(name, city))
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant", "name and city", name + " " + city));
		
		//nie działa tutaj ale działa w przepisie?
		Set<String> recipe = restaurant.getrecipes().stream().map(x -> x.getTitle()).collect(Collectors.toSet());
		restaurant.getrecipes().forEach(x-> System.out.println(x.getTitle()));

		RestaurantResponse response = new RestaurantResponse(restaurant.getId().getName(), restaurant.getAddress(),
				restaurant.getCode(), restaurant.getId().getCity(), recipe);

		return response;
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addRestautant(@Valid @RequestBody CreateRestaurantRequest createRestaurantRequest) {
		RestaurantsId id = new RestaurantsId(createRestaurantRequest.getName(), createRestaurantRequest.getCity());
		if (restaurantRepository.existsById(id)) {
			return new ResponseEntity<>(new ApiResponse(false, "Such restaurant exists!"), HttpStatus.BAD_REQUEST);
		}

		Restaurants restaurant = new Restaurants(id, createRestaurantRequest.getAddress(),
				createRestaurantRequest.getCode());

		if(createRestaurantRequest.getRecipes()!=null) {
			createRestaurantRequest.getRecipes().forEach(
					x -> restaurant.getrecipes()
					.add(recipeRepostory.findById(x).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", x))));

		}
		
		Restaurants results = restaurantRepository.save(restaurant);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/restaurants/{city}")
				.buildAndExpand(results.getId().getCity()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Restaurant was created successfully"));

	}

	@DeleteMapping("/{city}/{name}")
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

	@GetMapping("/")
	public List<RestaurantResponse> getAllRestaurant() {

		List<RestaurantResponse> restaurants = restaurantRepository.findAll().stream().map(obj -> {

			Set<String> recipe = obj.getrecipes()
					.stream()
					.map(x -> x.getTitle())
					.collect(Collectors.toSet());

			RestaurantResponse response = new RestaurantResponse(
					obj.getId().getName(), 
					obj.getAddress(),
					obj.getCode(),
					obj.getId().getCity(),
					recipe);

			return response;
		}).collect(Collectors.toList());

		return restaurants;

	}

}

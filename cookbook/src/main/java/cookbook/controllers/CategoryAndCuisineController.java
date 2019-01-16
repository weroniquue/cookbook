package cookbook.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.CategoryRepository;
import cookbook.database.CuisineRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Category;
import cookbook.models.Cuisine;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.PagedResponse;
import cookbook.payloads.recipes.RecipeResponse;
import cookbook.security.CurrentUser;
import cookbook.security.UserPrincipal;
import cookbook.service.RecipeService;
import cookbook.util.AppConstants;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CategoryAndCuisineController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CuisineRepository cuisineRepository;
	
	@Autowired
	private RecipeService recipeService;

	@GetMapping("/category/{category}")
	public PagedResponse<RecipeResponse> getAllRecipesByCategory(
			@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
			@PathVariable(value = "category") String category){
		return recipeService.getByCategory(currentUser, page, size, category);
		
	}
	
	@GetMapping("/category/all")
	public List<String> getCategories() {
		return recipeService.getAllCategories();
	}
	
	@PostMapping("/category")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addCategory(@RequestParam Map<String, String> category) {
		
		if (categoryRepository.existsById(category.get("category"))) {
			return new ResponseEntity<>(new ApiResponse(false, "Category exists!"), HttpStatus.BAD_REQUEST);
		}

		Category newCategory = new Category(category.get("category"));

		Category result = categoryRepository.save(newCategory);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/category/{category}")
				.buildAndExpand(result.getName()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Category added successfully"));

	}

	@DeleteMapping("/category/{category}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeCategory(@PathVariable(value = "category") String category) {

		categoryRepository.findById(category)
				.orElseThrow(() -> new ResourceNotFoundException("Such category doesn't exist", "category", category));

		categoryRepository.deleteById(category);

		return new ResponseEntity<>(new ApiResponse(true, "Category removed successfully"), HttpStatus.OK);
	}

	@GetMapping("/cuisine/{cuisine}")
	public PagedResponse<RecipeResponse> getAllRecipesByCousine(
			@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
			@PathVariable(value = "cuisine") String cuisine){
		return recipeService.getByCousine(currentUser, page, size, cuisine);
	}
	
	
	@PostMapping("/cuisine")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addCousine(@RequestParam Map<String, String> cuisine) {
		
		if (cuisineRepository.existsById(cuisine.get("cuisine"))) {
			return new ResponseEntity<>(new ApiResponse(false, "Cuisine exists!"), HttpStatus.BAD_REQUEST);
		}

		Cuisine newCousine = new Cuisine(cuisine.get("cuisine"));

		Cuisine result = cuisineRepository.save(newCousine);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cuisine/{cuisine}")
				.buildAndExpand(result.getName()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Cuisine added successfully"));

	}

	@DeleteMapping("/cuisine/{cuisine}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeCousine(@PathVariable(value = "cuisine") String cuisine) {

		cuisineRepository.findById(cuisine)
				.orElseThrow(() -> new ResourceNotFoundException("Such cuisine doesn't exist", "cuisine", cuisine));

		cuisineRepository.deleteById(cuisine);

		return new ResponseEntity<>(new ApiResponse(true, "Cuisine removed successfully"), HttpStatus.OK);
	}
	
	@GetMapping("/cuisine/all")
	public List<String> getCuisine() {
		return recipeService.getAllCuisine();
	}

}

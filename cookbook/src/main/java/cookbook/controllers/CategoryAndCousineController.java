package cookbook.controllers;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.CategoryRepository;
import cookbook.database.CousineRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Category;
import cookbook.models.Cousine;
import cookbook.payloads.ApiResponse;

@RestController
@RequestMapping("/api")
public class CategoryAndCousineController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CousineRepository cousineRepository;

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

	@PostMapping("/cousine")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addCousine(@RequestParam Map<String, String> cousine) {
		
		if (cousineRepository.existsById(cousine.get("cousine"))) {
			return new ResponseEntity<>(new ApiResponse(false, "Cousine exists!"), HttpStatus.BAD_REQUEST);
		}

		Cousine newCousine = new Cousine(cousine.get("cousine"));

		Cousine result = cousineRepository.save(newCousine);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cousine/{cousine}")
				.buildAndExpand(result.getName()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Cousine added successfully"));

	}

	@DeleteMapping("/cousine/{cousine}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeCousine(@PathVariable(value = "cousine") String cousine) {

		cousineRepository.findById(cousine)
				.orElseThrow(() -> new ResourceNotFoundException("Such cousine doesn't exist", "cousine", cousine));

		cousineRepository.deleteById(cousine);

		return new ResponseEntity<>(new ApiResponse(true, "Cousine removed successfully"), HttpStatus.OK);
	}

}

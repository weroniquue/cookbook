package cookbook.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cookbook.database.IngredientRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Ingredients;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.ingredients.CreateIngredientRequest;
import cookbook.security.CurrentUser;
import cookbook.security.UserPrincipal;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
	
	@Autowired	
	private IngredientRepository ingredientRepository;
	
	@PostMapping("/")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createIngredient(
			@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody CreateIngredientRequest request){
		
		if(ingredientRepository.existsByName(request.getName())){
			return new ResponseEntity<>(new ApiResponse(false, "Ingredient exists"), HttpStatus.BAD_REQUEST);
		}
		
		ingredientRepository.save(new Ingredients(request.getName(), request.getUnit()));
		
		return new ResponseEntity<>(new ApiResponse(true, "Ingredient was created successfully"), HttpStatus.OK);
	
	}
	
	@DeleteMapping("/")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeIngredient(@RequestParam Map<String, String> ingredientName){
		
		Ingredients ingredient = ingredientRepository.findByName(ingredientName.get("name"))
				.orElseThrow(() -> new ResourceNotFoundException("Ingredient", "name", ingredientName.get("name")));
		
		ingredientRepository.delete(ingredient);
		return new ResponseEntity<>(new ApiResponse(true, "Ingredient was removed successfully"), HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public List<CreateIngredientRequest> getIngredients(){
		return ingredientRepository.getAllIngredients()
				.stream()
				.map( ingr ->{
					return new CreateIngredientRequest(ingr.getName(), ingr.getUnit());
				}).collect(Collectors.toList());
	}

}

package cookbook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
	
	@GetMapping("/")
	public String hallo() {
		return "Hallo";
	}
	


}

package cookbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cookbook.database.RecipeRepository;
import cookbook.models.Recipes;
import cookbook.payloads.PagedResponse;
import cookbook.security.CurrentUser;
import cookbook.security.UserPrincipal;
import cookbook.service.RecipeService;
import cookbook.util.AppConstants;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeService recipeService;

	@GetMapping
	public PagedResponse<Recipes> getRecipes(@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		
		return recipeService.getAllRecipes(currentUser, page, size);
	}

}

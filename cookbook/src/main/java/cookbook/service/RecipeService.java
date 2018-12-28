package cookbook.service;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.AmountIngredientsRepository;
import cookbook.database.CategoryRepository;
import cookbook.database.CommentRepository;
import cookbook.database.CuisineRepository;
import cookbook.database.IngredientRepository;
import cookbook.database.PhotoRepository;
import cookbook.database.RecipeRepository;
import cookbook.database.StepRepository;
import cookbook.database.UserRepository;
import cookbook.exception.BadRequestException;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Amountingredients;
import cookbook.models.AmountingredientsId;
import cookbook.models.Category;
import cookbook.models.Comments;
import cookbook.models.Cuisine;
import cookbook.models.IngredientResponse;
import cookbook.models.Ingredients;
import cookbook.models.Photos;
import cookbook.models.Recipes;
import cookbook.models.Steps;
import cookbook.models.StepsId;
import cookbook.models.User;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.PagedResponse;
import cookbook.payloads.comments.CommentResponse;
import cookbook.payloads.comments.CreateCommentRequest;
import cookbook.payloads.recipes.CreateRecipeRequest;
import cookbook.payloads.recipes.RecipeResponse;
import cookbook.payloads.recipes.StepsRequest;
import cookbook.payloads.restaurants.RestaurantResponse;
import cookbook.payloads.users.UserProfile;
import cookbook.security.UserPrincipal;
import cookbook.util.AppConstants;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private AmountIngredientsRepository amountIngredientsRepository;

	public PagedResponse<RecipeResponse> getAllRecipes(UserPrincipal currentUser, int page, int size) {
		validatePageNumberAndSize(page, size);

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Recipes> recipes = recipeRepository.findAll(pageable);

		if (recipes.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), recipes.getNumber(), recipes.getSize(),
					recipes.getTotalElements(), recipes.getTotalPages(), recipes.isLast());
		}

		List<RecipeResponse> recipeResponse = recipeRepository.findAll().stream().map(x -> recipeMapper(x))
				.collect(Collectors.toList());

		return new PagedResponse<>(recipeResponse, page, size, recipes.getTotalElements(), recipes.getTotalPages(),
				recipes.isLast());

	}

	public PagedResponse<RecipeResponse> getBy(Page<Recipes> recipes, int page, int size) {

		if (recipes.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), recipes.getNumber(), recipes.getSize(),
					recipes.getTotalElements(), recipes.getTotalPages(), recipes.isLast());
		}

		List<RecipeResponse> recipeResponse = recipes.stream().map(x -> recipeMapper(x)).collect(Collectors.toList());

		return new PagedResponse<>(recipeResponse, page, size, recipes.getTotalElements(), recipes.getTotalPages(),
				recipes.isLast());
	}

	public PagedResponse<RecipeResponse> getByCategory(UserPrincipal currentUser, int page, int size, String cat) {
		validatePageNumberAndSize(page, size);

		Category category = categoryRepository.findById(cat)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryName", cat));

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Recipes> recipes = recipeRepository.findByCategory(category, pageable);

		return getBy(recipes, page, size);

	}

	public PagedResponse<RecipeResponse> getByCousine(UserPrincipal currentUser, int page, int size,
			String cousineName) {
		validatePageNumberAndSize(page, size);

		Cuisine cuisine = cuisineRepository.findById(cousineName)
				.orElseThrow(() -> new ResourceNotFoundException("Cousine", "cousineName", cousineName));

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Recipes> recipes = recipeRepository.findByCuisine(cuisine, pageable);

		return getBy(recipes, page, size);

	}

	public RecipeResponse getRecipeById(Integer recipeId) {

		Recipes recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

		return recipeMapper(recipe);
	}

	public RecipeResponse recipeMapper(Recipes recipe) {

		RecipeResponse response = new RecipeResponse(recipe.getId(), recipe.getCategory().getName(),
				recipe.getCuisine().getName(), recipe.getTittle(), recipe.getDescription());

		response.setRestaurants(recipe.getRestaurants()
				.stream()
				.map(restaurant -> {
					return new RestaurantResponse(restaurant.getId().getName(),
							restaurant.getAddress(),
							restaurant.getCode(),
							restaurant.getId().getCity(),
							null);//DO POPRAWY
		}).collect(Collectors.toSet()));

		response.setPhotos(recipe.getPhotoses()
				.stream()
				.map(photo -> {
					return photo.getPath();
				}).collect(Collectors.toSet()));

		response.setSteps(recipe.getStepses()
				.stream()
				.map(step -> {
					return new StepsRequest(step.getId().getNumber(), step.getId().getDescription());
				}).collect(Collectors.toSet()));

		response.setComments(getAllComment(recipe.getId()));
		
		response.setIngredients(recipe.getAmountingredientses()
				.stream()
				.map(ingr ->{
					return new IngredientResponse(ingr.getId().getAmount(), ingr.getId().getIngredientsName(), ingr.getIngredients().getUnit());
				}).collect(Collectors.toSet()));

		User user = recipe.getUsers();
		response.setCreatedBy(
				new UserProfile(user.getUsername(), user.getFirstname(), user.getSecondname(), user.getEmail()));
		
		
		return response;
	}

	public ResponseEntity<?> addRecipe(CreateRecipeRequest recipe, UserPrincipal user) {

		Cuisine cuisine = cuisineRepository.findById(recipe.getCuisineName())
				.orElseThrow(() -> new ResourceNotFoundException("Cousine", "id", recipe.getCuisineName()));
		
		Category category = categoryRepository.findById(recipe.getCategory())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", recipe.getCategory()));

		User currentUser = userRepository.findByUsername(user.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", user.getUsername()));

		
		Recipes newRecipe = new Recipes(cuisine, currentUser, "tittle", "description");
		newRecipe.setCategory(category);
		
		Recipes result = recipeRepository.save(newRecipe);
		recipe.getPhotos().forEach(
				photo -> {
					if(photoRepository.existsById(photo)) {throw new BadRequestException("Photo with name: "+photo +" exists!");}
					photoRepository.save((new Photos(photo, result)));
				});
		
		recipe.getSteps().forEach(step ->{
			stepRepository.save(new Steps(new StepsId(step.getId(), step.getDescription(), result.getId()),result));
		});

		recipe.getIngredients()
			.forEach(ingredient -> {
				Ingredients foundIngredient =  ingredientRepository.findByName(ingredient.getName())
						.orElseThrow(()-> new ResourceNotFoundException("Ingredient", "name", ingredient.getName()));
				amountIngredientsRepository.save(new Amountingredients(new AmountingredientsId(ingredient.getAmount(), ingredient.getName(), result.getId()),
						foundIngredient, result));
			});

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/recipes/{id}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Recipe was saved successfully with id: " + result.getId()));

	}
	
	
	
	//Font!! bo czyści wszytskie składniki zdjęcia itp 
	public ResponseEntity<?> modifyRecipe(CreateRecipeRequest request, UserPrincipal user, int id) {
			
		Recipes recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));
		
		if(!recipe.getUsers().getUsername().equals(user.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "It's not your recipe!"),HttpStatus.BAD_REQUEST);
		}
		
		if(!request.getCuisineName().equals(null)) {
			Cuisine cuisine = cuisineRepository.findById(request.getCuisineName())
					.orElseThrow(() -> new ResourceNotFoundException("Cousine", "id", request.getCuisineName()));
			recipe.setCuisine(cuisine);
			
		}
		
		if(!request.getCategory().equals(null)) {
			Category category = categoryRepository.findById(request.getCategory())
					.orElseThrow(() -> new ResourceNotFoundException("Category", "id", recipe.getCategory()));
			recipe.setCategory(category);
			
		}
		
		if(!request.getTittle().equals(null)) {
			recipe.setTittle(request.getTittle());
		}
		
		if(!request.getDescription().equals(null)) {
			recipe.setDescription(request.getDescription());
		}
		
		
		//czyszczenie listy kroków przed
		if(!request.getSteps().equals(null)) {
			
			stepRepository.deleteAll(stepRepository.findByRecipes(recipe));
			
			request.getSteps().forEach(step ->{
				stepRepository.save(new Steps(new StepsId(step.getId(), step.getDescription(), recipe.getId()), recipe));
			});
		}
		
		
		
		
//		request.getIngredients()
//		.forEach(ingredient -> {
//			Ingredients foundIngredient =  ingredientRepository.findByName(ingredient.getName())
//					.orElseThrow(()-> new ResourceNotFoundException("Ingredient", "name", ingredient.getName()));
//			
//			amountIngredientsRepository.save(new Amountingredients(new AmountingredientsId(ingredient.getAmount(), ingredient.getName(), result.getId()),
//					foundIngredient, result));
//		});
		
		
//		Recipes result = recipeRepository.save(newRecipe);
//		
//		
//



		return new ResponseEntity<>(new ApiResponse(true, "Recipe was edited successfully."), HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> deleteRecipe(int id,UserPrincipal user){
		Recipes recipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));
		
		
		if(!recipe.getUsers().getUsername().equals(user.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "It's not your recipe!"),HttpStatus.BAD_REQUEST);
		}
		
		recipeRepository.delete(recipe);
		
		return new ResponseEntity<>(new ApiResponse(true, "Recipe was deleted successfully."), HttpStatus.OK);
		
	}
	

	public List<CommentResponse> getAllComment(int recipeId) {

		Recipes recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

		List<CommentResponse> comments = commentRepository
				.findAllByRecipes(recipe)
				.stream()
				.map(obj -> {
					CommentResponse response = new CommentResponse(obj.getId(),
							obj.getRecipes().getId(),
							new UserProfile(
									obj.getUsers().getUsername(),
									obj.getUsers().getFirstname(),
									obj.getUsers().getSecondname(),
									obj.getUsers().getEmail()),
							obj.getComment(),
							obj.getDate());
			return response;
		}).collect(Collectors.toList());

		return comments;
	}

	public Comments createComment(int recipeId, CreateCommentRequest request, UserPrincipal user) {

		User currentUser = userRepository.findByUsername(user.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", user.getUsername()));

		Recipes recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

		Comments comment = new Comments(recipe, currentUser, request.getComment(), new Date());

		return commentRepository.save(comment);
	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > AppConstants.MAX_PAGE_SIZE) {
			throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
		}
	}

}

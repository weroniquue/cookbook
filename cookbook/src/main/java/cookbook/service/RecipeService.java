package cookbook.service;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.CategoryRepository;
import cookbook.database.CommentRepository;
import cookbook.database.CousineRepository;
import cookbook.database.RecipeRepository;
import cookbook.database.StepRepository;
import cookbook.database.UserRepository;
import cookbook.exception.BadRequestException;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Comments;
import cookbook.models.Cousine;
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
	private CousineRepository cousineRepository;

	@Autowired
	private StepRepository stepRepository;

	@Autowired
	private CommentRepository commentRepository;

	public PagedResponse<Recipes> getAllRecipes(UserPrincipal currentUser, int page, int size) {
		validatePageNumberAndSize(page, size);

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Recipes> recipes = recipeRepository.findAll(pageable);

		if (recipes.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), recipes.getNumber(), recipes.getSize(),
					recipes.getTotalElements(), recipes.getTotalPages(), recipes.isLast());
		}

		List<Integer> recipeIds = recipes.map(Recipes::getId).getContent();

/////TO DO!!

		return new PagedResponse<>(recipeRepository.findAll(), page, size, recipes.getTotalElements(),
				recipes.getTotalPages(), recipes.isLast());

	}

	public RecipeResponse getRecipeById(Integer recipeId) {

		Recipes recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

		RecipeResponse response = new RecipeResponse(recipe.getId(), recipe.getCategory().getName(),
				recipe.getCousine().getName(), recipe.getTittle(), recipe.getDescription(), recipe.getRestaurants());

		Set<String> photos = new HashSet<>();
		recipe.getPhotoses().forEach(x -> photos.add(x.getPath()));
		response.setPhotos(photos);

		User user = recipe.getUsers();
		response.setCreatedBy(
				new UserProfile(user.getUsername(), user.getFirstname(), user.getSecondname(), user.getEmail()));

		return response;
	}

	public ResponseEntity<?> addRecipe(CreateRecipeRequest recipe, UserPrincipal user) {

		Cousine cousine = cousineRepository.findById("inna")
				.orElseThrow(() -> new ResourceNotFoundException("Cousine", "id", "inna"));

		User currentUser = userRepository.findByUsername(user.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", user.getUsername()));

		Recipes newRecipe = new Recipes(cousine, currentUser, "tittle", "description");

		Recipes result = recipeRepository.save(newRecipe);

		Steps step1 = new Steps(new StepsId(1, "Krok 1", result.getId()), result);
		stepRepository.save(step1);

		result.getStepses().add(step1);

		recipeRepository.save(result);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/recipes/{id}")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Save recipes successfully."));

	}

	public List<CommentResponse> getAllComment(int recipeId) {

		Recipes recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));

		List<CommentResponse> comments = commentRepository.findAllByRecipes(recipe).stream().map(obj -> {
			CommentResponse response = new CommentResponse(
					obj.getId(),
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

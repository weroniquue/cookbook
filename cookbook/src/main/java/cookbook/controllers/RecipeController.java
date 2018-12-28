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

import cookbook.database.CommentRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.Comments;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.PagedResponse;
import cookbook.payloads.comments.CommentResponse;
import cookbook.payloads.comments.CreateCommentRequest;
import cookbook.payloads.recipes.CreateRecipeRequest;
import cookbook.payloads.recipes.RecipeResponse;
import cookbook.security.CurrentUser;
import cookbook.security.UserPrincipal;
import cookbook.service.RecipeService;
import cookbook.util.AppConstants;


@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RecipeService recipeService;
	

	@GetMapping
	public PagedResponse<RecipeResponse> getRecipes(@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

		return recipeService.getAllRecipes(currentUser, page, size);
	}
	
	@GetMapping("/{id}")
	public RecipeResponse getRecipesById(@PathVariable(value = "id") Integer id){
		return recipeService.getRecipeById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addRecipe(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody CreateRecipeRequest createRecipeRequest) {
		return recipeService.addRecipe(createRecipeRequest,currentUser);

	}
	
	@PostMapping("/{id}/edit")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> editRecipe(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody CreateRecipeRequest modifyRecipeRequest,
			@PathVariable(value = "id") Integer id){
		return recipeService.modifyRecipe(modifyRecipeRequest, currentUser, id);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> removeRecipe(
			@PathVariable(value = "id") Integer id,
			@CurrentUser UserPrincipal currentUser){
		
		return recipeService.deleteRecipe(id, currentUser);
		
	}
	
	
	@GetMapping("/{id}/comments")
	public List<CommentResponse> getAllComments(@PathVariable(value = "id") Integer id){
		return recipeService.getAllComment(id);	
	}
	
	@PostMapping("/{id}/comments")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createComment(@PathVariable(value = "id") Integer id,
			@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody CreateCommentRequest request){
		
	    Comments comment = recipeService.createComment(id, request, currentUser);
	    
	    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{idComment}")
				.buildAndExpand(comment.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Comment added successfully with id: " + comment.getId()));
	}

	@DeleteMapping("/{id}/comments/{commentId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> deleteComment(
			@PathVariable(value = "id") Integer id,
			@CurrentUser UserPrincipal currentUser,
			@PathVariable(value = "commentId") Integer commentId){
		
		Comments comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if(comment.getUsers().getUsername().equals(currentUser.getUsername())) {
			commentRepository.delete(comment);
			return new ResponseEntity<>(new ApiResponse(true, "Comment removed successfully"), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ApiResponse(false, "It's not your comment. You can't delete it"), HttpStatus.BAD_REQUEST);
	}
}

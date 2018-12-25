package cookbook.controllers;

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

import cookbook.database.RecipeRepository;
import cookbook.database.UserRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.User;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.ObjectAvailability;
import cookbook.payloads.users.ModifyAccount;
import cookbook.payloads.users.UserProfile;
import cookbook.security.CurrentUser;
import cookbook.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping("/checkUsernameAvailability")
	public ObjectAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new ObjectAvailability(isAvailable);
	}

	@GetMapping("/checkEmailAvailability")
	public ObjectAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new ObjectAvailability(isAvailable);
	}

	@GetMapping("/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		long recipeCount = recipeRepository.countByUsers(user);
		long commentCount = 0;

		UserProfile userProfile = new UserProfile(user.getUsername(), user.getFirstname(), user.getSecondname(),
				user.getEmail(), recipeCount, commentCount);

		return userProfile;
	}

	@DeleteMapping("/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> deleteUser(@CurrentUser UserPrincipal currentUser,
			@PathVariable(value = "username") String username) {

		User user = userRepository.findByUsername(currentUser.getUsername())
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		if (!(username.equals(currentUser.getUsername()))) {
			return new ResponseEntity<>(new ApiResponse(false, "You aren't allowed to delete account another user."),
					HttpStatus.BAD_REQUEST);
		} else {
			userRepository.delete(user);
			return new ResponseEntity<>(new ApiResponse(true, "Your account was removed successfully"), HttpStatus.OK);
		}
	}

	@PostMapping("/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> modifiAccount(@CurrentUser UserPrincipal currentUser,
			@PathVariable(value = "username") String username, 
			@Valid @RequestBody ModifyAccount modifyAccount) {

	   System.out.println(username);
	   System.out.println(currentUser.getUsername());
	   
		if (username.equals(currentUser.getUsername())){
			User user = userRepository.findByUsername(currentUser.getUsername())
					.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

			user.setFirstname(modifyAccount.getFirstName());
			user.setSecondname(modifyAccount.getSecondName());
			user.setEmail(modifyAccount.getEmail());

			userRepository.save(user);

			return new ResponseEntity<>(new ApiResponse(true, "Your account was updated successfully"), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ApiResponse(false, "You aren't allowed to modify account another user."),
				HttpStatus.BAD_REQUEST);

	}

}

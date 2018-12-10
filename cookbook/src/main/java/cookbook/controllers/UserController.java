package cookbook.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cookbook.database.UserRepository;
import cookbook.exception.ResourceNotFoundException;
import cookbook.models.User;
import cookbook.payloads.ObjectAvailability;
import cookbook.payloads.users.UserProfile;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user/checkUsernameAvailability")
	public ObjectAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new ObjectAvailability(isAvailable);
	}

	@GetMapping("/user/checkEmailAvailability")
	public ObjectAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new ObjectAvailability(isAvailable);
	}
	
	@GetMapping("/user/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        //long pollCount = pollRepository.countByCreatedBy(user.getId());
        //long voteCount = voteRepository.countByUserId(user.getId());
        
        long recipeCount = 0;
        long commentCount = 0;

        UserProfile userProfile = new UserProfile(user.getUsername(), user.getFirstname(), user.getSecondname(),
        		user.getEmail(), recipeCount, commentCount);
        

        return userProfile;
    }
	
	/*@GetMapping("/user/me")
	public String getMe(Principal principal) {
		return principal.getName();
		
	}*/

}

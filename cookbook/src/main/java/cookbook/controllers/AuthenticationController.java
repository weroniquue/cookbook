package cookbook.controllers;

import java.net.URI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cookbook.database.UserRepository;
import cookbook.models.User;
import cookbook.payloads.ApiResponse;
import cookbook.payloads.JwtAuthenticationResponse;
import cookbook.payloads.LoginRequest;
import cookbook.payloads.SignUpRequest;
import cookbook.security.JwtTokenProvider;


@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        
       
        SecurityContextHolder.getContext().setAuthentication(authentication);
        

        String jwt = tokenProvider.generateToken(authentication);
        
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
        
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

       
        
        // Creating user's account
        User user = new User(signUpRequest.getUsername(),
        		signUpRequest.getPassword(),
        		signUpRequest.getEmail(),
        		signUpRequest.getName(),
        		signUpRequest.getSurname());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
               .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
        
    }
}


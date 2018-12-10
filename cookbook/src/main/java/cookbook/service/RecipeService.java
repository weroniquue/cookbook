package cookbook.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cookbook.database.RecipeRepository;
import cookbook.database.UserRepository;
import cookbook.exception.BadRequestException;
import cookbook.models.Recipes;
import cookbook.payloads.PagedResponse;
import cookbook.security.UserPrincipal;
import cookbook.util.AppConstants;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private UserRepository userRepository;

	public PagedResponse<Recipes> getAllRecipes(UserPrincipal currentUser, int page, int size) {
		validatePageNumberAndSize(page, size);
		
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
		Page<Recipes> recipes = recipeRepository.findAll(pageable);
		
		if(recipes.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(),
					recipes.getNumber(), recipes.getSize(), recipes.getTotalElements(),
					recipes.getTotalPages(), recipes.isLast());
		}
		
		
		
		
		//TO DO
		return null;
		
		

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

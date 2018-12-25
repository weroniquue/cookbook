package cookbook.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cookbook.models.Comments;
import cookbook.models.Recipes;
import cookbook.models.User;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer >{
	
	List<Comments> findAllByRecipes(Recipes recipe);
	
	long countByRecipes(Recipes recipe);
	
	long countByUsers(User user);

}

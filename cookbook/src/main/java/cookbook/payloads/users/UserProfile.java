package cookbook.payloads.users;

public class UserProfile {

	private String username;

	private String firstName;

	private String secondName;

	private String email;

	private Long recipeCount;

	private Long commentCount;

	public UserProfile(String username, String firstName, String secondName, String email) {

		this.username = username;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;

	}

	public UserProfile(String username, String firstName, String secondName, String email, Long recipeCount,
			Long commentCount) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.recipeCount = recipeCount;
		this.commentCount = commentCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRecipeCount() {
		return recipeCount;
	}

	public void setRecipeCount(Long recipeCount) {
		this.recipeCount = recipeCount;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

}

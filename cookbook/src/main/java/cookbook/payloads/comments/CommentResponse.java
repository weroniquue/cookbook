package cookbook.payloads.comments;

import java.util.Date;

import cookbook.payloads.users.UserProfile;

public class CommentResponse {

	private int id;
	private int idRecipe;
	private UserProfile users;
	private String comment;
	private Date date;


	public CommentResponse() {
		super();
	}

	public CommentResponse(int id, int idRecipe, UserProfile users, String comment, Date date) {
		super();
		this.id = id;
		this.idRecipe = idRecipe;
		this.users = users;
		this.comment = comment;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}

	public UserProfile getUsers() {
		return users;
	}

	public void setUsers(UserProfile users) {
		this.users = users;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

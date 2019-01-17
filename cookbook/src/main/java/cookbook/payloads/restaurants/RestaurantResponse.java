package cookbook.payloads.restaurants;

import java.util.HashSet;
import java.util.Set;

import cookbook.payloads.recipes.RecipeResponse;

public class RestaurantResponse {

	private String name;
	private String address;
	private String code;
	private String city;
	private Set<RecipeResponse> recipes = new HashSet<RecipeResponse>(0);

	public RestaurantResponse() {
		super();
	}

	public RestaurantResponse(String name, String address, String code, String city, Set<RecipeResponse> recipes) {
		super();
		this.name = name;
		this.address = address;
		this.code = code;
		this.city = city;
		this.recipes = recipes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<RecipeResponse> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<RecipeResponse> recipes) {
		this.recipes = recipes;
	}

}

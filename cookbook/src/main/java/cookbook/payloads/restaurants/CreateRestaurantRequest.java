package cookbook.payloads.restaurants;

import java.util.HashSet;
import java.util.Set;

public class CreateRestaurantRequest {

	private String name;
	
	private String city;
	
	private String code;
	
	private String address;
	
	private Set<Integer> recipes = new HashSet<Integer>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Integer> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Integer> recipes) {
		this.recipes = recipes;
	}
	
	
	
	
}

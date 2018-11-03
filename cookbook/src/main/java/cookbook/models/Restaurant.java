package cookbook.models;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "restaurants")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "restaurant_name")
	private String restaurantName;

	private Optional<String> street;

	private Optional<String> zipCode;

	@NotBlank
	private Optional<String> city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Optional<String> getStreet() {
		return street;
	}

	public void setStreet(Optional<String> street) {
		this.street = street;
	}

	public Optional<String> getZipCode() {
		return zipCode;
	}

	public void setZipCode(Optional<String> zipCode) {
		this.zipCode = zipCode;
	}

	public Optional<String> getCity() {
		return city;
	}

	public void setCity(Optional<String> city) {
		this.city = city;
	}

}

package com.cg.fds.dto.restaurant;

import javax.validation.constraints.NotBlank;

public class UpdateRestaurantRequest {

	@NotBlank(message="Restaurant Id cannot be null")
	private String restaurantId;

	private String restaurantName;
	private String managerName;
	private String contactNumber;

	public UpdateRestaurantRequest() {
		// Do Nothing
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}

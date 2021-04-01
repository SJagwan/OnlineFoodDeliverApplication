package com.cg.fds.dto.customers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateCustomerRequest {

	@NotBlank
	private String id;
	private String firstName;
	private String lastName;
	private String age;
	
	@Size(min=10,max=10)
	private String mobileNumber;
	private String email;

	public UpdateCustomerRequest() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

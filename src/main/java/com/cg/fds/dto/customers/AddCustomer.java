package com.cg.fds.dto.customers;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddCustomer {

	@NotBlank(message="FirstName cannot be null for Customer")
	private String firstName;
	@NotBlank(message="LastName cannot be null for Customer")
	private String lastName;
	
	@NotBlank(message="Age cannot be null for Customer")
	@Min(value=0,message="Age cannot be negative") @Max(value=100,message="Age should be less than 100")
	private String age;
	private String gender;
	
	@Size(min=10,max=10,message="Mobile number should be of length 10")
	private String mobileNumber;
	private String buildingName;
	
	@NotBlank(message="Area cannot be null for customer address")
	private String area;
	private String streetNo;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String email;

	public AddCustomer() {
		//do Nothing
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

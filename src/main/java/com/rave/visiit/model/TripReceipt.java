package com.rave.visiit.model;

import java.util.Date;
import java.util.List;

import com.rave.visiit.entity.PackageConditions;
import com.rave.visiit.entity.PersonDetail;

public class TripReceipt {
	
	private String firstName;
	
	private String lastName;
	
	private String packageName;
	
	private String tripCode;
	
	private String tripDate;
	
	private String bookDate;
	
	private String hotels;
	
	private String contact;
	
	private List<PersonDetailModel> persons;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String postalCode;
	
	private String totalAmount;
	
	private String dayNight;
	
	List<String> packageConditions;
	
	
	

	public String getDayNight() {
		return dayNight;
	}

	public void setDayNight(String dayNight) {
		this.dayNight = dayNight;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTripCode() {
		return tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}

	public String getTripDate() {
		return tripDate;
	}

	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getHotels() {
		return hotels;
	}

	public void setHotels(String hotels) {
		this.hotels = hotels;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<PersonDetailModel> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonDetailModel> persons) {
		this.persons = persons;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<String> getPackageConditions() {
		return packageConditions;
	}

	public void setPackageConditions(List<String> packageConditions) {
		this.packageConditions = packageConditions;
	}


	
	
}

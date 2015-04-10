package com.rave.visiit.model;

import java.util.Date;

import javax.persistence.Column;

import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.util.CommonUtil;

public class CustomerModel {

	private Integer customerId;

	private String customerFirstName;

	private String customerLastName;

	private String customerEmail;

	private String phone;

	private String contactAddress;

	private String contactpreference;

	private String dateOfBirth;

	private Integer age;

	private String city;

	private String state;

	private String country;

	private String postalCode;

	public CustomerModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerModel(CustRegistration customerRegistration) {
		super();
		this.setCustomerId(customerRegistration.getCustomerId());
		this.setCustomerFirstName(customerRegistration.getCustomerFirstName());
		this.setCustomerLastName(customerRegistration.getCustomerLastName());
		this.setCustomerEmail(customerRegistration.getCustomerEmail());
		this.setAge(customerRegistration.getAge());
		this.setCity(customerRegistration.getCity());
		this.setContactAddress(customerRegistration.getContactAddress());
		this.setContactpreference(customerRegistration.getContactpreference());
		this.setCountry(customerRegistration.getCountry());
		if (customerRegistration.getDateOfBirth() != null) {
			this.setDateOfBirth(CommonUtil.getDateFormat(customerRegistration
					.getDateOfBirth()));
		}
		this.setPostalCode(customerRegistration.getPostalCode());
		this.setPhone(customerRegistration.getPhone());
		this.setState(customerRegistration.getState());
	}

	// public CustRegistration update(CustRegistration customerRegistration) {
	//
	// customerRegistration.setCustomerId(this.getCustomerId());
	// customerRegistration.setCustomerName(this.getCustomerName());
	// customerRegistration.setCustomerEmail(this.getCustomerEmail());
	// customerRegistration.setAge(this.getAge());
	// customerRegistration.setCity(this.getCity());
	// customerRegistration.setContactAddress(this.getContactAddress());
	// customerRegistration.setContactpreference(this.getContactpreference());
	// customerRegistration.setCountry(this.getCountry());
	// if (customerRegistration.getDateOfBirth() != null) {
	// customerRegistration.setDateOfBirth(this
	// .getDateOfBirth());
	// }
	// customerRegistration.setPostalCode(this.getPostalCode());
	// customerRegistration.setPhone(this.getPhone());
	// customerRegistration.setState(this.getState());
	// }
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactpreference() {
		return contactpreference;
	}

	public void setContactpreference(String contactpreference) {
		this.contactpreference = contactpreference;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

}

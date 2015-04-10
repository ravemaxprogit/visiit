/**
 * 
 */
package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import com.rave.visiit.util.Constants;

/**
 * @author admin
 *
 */
@Entity
@Table(name = "customer_registration", schema = Constants.DB_CATELOG)
public class CustRegistration implements Serializable {

	private static final long serialVersionUID = -7068493051248368591L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cr_cus_id", unique = true, nullable = false)
	private Integer customerId;

	@Column(name = "cr_cus_firstname", length = 50)
	private String customerFirstName;

	@Column(name = "cr_cus_lastname", length = 50)
	private String customerLastName;

	@Column(name = "cr_cus_email", unique = true, length = 50)
	private String customerEmail;

	@Column(name = "cr_cus_pass", length = 100)
	private String password;

	@Column(name = "cr_cus_phone", unique = true, length = 15)
	private String phone;

	@Column(name = "cr_updated_on")
	private Date updatedDate;

	@Column(name = "cr_is_active")
	private Boolean isActive;

	@Column(name = "is_delete")
	private Boolean isDelete;

	@Column(name = "cr_expiration_date")
	private Date expirationDate;

	@Column(name = "cr_contact_address")
	private String contactAddress;

	@Column(name = "cr_contact_preference")
	private String contactpreference;

	@Column(name = "cr_date_of_birth")
	private Date dateOfBirth;

	@Column(name = "cr_age")
	private Integer age;

	@Column(name = "cr_city")
	private String city;

	@Column(name = "cr_state")
	private String state;

	@Column(name = "cr_country")
	private String country;

	@Column(name = "cr_postal_code")
	private String postalCode;

	public CustRegistration() {
		super();
		// TODO Auto-generated constructor stub
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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
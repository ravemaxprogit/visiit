package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "traveller_detail", catalog = Constants.DB_CATELOG)
public class TravellerDetail {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "traveller_id", unique = true, nullable = false)
	private int id;

	@Column(name = "dateoftravel")
	private Date travelDate;

	private String tripcode;

	private String firstname;

	private String lastname;

	private String email;

	private String phone;

	private String address;

	private String postelCode;

	private String country;

	private String state;

	private String city;
	
	@Column(name = "payment_commands")
	private String payementCommands;
	
	@Column(name = "status_commands")
	private String statusCommands;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_pk_id")
	private Package pack;

	@Column(name = "package_amount")
	private Double packageAmount;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "approved_status")
	private String approvedStatus;

	@Column(name = "approved_Date")
	private Date approvedDate;

	@Column(name = "payment_date")
	private Date paymentDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_customer_id")
	private CustRegistration customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_prize_id")
	private PackageCost packageCost;

	@Column(name = "voucher")
	private String voucher;

	@Column(name = "final_amount")
	private Double finalAmount;

	public TravellerDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public String getTripcode() {
		return tripcode;
	}

	public void setTripcode(String tripcode) {
		this.tripcode = tripcode;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostelCode() {
		return postelCode;
	}

	public void setPostelCode(String postelCode) {
		this.postelCode = postelCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public Double getPackageAmount() {
		return packageAmount;
	}

	public void setPackageAmount(Double packageAmount) {
		this.packageAmount = packageAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public CustRegistration getCustomer() {
		return customer;
	}

	public void setCustomer(CustRegistration customer) {
		this.customer = customer;
	}

	

	public String getPayementCommands() {
		return payementCommands;
	}

	public void setPayementCommands(String payementCommands) {
		this.payementCommands = payementCommands;
	}

	public String getStatusCommands() {
		return statusCommands;
	}

	public void setStatusCommands(String statusCommands) {
		this.statusCommands = statusCommands;
	}

	public PackageCost getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(PackageCost packageCost) {
		this.packageCost = packageCost;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

}

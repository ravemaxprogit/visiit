package com.rave.visiit.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.util.CommonUtil;

public class TravellerDetailModel {

	private int id;

	private String travelDate;

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


	private String packName;

	private Double packageAmount;

	private Double totalAmount;

	private String paymentStatus;

	private String approvedStatus;

	private String approvedDate;

	private String paymentDate;

	private String voucher;

	private Double finalAmount;

	private Integer days;

	private Integer nights;
	
	private String payementCommands;
	
	private String statusCommands;

	public TravellerDetailModel(TravellerDetail TravellerDetail) {
		super();
		this.setApprovedStatus(TravellerDetail.getApprovedStatus());
		this.setEmail(TravellerDetail.getEmail());
		this.setFirstname(TravellerDetail.getFirstname());
		this.setLastname(TravellerDetail.getLastname());
		this.setId(TravellerDetail.getId());
		this.setPaymentStatus(TravellerDetail.getPaymentStatus());
		if (TravellerDetail.getTravelDate() != null) {
			this.setTravelDate(CommonUtil.getDateFormat(TravellerDetail
					.getTravelDate()));
		}
		if (TravellerDetail.getPaymentDate() != null) {
			this.setPaymentDate(CommonUtil.getDateFormat(TravellerDetail
					.getPaymentDate()));
		}
		this.setFinalAmount(TravellerDetail.getFinalAmount());
		this.setAddress(TravellerDetail.getAddress());
		if (TravellerDetail.getApprovedDate() != null) {
			this.setApprovedDate(CommonUtil.getDateFormat(TravellerDetail
					.getApprovedDate()));
		}
		this.setCity(TravellerDetail.getCity());
		this.setStatusCommands(TravellerDetail.getStatusCommands());
		this.setPayementCommands(TravellerDetail.getPayementCommands());
		this.setCountry(TravellerDetail.getCountry());
		this.setTripcode(TravellerDetail.getTripcode());
		this.setPhone(TravellerDetail.getPhone());
		this.setPostelCode(TravellerDetail.getPostelCode());
		this.setState(TravellerDetail.getState());
		this.setVoucher(TravellerDetail.getVoucher());
		this.setTotalAmount(TravellerDetail.getTotalAmount());
		this.setPackageAmount(TravellerDetail.getPackageAmount());
		if (TravellerDetail.getPack() != null) {
			this.setPackName(TravellerDetail.getPack().getPkName());
		}
		if (TravellerDetail.getPack() != null) {

			this.setDays(TravellerDetail.getPack().getPkDays());
			this.setNights(TravellerDetail.getPack().getPkNights());
		}

	}

	public TravellerDetailModel() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
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

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
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

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
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
	
	
	
	

}

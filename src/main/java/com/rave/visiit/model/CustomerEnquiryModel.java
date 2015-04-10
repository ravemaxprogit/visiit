package com.rave.visiit.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.TravellerDetail;

public class CustomerEnquiryModel {

	private int enqId;

	private String enqCode;

	private Date enqCreatedOn;

	private String enqCustomerName;

	private String email;

	private String enqMessage;

	private String enqMobile;

	private String enqStatus;

	private String enqSubject;

	private Date enqSumbitedDate;

	private Date enqUpdatedOn;

	private String enqReplay;

	private CustRegistration customer;

	private Date enqTripDate;

	private Integer noOfAdults;

	private Integer noOfChildren;

	private String enqPackageName;
	
	private TravellerDetail travellerDetail;

	public CustomerEnquiryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerEnquiryModel(CustomerEnquiry customerEnquiry) {
		this.setEnqId(customerEnquiry.getEnqId());
		this.setEnqCode(customerEnquiry.getEnqCode());
		this.setEnqMessage(customerEnquiry.getEnqMessage());
		this.setEnqStatus(customerEnquiry.getEnqStatus());
		this.setEnqSubject(customerEnquiry.getEnqSubject());
		this.setEnqCustomerName(customerEnquiry.getEnqCustomerName());
		this.setEmail(customerEnquiry.getEmail());
		this.setEnqCreatedOn(customerEnquiry.getEnqCreatedOn());
		this.setEnqPackageName(customerEnquiry.getEnqPackageName());
		this.setEnqReplay(customerEnquiry.getEnqReplay());
		this.setEnqSumbitedDate(customerEnquiry.getEnqSumbitedDate());
		this.setEnqTripDate(customerEnquiry.getEnqTripDate());
		this.setEnqCreatedOn(customerEnquiry.getEnqCreatedOn());
		this.setEnqUpdatedOn(customerEnquiry.getEnqUpdatedOn());
		this.setNoOfAdults(customerEnquiry.getNoOfAdults());
		this.setNoOfChildren(customerEnquiry.getNoOfChildren());
		this.setEnqMobile(customerEnquiry.getEnqMobile());

	}

	

	public int getEnqId() {
		return enqId;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public String getEnqCode() {
		return enqCode;
	}

	public void setEnqCode(String enqCode) {
		this.enqCode = enqCode;
	}

	public Date getEnqCreatedOn() {
		return enqCreatedOn;
	}

	public void setEnqCreatedOn(Date enqCreatedOn) {
		this.enqCreatedOn = enqCreatedOn;
	}

	public String getEnqCustomerName() {
		return enqCustomerName;
	}

	public void setEnqCustomerName(String enqCustomerName) {
		this.enqCustomerName = enqCustomerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnqMessage() {
		return enqMessage;
	}

	public void setEnqMessage(String enqMessage) {
		this.enqMessage = enqMessage;
	}

	public String getEnqMobile() {
		return enqMobile;
	}

	public void setEnqMobile(String enqMobile) {
		this.enqMobile = enqMobile;
	}

	public String getEnqStatus() {
		return enqStatus;
	}

	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

	public String getEnqSubject() {
		return enqSubject;
	}

	public void setEnqSubject(String enqSubject) {
		this.enqSubject = enqSubject;
	}

	public Date getEnqSumbitedDate() {
		return enqSumbitedDate;
	}

	public void setEnqSumbitedDate(Date enqSumbitedDate) {
		this.enqSumbitedDate = enqSumbitedDate;
	}

	public Date getEnqUpdatedOn() {
		return enqUpdatedOn;
	}

	public void setEnqUpdatedOn(Date enqUpdatedOn) {
		this.enqUpdatedOn = enqUpdatedOn;
	}

	public String getEnqReplay() {
		return enqReplay;
	}

	public void setEnqReplay(String enqReplay) {
		this.enqReplay = enqReplay;
	}

	public CustRegistration getCustomer() {
		return customer;
	}

	public void setCustomer(CustRegistration customer) {
		this.customer = customer;
	}

	public Date getEnqTripDate() {
		return enqTripDate;
	}

	public void setEnqTripDate(Date enqTripDate) {
		this.enqTripDate = enqTripDate;
	}

	public Integer getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(Integer noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public String getEnqPackageName() {
		return enqPackageName;
	}

	public void setEnqPackageName(String enqPackageName) {
		this.enqPackageName = enqPackageName;
	}

	public TravellerDetail getTravellerDetail() {
		return travellerDetail;
	}

	public void setTravellerDetail(TravellerDetail travellerDetail) {
		this.travellerDetail = travellerDetail;
	}

	

}

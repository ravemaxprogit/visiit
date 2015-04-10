package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.*;

import com.rave.visiit.util.Constants;

import java.util.Date;

/**
 * The persistent class for the customer_enquiry database table.
 * 
 */
@Entity
@Table(name = "customer_enquiry", catalog = Constants.DB_CATELOG)
public class CustomerEnquiry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "enq_seq_id")
	private int enqId;

	@Column(name = "enq_code")
	private String enqCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enq_created_on")
	private Date enqCreatedOn;

	@Column(name = "enq_customer_name")
	private String enqCustomerName;

	@Column(name = "enq_email")
	private String email;

	@Column(name = "enq_message")
	private String enqMessage;

	@Column(name = "enq_mobile")
	private String enqMobile;

	@Column(name = "enq_status")
	private String enqStatus;

	@Column(name = "enq_subject")
	private String enqSubject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enq_sumbited_date")
	private Date enqSumbitedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enq_updated_on")
	private Date enqUpdatedOn;

	@Column(name = "enq_replay")
	private String enqReplay;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_customer_id")
	private CustRegistration customer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enq_tripdate")
	private Date enqTripDate;

	@Column(name = "enq_adults")
	private Integer noOfAdults;

	@Column(name = "enq_children")
	private Integer noOfChildren;

	@Column(name = "enq_package")
	private String enqPackageName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_traveller_id")
	private TravellerDetail travellerDetail;

	public CustomerEnquiry() {
	}

	public int getEnqId() {
		return enqId;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public String getEnqCode() {
		return this.enqCode;
	}

	public void setEnqCode(String enqCode) {
		this.enqCode = enqCode;
	}

	public Date getEnqCreatedOn() {
		return this.enqCreatedOn;
	}

	public void setEnqCreatedOn(Date enqCreatedOn) {
		this.enqCreatedOn = enqCreatedOn;
	}

	public String getEnqCustomerName() {
		return this.enqCustomerName;
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
		return this.enqMessage;
	}

	public void setEnqMessage(String enqMessage) {
		this.enqMessage = enqMessage;
	}

	public String getEnqMobile() {
		return this.enqMobile;
	}

	public void setEnqMobile(String enqMobile) {
		this.enqMobile = enqMobile;
	}

	public String getEnqStatus() {
		return this.enqStatus;
	}

	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

	public String getEnqSubject() {
		return this.enqSubject;
	}

	public void setEnqSubject(String enqSubject) {
		this.enqSubject = enqSubject;
	}

	public Date getEnqSumbitedDate() {
		return this.enqSumbitedDate;
	}

	public void setEnqSumbitedDate(Date enqSumbitedDate) {
		this.enqSumbitedDate = enqSumbitedDate;
	}

	public Date getEnqUpdatedOn() {
		return this.enqUpdatedOn;
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
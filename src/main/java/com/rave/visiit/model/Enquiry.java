package com.rave.visiit.model;

import java.util.List;

import com.rave.visiit.entity.CustomerEnquiry;

public class Enquiry {

	private Integer maxRecord;

	private List<CustomerEnquiry> customerEnquiry;

	public Enquiry() {
		super();
	}

	public Enquiry(Integer maxRecord, List<CustomerEnquiry> customerEnquiry) {
		super();
		this.maxRecord = maxRecord;
		this.customerEnquiry = customerEnquiry;
	}

	public Integer getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(Integer maxRecord) {
		this.maxRecord = maxRecord;
	}

	public List<CustomerEnquiry> getCustomerEnquiry() {
		return customerEnquiry;
	}

	public void setCustomerEnquiry(List<CustomerEnquiry> customerEnquiry) {
		this.customerEnquiry = customerEnquiry;
	}

}

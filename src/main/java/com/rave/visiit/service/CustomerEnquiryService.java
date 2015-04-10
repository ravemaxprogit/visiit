package com.rave.visiit.service;

import java.util.Map;

import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.model.Enquiry;

public interface CustomerEnquiryService {
	
	public CustomerEnquiry saveandupdate(CustomerEnquiry customerEnquiry);
	
	public Enquiry getAllEnquiry(Map  searchFields,
			String sortType, String sortValue, String filterType,
			String startIndex, String endIndex);

	public CustomerEnquiry getById(Integer parseInt);

	public Integer getNoQuiryRequest();

	public Integer getNoQuiryReply();

}

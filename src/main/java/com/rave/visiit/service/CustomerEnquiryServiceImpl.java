package com.rave.visiit.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.CustomerEnquiryDao;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.model.Enquiry;

@Service
public  class CustomerEnquiryServiceImpl implements CustomerEnquiryService {

	private CustomerEnquiryDao customerEnquiryDao;

	public CustomerEnquiryDao getCustomerEnquiryDao() {
		return customerEnquiryDao;
	}

	public void setCustomerEnquiryDao(CustomerEnquiryDao customerEnquiryDao) {
		this.customerEnquiryDao = customerEnquiryDao;
	}

	@Override
	@Transactional
	public CustomerEnquiry saveandupdate(CustomerEnquiry customerEnquiry) {
		// TODO Auto-generated method stub
		return getCustomerEnquiryDao().saveandupdate(customerEnquiry);
	}

	@Override
	@Transactional
	public Enquiry getAllEnquiry(Map searchFields,
			String sortType, String sortValue, String filterType,
			String startIndex, String endIndex) {
		// TODO Auto-generated method stub
		return getCustomerEnquiryDao().getAllEnquiry(searchFields, sortType,
				sortValue, filterType, startIndex, endIndex);
	}

	@Override
	@Transactional
	public CustomerEnquiry getById(Integer parseInt) {
		return getCustomerEnquiryDao().getById(parseInt);
	}

	@Override
	public Integer getNoQuiryRequest() {
		 return getCustomerEnquiryDao().getNoQuiryRequest();
	}

	@Override
	public Integer getNoQuiryReply() {
		 return getCustomerEnquiryDao().getNoQuiryReply();
	}

}

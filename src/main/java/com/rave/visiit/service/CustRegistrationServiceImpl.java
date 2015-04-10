/**
 * 
 */
package com.rave.visiit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.CustRegistration;

/**
 * @author admin
 *
 */
@Service
public class CustRegistrationServiceImpl extends AbstractService implements
		CustRegistrationService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rave.visiit.service.CustRegistrationService#saveorupdate(com.rave
	 * .visiit.entity.CustRegistration)
	 */
	@Override
	@Transactional
	public CustRegistration saveorupdate(CustRegistration custRegistration)
			throws Exception {
		// TODO Auto-generated method stub
		return getCustRegistrationDao().saveorupdate(custRegistration);
	}

	@Override
	@Transactional
	public CustRegistration get(String email) throws Exception {
		return getCustRegistrationDao().get(email);
	}

	@Override
	@Transactional
	public CustRegistration getByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return getCustRegistrationDao().getByCode(code);
	}

	@Override
	@Transactional
	public CustRegistration getByPhoneNo(String phone) throws Exception {
		// TODO Auto-generated method stub
		return getCustRegistrationDao().getByPhoneNo(phone);
	}

	@Override
	@Transactional
	public CustRegistration getById(Integer Id) throws Exception {
		
		return getCustRegistrationDao().getById(Id);
	}

}
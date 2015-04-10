/**
 * 
 */
package com.rave.visiit.service;

import com.rave.visiit.entity.CustRegistration;

/**
 * @author admin
 *
 */
public interface CustRegistrationService {
	
	public CustRegistration saveorupdate(CustRegistration custRegistration) throws Exception;
	
	public CustRegistration get(String email) throws Exception;
	
	public CustRegistration getById(Integer Id) throws Exception;
	
	public CustRegistration getByCode(String code) throws Exception;
	
	public CustRegistration getByPhoneNo(String phone) throws Exception;
	
}
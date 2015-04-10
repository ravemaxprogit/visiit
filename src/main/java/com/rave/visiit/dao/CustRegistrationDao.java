/**
 * 
 */
package com.rave.visiit.dao;

import com.rave.visiit.entity.CustRegistration;

/**
 * @author admin
 *
 */
public interface CustRegistrationDao {
	
	public CustRegistration saveorupdate(CustRegistration custRegistration) throws Exception;
	
	public CustRegistration get(String email) throws Exception;
	
	public CustRegistration getByCode(String code) throws Exception;
	
	public CustRegistration getByPhoneNo(String phone) throws Exception;

	public CustRegistration getById(Integer id);
	
}
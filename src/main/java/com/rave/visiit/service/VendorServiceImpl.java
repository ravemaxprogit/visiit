package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Vendor;
import com.rave.visiit.entity.VendorKey;

@Service
public class VendorServiceImpl extends AbstractService implements VendorService {


	@Override
	@Transactional
	public void save(Vendor vendor) {
		getVendorDao().save(vendor);
		
	}

	@Override
	@Transactional
	public List<Vendor> list() {
		return getVendorDao().list();
	}

	@Override
	@Transactional
	public Vendor get(int id) {
		// TODO Auto-generated method stub
		return getVendorDao().get(id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		getVendorDao().delete(id);
	}

	@Override
	@Transactional
	public String generateVendorCode() {
		// TODO Auto-generated method stub
		return getVendorDao().generateVendorCode();
	}

	@Override
	public VendorKey saveVendorKey(VendorKey vendorKey) {
		return getVendorDao().saveVendorKey(vendorKey);
	}
	
	@Override
	@Transactional
	public Long getVendorCountByCountryId(Integer countryId) throws Exception {
		return getVendorDao().getVendorCountByCountryId(countryId);
	}
	
	@Override
	@Transactional
	public Long getVendorCountByStateId(Integer stateId) throws Exception {
		return getVendorDao().getVendorCountByStateId(stateId);
	}
	
	@Override
	@Transactional
	public Long getVendorCountByCityId(Integer cityId) throws Exception {
		return getVendorDao().getVendorCountByCityId(cityId);
	}
	
	@Override
	@Transactional
	public boolean validateVendorRefference(Integer vendorId) throws Exception {
		return getVendorDao().validateVendorRefference(vendorId);
	}
}
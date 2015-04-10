package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Vendor;
import com.rave.visiit.entity.VendorKey;

public interface VendorService {
	
	public void save(Vendor vendor);
    public List<Vendor> list();
    public Vendor get(int id);
    public void delete(int id);
    public  String generateVendorCode();
	public VendorKey saveVendorKey(VendorKey vendorKey);
	
	public Long getVendorCountByCountryId(Integer countryId) throws Exception;
	
	public Long getVendorCountByStateId(Integer stateId) throws Exception;
	
	public Long getVendorCountByCityId(Integer cityId) throws Exception;
	
	public boolean validateVendorRefference(Integer vendorId) throws Exception;
}
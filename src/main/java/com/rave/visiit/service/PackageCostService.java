package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCost;

public interface PackageCostService {
	
	public PackageCost saveorupdate(PackageCost pack) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<PackageCost> getAll(Integer id) throws Exception;

	public PackageCost get(Integer id) throws Exception;

	List getAllIn(List<Package> packList) throws Exception;
	
	public List<PackageCost> getAllPrice(Integer id) throws Exception;
	
	public PackageCost getminimumPrice(Integer pkId) throws Exception;
	
	public List<PackageCost> getOfferPrice(Integer id) throws Exception;
	
	public PackageCost getHotDealsPrice(Integer id) throws Exception;

}

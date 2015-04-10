package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.PackageLocation;

public interface PackageLocationService {
	
	public PackageLocation saveorupdate(PackageLocation pack) throws Exception;
	
	public List<PackageLocation> saveorupdate(List<PackageLocation> packlocations, boolean newPack,int packId) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<PackageLocation> getAll(Integer id) throws Exception;

	public PackageLocation get(Integer id) throws Exception;
	
	public List<String> getPackageLocationByPackage(Integer id) throws Exception;
	
	public List<PackageLocation> getAllPackageLocationByPackage(Integer id) throws Exception;

}

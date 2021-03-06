package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.PackageAttraction;

public interface PackageAttractionDao {
	
	public PackageAttraction saveorupdate(PackageAttraction pack) throws Exception;

	public boolean delete(PackageAttraction pack) throws Exception;

	public List<PackageAttraction> getAll() throws Exception;

	public PackageAttraction get(Integer id) throws Exception;

}

package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.PackageConditions;

public interface PackageConditionDao {
	
	public PackageConditions saveorupdate(PackageConditions pack) throws Exception;

	public List<PackageConditions> getAll(Integer id) throws Exception;

	public PackageConditions get(Integer id) throws Exception;

}

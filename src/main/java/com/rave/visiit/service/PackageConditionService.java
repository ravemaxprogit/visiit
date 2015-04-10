package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.PackageConditions;
import com.rave.visiit.entity.PackageItinerary;

public interface PackageConditionService {
	
	public PackageConditions saveorupdate(PackageConditions pack) throws Exception;

	public List<PackageConditions> getAll(Integer id) throws Exception;

	public PackageConditions get(Integer id) throws Exception;
	
}

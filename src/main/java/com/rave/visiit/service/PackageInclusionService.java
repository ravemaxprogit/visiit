package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageInclusionKey;

public interface PackageInclusionService {
	
	public PackageInclusion saveorupdate(PackageInclusion pack) throws Exception;

	public boolean delete(PackageInclusion pack) throws Exception;

	public List<PackageInclusion> getAll() throws Exception;

	public PackageInclusion get(Integer id) throws Exception;
	
	public List<PackageInclusion> getAllByPackage(Integer pkId) throws Exception;
	
	public Integer getNextInculusionId() throws Exception;

	public List<PackageInclusion> getInclusionByPackage(Integer parseInt);

	public List<PackageInclusion> getValideInclusionByPackage(Integer parseInt);

	public PackageInclusionKey saveorupdateInclusionKey(PackageInclusionKey packageInclusionKey);


}

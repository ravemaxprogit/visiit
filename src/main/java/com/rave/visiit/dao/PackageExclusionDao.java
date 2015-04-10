package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageExclusionKey;

public interface PackageExclusionDao {
	
	public PackageExclusion saveorupdate(PackageExclusion pack) throws Exception;

	public boolean delete(PackageExclusion pack) throws Exception;

	public List<PackageExclusion> getAll() throws Exception;

	public PackageExclusion get(Integer id) throws Exception;
	
	public List<PackageExclusion> getAllByPackage(Integer pkId) throws Exception;
	
	public Integer getNextExclusionId() throws Exception;

	public List<PackageExclusion> getExclusionByPackage(Integer parseInt);

	public List<PackageExclusion> getValideExclusionByPackage(Integer parseInt);

	public PackageExclusionKey saveorupdateExclusionKey(
			PackageExclusionKey packageInclusionKey);

}

package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.PackageCategory;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.Category;

public interface PackageCategoryDao {

//	public PackageCategory saveorupdate(PackageCategory pack) throws Exception;
	
	public List<PackageCategory> saveorupdate(List<PackageCategory> packcategory, boolean newPack, int packId) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<PackageCategory> getAll(Integer id) throws Exception;

//	public PackageCategory get(Integer id) throws Exception;
	
	public List<Package> getAllPackByPackCategory(Integer id) throws Exception;
	
	public List<Category> getAllCatByPackCategory(Integer id) throws Exception;

	public List<Category> getValidCategories() throws Exception;

}

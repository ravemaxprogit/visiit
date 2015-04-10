package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCategory;

@Service
public class PackageCategoryServiceImpl extends AbstractService implements
		PackageCategoryService {

	@Override
	@Transactional
	public List<PackageCategory> saveorupdate(
			List<PackageCategory> packcategory, boolean newPack, int packId)
			throws Exception {
		// TODO Auto-generated method stub
		return getPackageCategoryDao().saveorupdate(packcategory, newPack, packId);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCategoryDao().delete(id);
	}

	@Override
	@Transactional
	public List<PackageCategory> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCategoryDao().getAll(id);
	}
	
	@Override
	@Transactional
	public List<Package> getAllPackByPackCategory(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCategoryDao().getAllPackByPackCategory(id);
	}

	@Override
	@Transactional
	public List<Category> getAllCatByPackCategory(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCategoryDao().getAllCatByPackCategory(id);
	}

	@Override
	@Transactional
	public List<Category> getValidCategories() throws Exception {
		// TODO Auto-generated method stub
		return getPackageCategoryDao().getValidCategories();
	}

}

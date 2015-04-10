package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageLocation;

@Service
public class PackageLocationServiceImpl extends AbstractService implements
		PackageLocationService {

	@Override
	@Transactional
	public PackageLocation saveorupdate(PackageLocation pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageLocationDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageLocationDao().delete(id);
	}

	@Override
	@Transactional
	public List<PackageLocation> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageLocationDao().getAll(id);
	}

	@Override
	@Transactional
	public PackageLocation get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageLocationDao().get(id);
	}

	@Override
	@Transactional
	public List<PackageLocation> saveorupdate(
			List<PackageLocation> packlocations, boolean newPack, int packId) throws Exception {
		// TODO Auto-generated method stub
		return getPackageLocationDao().saveorupdate(packlocations,newPack,packId);
	}
	
	@Override
	@Transactional
	public List<String> getPackageLocationByPackage(Integer id) throws Exception {
		return getPackageLocationDao().getPackageLocationByPackage(id);
	}
	
	@Override
	@Transactional
	public List<PackageLocation> getAllPackageLocationByPackage(Integer id) throws Exception {
		return getPackageLocationDao().getAllPackageLocationByPackage(id);
	}
}

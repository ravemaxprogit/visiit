package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageInclusionKey;

@Service
public class PackageInclusionServiceImpl extends AbstractService implements
		PackageInclusionService {

	@Override
	@Transactional
	public PackageInclusion saveorupdate(PackageInclusion pack)
			throws Exception {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(PackageInclusion pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().delete(pack);
	}

	@Override
	@Transactional
	public List<PackageInclusion> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().getAll();
	}

	@Override
	@Transactional
	public PackageInclusion get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().get(id);
	}

	@Override
	@Transactional
	public List<PackageInclusion> getAllByPackage(Integer pkId) throws Exception {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().getAllByPackage(pkId);
	}

	@Override
	@Transactional
	public Integer getNextInculusionId() throws Exception {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().getNextInclusionId();
	}

	@Override
	@Transactional
	public List<PackageInclusion> getInclusionByPackage(Integer parseInt) {
		// TODO Auto-generated method stub
		return getPackageInclusionDao().getInclusionByPackage(parseInt);
	}

	@Override
	@Transactional
	public List<PackageInclusion> getValideInclusionByPackage(Integer parseInt) {
		return getPackageInclusionDao().getValideInclusionByPackage(parseInt);
	}

	@Override
	@Transactional
	public PackageInclusionKey saveorupdateInclusionKey(PackageInclusionKey packageInclusionKey) {
		return getPackageInclusionDao().saveorupdateInclusionKey(packageInclusionKey);
		
	}

}

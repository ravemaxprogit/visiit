package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageExclusionKey;

@Service
public class PackageExclusionServiceImpl extends AbstractService implements
		PackageExclusionService {

	@Override
	@Transactional
	public PackageExclusion saveorupdate(PackageExclusion pack)
			throws Exception {
		// TODO Auto-generated method stub
		return getPackageExclusionDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(PackageExclusion pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageExclusionDao().delete(pack);
	}

	@Override
	@Transactional
	public List<PackageExclusion> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getPackageExclusionDao().getAll();
	}

	@Override
	@Transactional
	public PackageExclusion get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageExclusionDao().get(id);
	}

	@Override
	@Transactional
	public List<PackageExclusion> getAllByPackage(Integer pkId)
			throws Exception {
		// TODO Auto-generated method stub
		return getPackageExclusionDao().getAllByPackage(pkId);
	}

	@Override
	@Transactional
	public Integer getNextExclusionId() throws Exception {
		// TODO Auto-generated method stub
		return getPackageExclusionDao().getNextExclusionId();
	}

	@Override
	@Transactional
	public List<PackageExclusion> getExclusionByPackage(Integer parseInt) {

		return getPackageExclusionDao().getExclusionByPackage(parseInt);
	}

	@Override
	@Transactional
	public List<PackageExclusion> getValideExclusionByPackage(Integer parseInt) {

		return getPackageExclusionDao().getValideExclusionByPackage(parseInt);
	}

	@Override
	public PackageExclusionKey saveorupdateExclusionKey(
			PackageExclusionKey packageInclusionKey) {
		return getPackageExclusionDao().saveorupdateExclusionKey(packageInclusionKey);
	
	}

}

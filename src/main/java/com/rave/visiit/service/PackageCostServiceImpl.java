package com.rave.visiit.service;

import java.util.List;

import javax.persistence.Temporal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCost;

@Service
public class PackageCostServiceImpl extends AbstractService implements
		PackageCostService {

	@Override
	@Transactional
	public PackageCost saveorupdate(PackageCost pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCostDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCostDao().delete(id);
	}

	@Override
	@Transactional
	public List<PackageCost> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCostDao().getAll(id);
	}

	@Override
	@Transactional
	public PackageCost get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCostDao().get(id);
	}

	@Override
	@Transactional
	public List getAllIn(List<Package> packList) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCostDao().getAllIn(packList);
	}
	
	@Override
	@Transactional
	public List<PackageCost> getAllPrice(Integer id) throws Exception {
		return getPackageCostDao().getAllPrice(id);
	}
	
	@Override
	@Transactional
	public PackageCost getminimumPrice(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageCostDao().getminimumPrice(id);
	}
	
	@Override
	@Transactional
	public List<PackageCost> getOfferPrice(Integer id) throws Exception {
		return getPackageCostDao().getOfferPrice(id);
	}
	
	@Override
	@Transactional
	public PackageCost getHotDealsPrice(Integer id) throws Exception {
		return getPackageCostDao().getHotDealsPrice(id);
	}
}

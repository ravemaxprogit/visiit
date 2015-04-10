package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageItinerary;

@Service
public class PackageItineraryServiceImpl extends AbstractService implements
		PackageItineraryService {

	@Override
	@Transactional
	public PackageItinerary saveorupdate(PackageItinerary pack)
			throws Exception {
		// TODO Auto-generated method stub
		return getPackageItineraryDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageItineraryDao().delete(id);
	}

	@Override
	@Transactional
	public List<PackageItinerary> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageItineraryDao().getAll(id);
	}

	@Override
	@Transactional
	public PackageItinerary get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageItineraryDao().get(id);
	}
	
	@Override
	@Transactional
	public Boolean checkDayExists(Integer id,Integer day) throws Exception {
		return getPackageItineraryDao().checkDayExists(id,day);
	}
}

package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageHotel;

@Service
public class PackageHotelServiceImpl extends AbstractService implements
		PackageHotelService {

	@Override
	@Transactional
	public PackageHotel saveorupdate(PackageHotel pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageHotelDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageHotelDao().delete(id);
	}

	@Override
	@Transactional
	public List<PackageHotel> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageHotelDao().getAll(id);
	}

	@Override
	@Transactional
	public PackageHotel get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageHotelDao().get(id);
	}

}

package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.PackageHotel;

public interface PackageHotelDao {
	
	public PackageHotel saveorupdate(PackageHotel pack) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<PackageHotel> getAll(Integer id) throws Exception;

	public PackageHotel get(Integer id) throws Exception;

}

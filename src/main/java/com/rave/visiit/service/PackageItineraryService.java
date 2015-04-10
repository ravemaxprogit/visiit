package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.PackageItinerary;

public interface PackageItineraryService {
	
	public PackageItinerary saveorupdate(PackageItinerary pack) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<PackageItinerary> getAll(Integer id) throws Exception;

	public PackageItinerary get(Integer id) throws Exception;
	
	public Boolean checkDayExists(Integer id,Integer day) throws Exception;
	
}

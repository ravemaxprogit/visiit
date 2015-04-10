package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Hotel;
import com.rave.visiit.entity.HotelContact;
import com.rave.visiit.entity.HotelKey;
import com.rave.visiit.entity.PackageHotel;

public interface HotelDao {
	
	public Hotel saveorupdate(Hotel hotel) throws Exception;

	public boolean delete(Hotel hotel) throws Exception;

	public List<Hotel> getAll() throws Exception;

//	public List<Hotel> getAll(String place) throws Exception;
	
	public Hotel get(Integer id) throws Exception;
	
	public String generateHotelCode();

	public HotelKey saveorupdateHotelkey(HotelKey hotelkey);
	
	public List<HotelContact> getHotelContact(Integer id);
	
	public List<PackageHotel> getAllHotelByPKId(Integer id) throws Exception;
	
	public List<Hotel> getHotelByCity(String id) throws Exception;
	
	public String getImageUrlById(Integer id);
	
	public Long getHotelCountByCountryId(Integer countryId) throws Exception;
	
	public Long getHotelCountByStateId(Integer stateId) throws Exception;
	
	public Long getHotelCountByCityId(Integer cityId) throws Exception;
	
	public boolean validateVendorRefference(Integer hotelId) throws Exception;
}

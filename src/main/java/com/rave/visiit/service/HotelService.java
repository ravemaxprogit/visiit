package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Hotel;
import com.rave.visiit.entity.HotelContact;
import com.rave.visiit.entity.HotelKey;
import com.rave.visiit.entity.PackageHotel;

public interface HotelService {
	
	public Hotel saveorupdate(Hotel hotel) throws Exception;

	public boolean delete(Hotel hotel) throws Exception;

	public List<Hotel> getAll() throws Exception;

	public Hotel get(Integer id) throws Exception;
	
	public  String generateHotelCode();
	
	public HotelKey saveorupdateHotelKey(HotelKey hotelkey) throws Exception;
	
	public List<HotelContact> getHotelContact(Integer id) throws Exception;
	
	public List<PackageHotel> getAllHotelByPKId(Integer Id) throws Exception;
	
	public List<Hotel> getHotelByCity(String id) throws Exception;
	
	public String getImageUrlById(Integer id) throws Exception;
	
	public Long getHotelCountByCountryId(Integer countryId) throws Exception;
	
	public Long getHotelCountByStateId(Integer stateId) throws Exception;
	
	public Long getHotelCountByCityId(Integer cityId) throws Exception;
	
	public boolean validateVendorRefference(Integer hotelId) throws Exception;

}

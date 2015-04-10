package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.HotelContact;

public interface HotelContactService {
	
	public HotelContact saveorupdate(HotelContact hotelCont) throws Exception;

	public boolean delete(HotelContact hotelCont) throws Exception;

	public List<HotelContact> getAll() throws Exception;

	public HotelContact get(Integer id) throws Exception;

}

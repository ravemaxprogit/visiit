package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Hotel;
import com.rave.visiit.entity.HotelContact;
import com.rave.visiit.entity.HotelKey;
import com.rave.visiit.entity.PackageHotel;

@Service
public class HotelServiceImpl extends AbstractService implements HotelService {

	@Override
	@Transactional
	public Hotel saveorupdate(Hotel hotel) throws Exception {
		return getHotelDao().saveorupdate(hotel);
	}

	@Override
	@Transactional
	public boolean delete(Hotel hotel) throws Exception {
		return getHotelDao().delete(hotel);
	}

	@Override
	@Transactional
	public List<Hotel> getAll() throws Exception {
		return getHotelDao().getAll();
	}

	@Override
	@Transactional
	public Hotel get(Integer id) throws Exception {
		return getHotelDao().get(id);
	}

	@Override
	@Transactional
	public String generateHotelCode() {
		return getHotelDao().generateHotelCode();
	}

	@Override
	@Transactional
	public HotelKey saveorupdateHotelKey(HotelKey hotelkey) throws Exception {
		
		return getHotelDao().saveorupdateHotelkey(hotelkey);
	}

	@Override
	public List<HotelContact> getHotelContact(Integer id) throws Exception {
		return getHotelDao().getHotelContact(id);
	}
	
	@Override
	@Transactional
	public List<PackageHotel> getAllHotelByPKId(Integer id) throws Exception {
		return getHotelDao().getAllHotelByPKId(id);
	}
	
	@Override
	@Transactional
	public List<Hotel> getHotelByCity(String id) throws Exception {
		return getHotelDao().getHotelByCity(id);
	}
	
	@Override
	@Transactional
	public String getImageUrlById(Integer id) throws Exception {
		return getHotelDao().getImageUrlById(id);
	}
	
	@Override
	@Transactional
	public Long getHotelCountByCountryId(Integer countryId) throws Exception {
		return getHotelDao().getHotelCountByCountryId(countryId);
	}
	
	@Override
	@Transactional
	public Long getHotelCountByStateId(Integer stateId) throws Exception {
		return getHotelDao().getHotelCountByStateId(stateId);
	}
	
	@Override
	@Transactional
	public Long getHotelCountByCityId(Integer cityId) throws Exception {
		return getHotelDao().getHotelCountByCityId(cityId);
	}
	
	@Override
	@Transactional
	public boolean validateVendorRefference(Integer hotelId) throws Exception {
		return getHotelDao().validateVendorRefference(hotelId);
	}
}

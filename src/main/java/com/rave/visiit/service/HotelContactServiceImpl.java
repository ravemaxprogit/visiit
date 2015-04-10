package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.HotelContact;

@Service
public class HotelContactServiceImpl extends AbstractService implements
		HotelContactService {

	@Override
	@Transactional
	public HotelContact saveorupdate(HotelContact hotelCont) throws Exception {
		// TODO Auto-generated method stub
		return  getHotelContactDao().saveorupdate(hotelCont);
	}

	@Override
	@Transactional
	public boolean delete(HotelContact hotelCont) throws Exception {
		// TODO Auto-generated method stub
		return getHotelContactDao().delete(hotelCont);
	}

	@Override
	@Transactional
	public List<HotelContact> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getHotelContactDao().getAll();
	}

	@Override
	@Transactional
	public HotelContact get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getHotelContactDao().get(id);
	}

}

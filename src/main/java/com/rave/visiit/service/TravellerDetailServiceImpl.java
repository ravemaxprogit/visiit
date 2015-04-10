package com.rave.visiit.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.TravellerDetailDao;
import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.model.TravellerModel;

public class TravellerDetailServiceImpl implements TravellerDetailService {
	
	
	private TravellerDetailDao travellerDetailDao;

	@Transactional
	@Override
	public TravellerDetail saveorupdateTraveler(TravellerDetail travellerDetail, Integer packId) {
		return getTravellerDetailDao().saveorupdateTraveler(travellerDetail, packId);
	}

	public TravellerDetailDao getTravellerDetailDao() {
		return travellerDetailDao;
	}

	public void setTravellerDetailDao(TravellerDetailDao travellerDetailDao) {
		this.travellerDetailDao = travellerDetailDao;
	}

	@Transactional
	@Override
	public PersonDetail saveorupdatePerson(List<PersonDetail> personDetailList,
			Integer travellerId, Integer packId) {

		return getTravellerDetailDao().saveorupdatePerson(personDetailList,travellerId, packId);
	}

	@Transactional
	@Override
	public TravellerDetail getTravellerDetailByTripCode(String tripCode) {

		return  getTravellerDetailDao().getTravellerDetailByTripCode(tripCode);
	}

	@Transactional
	@Override
	public List<PersonDetail> getPersonDetailByTraverlerId(TravellerDetail traveler) {
		return  getTravellerDetailDao().getPersonDetailByTraverlerId(traveler);
	}
	
	
	@Transactional
	@Override
	public TravellerModel getTravellerDetails(Map searchFieldMap, String sortTypeStr,
			String sortValueStr, String filterTypeStr, String startIndexStr,
			String endIndexStr) {
		// TODO Auto-generated method stub
		return getTravellerDetailDao().getTravellerDetails(searchFieldMap,sortTypeStr,
				sortValueStr,filterTypeStr,startIndexStr,
				endIndexStr);
	}
	
	@Transactional
	@Override
	public List<PersonDetail> getAllPersonByTraverlerId(Integer travelerId) {
		return  getTravellerDetailDao().getAllPersonByTraverlerId(travelerId);
	}

	@Override
	public Integer getNoPackageBooked() {
		return  getTravellerDetailDao().getTravellerDetailDao();
	}

	@Override
	public Integer getNoTripCompleted() {
		return  getTravellerDetailDao().getNoTripCompleted();
	}
	
}

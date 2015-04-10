package com.rave.visiit.dao;

import java.util.List;
import java.util.Map;

import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.model.TravellerModel;

public interface TravellerDetailDao {


	public TravellerDetail saveorupdateTraveler(TravellerDetail travellerDetail,
			Integer packId);

	public PersonDetail saveorupdatePerson(List<PersonDetail> personDetailList,
			Integer travellerId, Integer packId);

	public TravellerDetail getTravellerDetailByTripCode(String tripCode);


	public List<PersonDetail> getPersonDetailByTraverlerId(TravellerDetail traveler);

	public TravellerModel getTravellerDetails(Map searchFieldMap, String sortTypeStr,
			String sortValueStr, String filterTypeStr, String startIndexStr,
			String endIndexStr);

	public List<PersonDetail> getAllPersonByTraverlerId(Integer travelerId);

	public Integer getTravellerDetailDao();

	public Integer getNoTripCompleted();
}

package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.City;
import com.rave.visiit.entity.Country;
import com.rave.visiit.entity.Locations;
import com.rave.visiit.entity.State;

public interface CountryDao {
	
	public <T> T save(final T o) throws Exception;
	
	public <T> boolean delete(final T o) throws Exception;
	
	public List<State> getStates(Integer countryId) throws Exception;

	public List<City> getCities(Integer stateId) throws Exception;

	public List<Locations> getLocations(Integer cityId) throws Exception;

	public List<Country> getAllCountry() throws Exception;

	public Country getCountry(Integer id) throws Exception;

	public State getState(Integer stateId) throws Exception;

	public City getCity(Integer cityId) throws Exception;

//	public List<Locations> getLocByCntryId(Integer cntryId) throws Exception;
	
	public Locations getLocation(Integer locId) throws Exception;
	
	public List<State> getAllStates(List<Country> country,boolean isActive) throws Exception;
	
	public List<City> getAllCities(List<State> state,boolean isActive) throws Exception;
	
	public List<Locations> getAllLocations(List<City> city,boolean isActive) throws Exception;
	
	public List<State> getAllStatesByCountryId(Integer cntryId,boolean isActive) throws Exception;
	
	public List<City> getAllCitiesByStateIds(List<Integer> stateIds,boolean isActive) throws Exception;
	
	public List<Locations> getAllLocationsByCityIds(List<Integer> cityIds,boolean isActive) throws Exception;
	
	public List<Country> getAllCountries(boolean isActive) throws Exception;
}
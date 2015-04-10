package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.CountryDao;
import com.rave.visiit.entity.City;
import com.rave.visiit.entity.Country;
import com.rave.visiit.entity.Locations;
import com.rave.visiit.entity.State;

@Service
public class CountryServiceImpl implements CountryService {
	
	private CountryDao countryDao;

	@Override
	@Transactional
	public Country save(Country country) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().save(country); 
	}

	@Override
	@Transactional
	public State save(State state) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().save(state);
	}

	@Override
	@Transactional
	public City save(City city) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().save(city);
	}

	@Override
	@Transactional
	public Locations save(Locations locations) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().save(locations);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<State> getStates(Integer countryId) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getStates(countryId);
	}

	@Override
	@Transactional
	public List<City> getCities(Integer stateId) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getCities(stateId);
	}

	@Override
	@Transactional
	public List<Locations> getLocations(Integer cityId) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getLocations(cityId);
	}

	@Override
	@Transactional
	public boolean delete(Country country) throws Exception {
		return getCountryDao().delete(country);
	}

	@Override
	@Transactional
	public boolean delete(State state) throws Exception {
		return getCountryDao().delete(state);
	}

	@Override
	@Transactional
	public boolean delete(City city) throws Exception {
		return getCountryDao().delete(city);
	}

	@Override
	@Transactional
	public boolean delete(Locations locations) throws Exception {
		return getCountryDao().delete(locations);
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}
	
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	@Override
	@Transactional
	public List<Country> getAllCountry() throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getAllCountry();
	}

	@Override
	@Transactional
	public Country getCountry(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getCountry(id);
	}

	@Override
	@Transactional
	public State getState(Integer stateId) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getState(stateId);
	}

	@Override
	@Transactional
	public City getCity(Integer cityId) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getCity(cityId);
	}

	@Override
	@Transactional
	public Locations getLocation(Integer locId) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getLocation(locId);
	}

	@Override
	@Transactional
	public List<State> getAllStates(List<Country> country,boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getAllStates(country,isActive);
	}

	@Override
	@Transactional
	public List<City> getAllCities(List<State> state,boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getAllCities(state,isActive);
	}

	@Override
	@Transactional
	public List<Locations> getAllLocations(List<City> city,boolean isActive) throws Exception {
		// TODO Auto-generated method stub
		return getCountryDao().getAllLocations(city,isActive);
	}

	@Override
	public List<State> getAllStatesByCountryId(Integer cntryId,boolean isActive)
			throws Exception {
		return getCountryDao().getAllStatesByCountryId(cntryId,isActive);
	}

	@Override
	public List<City> getAllCitiesByStateIds(List<Integer> stateIds,boolean isActive)
			throws Exception {
		return getCountryDao().getAllCitiesByStateIds(stateIds,isActive);
	}

	@Override
	public List<Locations> getAllLocationsByCityIds(List<Integer> cityIds,boolean isActive)
			throws Exception {
		
		return getCountryDao().getAllLocationsByCityIds(cityIds,isActive);
	}
	
	@Override
	@Transactional
	public List<Country> getAllCountries(boolean isActive) throws Exception {
		return getCountryDao().getAllCountries(isActive);
	}
}

package com.rave.visiit.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.City;
import com.rave.visiit.entity.Country;
import com.rave.visiit.entity.Locations;
import com.rave.visiit.entity.State;
import com.rave.visiit.service.CountryService;
import com.rave.visiit.service.HotelService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.service.VendorService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.LabelValue;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class CountryController {

	private static Logger log = Logger.getLogger(CountryController.class);

	private CountryService countryService;
	
	@Autowired(required = true)
	@Qualifier(value = "hotelService")
	private HotelService hotelService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageService")
	private PackageService packageService;
	
	@Autowired(required = true)
	@Qualifier(value = "vendorService")
	private VendorService vendorService;

	List<LabelValue> lvList = new ArrayList<LabelValue>();

	@RequestMapping("/country")
	public ModelAndView getCountry() {
		List<Country> cntryList = null;
		if (getCountryService() != null) {
			try {
				cntryList = getCountryService().getAllCountry();
				if(cntryList == null){
					cntryList = new ArrayList<Country>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("country list " + cntryList.size());
		return new ModelAndView("CountryMaster", "cntryList", cntryList);
	}
	
	@RequestMapping(value ={"/secure/getAllCountries","/getAllCountries"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getCountryInJSON() {
		ModelMap model = new ModelMap();
		try {
			List<Country> cntryList = null;
			cntryList = getCountryService().getAllCountries(false);
			if(cntryList != null && cntryList.size() > 0){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("cntryList", cntryList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_COUNTRY_TO_DISPLAY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while displaying country details.....\n\n" + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}			
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getAllLocation","/getAllLocation"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllLocation(@RequestParam("cityStr") String cityStr) {
		ModelMap model = new ModelMap();
		try {
			if(cityStr == null || cityStr.equals("")){
				throw new VisiitRunTimeException("Please Select City");
			}
			int cityId = Integer.parseInt(cityStr);
			List<Integer> cityIds = new ArrayList<Integer>();
			cityIds.add(cityId);	
			List<Locations> locList = getCountryService().getAllLocationsByCityIds(cityIds,false);
			if(locList != null && locList.size() > 0 ){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("locations", locList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_LOCATIONS_TO_DISPLAY);
			}
			
		} catch (Exception e) {
			log.error("Error Occured while displaying location details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/stateList","/stateList"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getStateInJSON(@RequestParam("countryId") String countryId) {
		List<State> stateList = null;
		ModelMap model = new ModelMap();
		try {
			if(countryId==null || countryId.equals("")){throw new VisiitRunTimeException("Please Select City.");}
			Integer cntryId = Integer.parseInt(countryId);
			stateList = getCountryService().getAllStatesByCountryId(cntryId,false);
			if(stateList != null && stateList.size() >0){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("states", stateList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_STATE_TO_DISPLAY);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while displaying state details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/cityList","/cityList"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getCityInJSON(@RequestParam("stateId") String stateId) {
		ModelMap model = new ModelMap();
		try {
			if(stateId == null || stateId.isEmpty()){throw new VisiitRunTimeException("Please select state");}
			Integer stId = Integer.parseInt(stateId);
			List<Integer> stateIds = new ArrayList<Integer>();
			stateIds.add(stId);
			List<City> cityList = getCountryService().getAllCitiesByStateIds(stateIds,false);
			if(cityList != null && cityList.size() >0){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("cities", cityList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_CITY_TO_DISPLAY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while displaying City details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/locationList","/locationList"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getLocationInJSON(@RequestParam("cityId") String cityId) {
		ModelMap model = new ModelMap();
		try {
			if(cityId == null || cityId.isEmpty()){throw new VisiitRunTimeException("Please select city");}
			List<Integer> cityIds = new ArrayList<Integer>();
			cityIds.add(Integer.parseInt(cityId));
			List<Locations> locList = getCountryService().getAllLocationsByCityIds(cityIds,false);
			if(locList != null && locList.size() >0){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("locations", locList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_LOCATIONS_TO_DISPLAY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while displaying Location details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getAllStates","/getAllStates"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getAllState(@RequestParam("countryArr") String countryArr) {
		List<Country> countList = null;
		List<State> stateList = null;
		ModelMap model = new ModelMap();
		try {
			if(countryArr==null || countryArr.equals("")){throw new VisiitRunTimeException("Country Id is null.");}
			ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			countList = mapper.readValue(countryArr, new TypeReference<ArrayList<Country>>() {});
			if(countList == null || countList.isEmpty()){throw new VisiitRunTimeException("There is no Countries");}
			stateList = getCountryService().getAllStates(countList,false);
			if(stateList != null && stateList.size() >0){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("states", stateList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_STATE_TO_DISPLAY);
			}
		} catch (Exception e) {
			log.error("Error Occured while displaying States details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getAllCities","/getAllCities"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getAllCities(@RequestParam("stateArr") String stateArr) {
		List<State> stateList = null;
		List<City> cityList = null;
		ModelMap model = new ModelMap();
		try {
			if(stateArr==null || stateArr.equals("")){throw new VisiitRunTimeException("State is null.");}
			ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    stateList = mapper.readValue(stateArr, new TypeReference<ArrayList<State>>() {});
		    if(stateList == null || stateList.isEmpty()){throw new VisiitRunTimeException("There is no states");}
		    cityList = getCountryService().getAllCities(stateList,false);
		    if(cityList != null && cityList.size() >0){
		    	model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("cities", cityList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_CITY_TO_DISPLAY);
			}
		} catch (Exception e) {
			log.error("Error Occured while displaying Cities details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getAllLocations","/getAllLocations"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getAllLocations(@RequestParam("cityArr") String cityArr) {
		List<City> cityList = null;
		List<Locations> locationList = null;
		ModelMap model = new ModelMap();
		try {
			if(cityArr==null || cityArr.equals("")){throw new VisiitRunTimeException("City is null.");}
			ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    cityList = mapper.readValue(cityArr, new TypeReference<ArrayList<City>>() {});
		    if(cityList == null || cityList.isEmpty()){throw new VisiitRunTimeException("There is no Cities");}
		    locationList = getCountryService().getAllLocations(cityList,false);
		    if(locationList != null && locationList.size() >0){
		    	model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("locations", locationList);
			}else {
				throw new VisiitRunTimeException(ErrorConstants.NO_LOCATIONS_TO_DISPLAY);
			}
		} catch (Exception e) {
			log.error("Error Occured while displaying Locations details.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}


	@RequestMapping(value = {"/secure/saveCountry","/saveCountry"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveCountry(@RequestBody Country country) {
		ModelMap model = new ModelMap();
		try {
			if(country != null){
				if(country.getCountryCode() == null || country.getCountryCode().isEmpty()){throw new VisiitRunTimeException("Country Code Required");} 
				if(country.getCountryName() == null || country.getCountryName().isEmpty()){throw new VisiitRunTimeException("Country Name Required");} 
				if(country.getCountryDescription() == null || country.getCountryDescription().isEmpty()){throw new VisiitRunTimeException("Country Description Required");}
				
				if (country.getCountryIsactive() == null
						|| country.getCountryIsactive().equals("")) {
					country.setCountryIsactive(Boolean.TRUE);
				}
				country.setCountryUpdatedDate(new Timestamp(System
						.currentTimeMillis()));
				country.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				country.setCreatedBy(Constants.ADMIN);
				country.setIsDeleted(Boolean.FALSE);
				getCountryService().save(country);
				
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "Country successfully saved");
			} else {
				throw new VisiitRunTimeException("Please Provide Country");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving country.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof ConstraintViolationException){
				model.put(Constants.MESSAGE_STR, country.getCountryCode() + " : " +ErrorConstants.THIS_CODE_ALREADY_EXISTS);
			} else if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/saveState","/saveState"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveState(@RequestBody State state) {
		ModelMap model = new ModelMap();
		try {
			if(state != null){
				if(state.getStateCode() == null || state.getStateCode().isEmpty()){throw new VisiitRunTimeException("State Code Required");} 
				if(state.getStateName() == null || state.getStateName().isEmpty()){throw new VisiitRunTimeException("State Name Required");}
				if(state.getStateDescription() == null || state.getStateDescription().isEmpty()){throw new VisiitRunTimeException("State Description Required");}
				
				if (state.getStateIsactive() == null
						|| state.getStateIsactive().equals("")) {
					state.setStateIsactive(Boolean.TRUE);
				}
				state.setStateUpdatedDate(new Timestamp(System
						.currentTimeMillis()));
				state.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				state.setIsDeleted(Boolean.FALSE);
				state.setCreatedBy(Constants.ADMIN);
				getCountryService().save(state);
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "State successfully saved.");
			} else {
				throw new VisiitRunTimeException("Please Provode State");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving State.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof ConstraintViolationException){
				model.put(Constants.MESSAGE_STR, state.getStateCode()  + " : " +ErrorConstants.THIS_CODE_ALREADY_EXISTS);
			}  else if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/saveCity","/saveCity"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveCity(@RequestBody City city) {
		ModelMap model = new ModelMap();
		try {
			if(city != null){
				if(city.getCityCode() == null || city.getCityCode().isEmpty()){throw new VisiitRunTimeException("City Code Required");} 
				if(city.getCityName() == null || city.getCityName().isEmpty()){throw new VisiitRunTimeException("City Name Required");}
				if(city.getCityDescription() == null || city.getCityDescription().isEmpty()){throw new VisiitRunTimeException("City Description Required");}
				
				if (city.getCityIsactive() == null 	|| city.getCityIsactive().equals("")) {
					city.setCityIsactive(Boolean.TRUE);
				}
				city.setCityUpdatedDate(new Timestamp(System
						.currentTimeMillis()));
				city.setIsDeleted(Boolean.FALSE);
				city.setCreatedBy(Constants.ADMIN);
				city.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				getCountryService().save(city);
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "City successfully saved.");
			} else {
				throw new VisiitRunTimeException("Please Provode City");
			}
		} catch (Exception e) {	
			e.printStackTrace();
			log.error("Error Occured while saving city.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof ConstraintViolationException){
				model.put(Constants.MESSAGE_STR, city.getCityCode() + " : " +ErrorConstants.THIS_CODE_ALREADY_EXISTS);
			}  else if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/saveLocation","/saveLocation"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveLocation(@RequestBody Locations location) {
		ModelMap model = new ModelMap();
		try {
			if(location != null){
				if(location.getLocCode() == null || location.getLocCode().isEmpty()){throw new VisiitRunTimeException("Location Code Required");}  
				if(location.getLocName() == null || location.getLocName().isEmpty()){throw new VisiitRunTimeException("Location Name Required");} 
				if(location.getLocDescription() == null || location.getLocDescription().isEmpty()){throw new VisiitRunTimeException("Location Description Required");}
				
				if (location.getLocIsactive() == null
						|| location.getLocIsactive().equals("")) {
					location.setLocIsactive(Boolean.TRUE);
				}
				location.setLocUpdatedDate(new Timestamp(System
						.currentTimeMillis()));
				location.setIsDeleted(Boolean.FALSE);
				location.setCreatedBy(Constants.ADMIN);
				location.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				getCountryService().save(location);
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "Location successfully saved.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving location.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof ConstraintViolationException){
				model.put(Constants.MESSAGE_STR, location.getLocCode() + " : " +ErrorConstants.THIS_CODE_ALREADY_EXISTS);
			}else if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deleteCountry","/deleteCountry"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteCountry(@RequestParam("countryId") String countryId) {
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(countryId);
			Long packCount = packageService.getPackageCountByPackLoaction(id);
			Long hotelCount = hotelService.getHotelCountByCountryId(id);
			Long vendorCount = vendorService.getVendorCountByCountryId(id);
			if((packCount == null || packCount == 0) && (hotelCount == null || hotelCount == 0) && (vendorCount == null || vendorCount == 0)){
				Country country = getCountryService().getCountry(id);
				if(country == null){throw new VisiitRunTimeException(ErrorConstants.COUNTRY_NOT_FOUND);}
				country.setIsDeleted(Boolean.TRUE);
				country.setModifiedBy(Constants.ADMIN);
				country.setCountryCode(Constants.DELETE_TOKEN+CommonUtil.getLastForDigitsOfNowTimeStamp()+country.getCountryCode());
				country.setCountryUpdatedDate(new Timestamp(System.currentTimeMillis()));
				getCountryService().save(country);
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "Country successfully deleted.");
			} else{throw new VisiitRunTimeException(ErrorConstants.COUNTRY_REFERRING_BY_VENDOR_OR_HOTE_PACKAGE);}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting country.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deleteStates","/deleteStates"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteStatesByStateId(@RequestParam("stateId") String stateId) {
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(stateId);
			Long packCount = packageService.getPackageCountByPackLoaction(id);
			Long hotelCount = hotelService.getHotelCountByStateId(id);
			Long vendorCount = vendorService.getVendorCountByStateId(id);
			if((packCount == null || packCount == 0) && (hotelCount == null || hotelCount == 0) && (vendorCount == null || vendorCount == 0)){
				State state = getCountryService().getState(id);
				if(state != null){
					state.setStateCode(Constants.DELETE_TOKEN+CommonUtil.getLastForDigitsOfNowTimeStamp()+state.getStateCode());
					state.setIsDeleted(Boolean.TRUE);
					state.setModifiedBy(Constants.ADMIN);
					state.setStateUpdatedDate(new Timestamp(System.currentTimeMillis()));
					getCountryService().save(state);
				}
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "State successfully deleted.");
			} else{throw new VisiitRunTimeException(ErrorConstants.STATE_REFERRING_BY_VENDOR_OR_HOTE_PACKAGE);}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting state.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deleteCity","/deleteCity"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteCityByStId(@RequestParam("cityId") String cityId) {
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(cityId);
			Long packCount = packageService.getPackageCountByPackLoaction(id);
			Long hotelCount = hotelService.getHotelCountByCityId(id);
			Long vendorCount = vendorService.getVendorCountByCityId(id);
			if((packCount == null || packCount == 0) && (hotelCount == null || hotelCount == 0) && (vendorCount == null || vendorCount == 0)){
				City city = getCountryService().getCity(id);
				if(city != null){
					city.setCityCode(Constants.DELETE_TOKEN+CommonUtil.getLastForDigitsOfNowTimeStamp()+city.getCityCode());
					city.setIsDeleted(Boolean.TRUE);
					city.setModifiedBy(Constants.ADMIN);
					city.setCityUpdatedDate(new Timestamp(System.currentTimeMillis()));
					getCountryService().save(city);
				}
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, "City successfully deleted.");
			} else{throw new VisiitRunTimeException(ErrorConstants.CITY_REFERRING_BY_VENDOR_OR_HOTE_PACKAGE);}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting city.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deleteLocation","/deleteLocation"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteLocByCityId(@RequestParam("locationId") String locationId) {
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(locationId);
			Long packCount = packageService.getPackageCountByPackLoaction(id);
			if((packCount == null || packCount == 0)){
				Locations loc = getCountryService().getLocation(id);
				if(loc != null){
					loc.setLocCode(Constants.DELETE_TOKEN+CommonUtil.getLastForDigitsOfNowTimeStamp()+loc.getLocCode());
					loc.setIsDeleted(Boolean.TRUE);
					loc.setModifiedBy(Constants.ADMIN);
					loc.setLocUpdatedDate(new Timestamp(System.currentTimeMillis()));
					getCountryService().save(loc);
				}
			} else{throw new VisiitRunTimeException(ErrorConstants.LOCATION_REFERRING_BY_VENDOR_OR_HOTE_PACKAGE);}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("message", "Location successfully deleted.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting location.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/editCountry","/editCountry"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap editCountry(@RequestParam("countryId") String countryId) {
		Country country = null;
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(countryId);
			country = getCountryService().getCountry(id);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("country", country);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/editState","/editState"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap editState(@RequestParam("stateId") String stateId) {
		State state = null;
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(stateId);
			state = getCountryService().getState(id);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("state", state);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/editCity","/editCity"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap editCity(@RequestParam("cityId") String cityId) {
		City city = null;
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(cityId);
			city = getCountryService().getCity(id);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("city", city);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/editLocation","/editLocation"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap editLocation(@RequestParam("locationId") String locationId) {
		Locations loc = null;
		ModelMap model = new ModelMap();
		try {
			Integer id = Integer.parseInt(locationId);
			loc = getCountryService().getLocation(id);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("loc", loc);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	@Autowired(required = true)
	@Qualifier(value = "countryService")
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
}
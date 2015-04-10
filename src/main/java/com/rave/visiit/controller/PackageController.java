package com.rave.visiit.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.City;
import com.rave.visiit.entity.Country;
import com.rave.visiit.entity.Featuredpackage;
import com.rave.visiit.entity.Locations;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCategory;
import com.rave.visiit.entity.PackageConditions;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageHotel;
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageItinerary;
import com.rave.visiit.entity.PackageLocation;
import com.rave.visiit.entity.PackageView;
import com.rave.visiit.entity.State;
import com.rave.visiit.entity.Vendor;
import com.rave.visiit.model.FeaturedPack;
import com.rave.visiit.model.HotelModel;
import com.rave.visiit.model.IdNamePair;
import com.rave.visiit.model.PackageActivity;
import com.rave.visiit.model.PackageConditionModel;
import com.rave.visiit.model.PackageExclusionModel;
import com.rave.visiit.model.PackageInclusionModel;
import com.rave.visiit.model.PackageModel;
import com.rave.visiit.model.PackagePrice;
import com.rave.visiit.model.PackageViewDetailsModel;
import com.rave.visiit.model.SearchField;
import com.rave.visiit.service.CountryService;
import com.rave.visiit.service.FeaturedpackageService;
import com.rave.visiit.service.PackageCategoryService;
import com.rave.visiit.service.PackageConditionService;
import com.rave.visiit.service.PackageCostService;
import com.rave.visiit.service.PackageExclusionService;
import com.rave.visiit.service.PackageHotelService;
import com.rave.visiit.service.PackageInclusionService;
import com.rave.visiit.service.PackageItineraryService;
import com.rave.visiit.service.PackageLocationService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.Helper;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class PackageController {

	private static Logger logger = LogManager
			.getLogger(PackageController.class);

	@Autowired(required = true)
	@Qualifier(value = "packageService")
	PackageService packageService;

	@Autowired(required = true)
	@Qualifier(value = "countryService")
	CountryService countryService;

	@Autowired(required = true)
	@Qualifier(value = "packageItineraryService")
	PackageItineraryService packageItineraryService;

	@Autowired(required = true)
	@Qualifier(value = "packageCostService")
	PackageCostService packageCostService;

	@Autowired(required = true)
	@Qualifier(value = "packageLocationService")
	PackageLocationService packageLocationService;

	@Autowired(required = true)
	@Qualifier(value = "featuredpackageService")
	FeaturedpackageService featuredpackageService; //

	@Autowired(required = true)
	@Qualifier(value = "packageConditionService")
	PackageConditionService packageConditionService;

	@Autowired(required = true)
	@Qualifier(value = "packageHotelService")
	PackageHotelService packageHotelService;

	@Autowired(required = true)
	@Qualifier(value = "packageInclusionService")
	PackageInclusionService packageInclusionService;

	@Autowired(required = true)
	@Qualifier(value = "packageExclusionService")
	PackageExclusionService packageExclusionService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageCategoryService")
	PackageCategoryService packageCategoryService;

	@RequestMapping("/packageCategory")
	public ModelAndView createPackageCategory() {
		return new ModelAndView("PackageCategory", "message",
				"loged in successfuly");
	}

	@RequestMapping("/package")
	public ModelAndView createPackage() {
		return new ModelAndView("PackageMaster", "message",
				"loged in successfuly");
	}

	@RequestMapping(value = {"/secure/getAllPack","/getAllPack"}, method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody ModelMap getAllPack() {
		ModelMap model = new ModelMap();
		List<Package> packList = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			packList = this.packageService.getAll();
			if (packList != null && !CollectionUtils.isEmpty(packList)) {
				model.put("packList", packList);
				model.put(Constants.STATUS_STR, Constants.OK_STR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getFeaturedPack","/getFeaturedPack"}, method = RequestMethod.POST, headers = "Accept=application/x-www-form-urlencoded")
	public @ResponseBody String getAllFeaturedPack() {
		String resultStr = "";
		List<FeaturedPack> featuredPacks = new ArrayList<FeaturedPack>();
		List<Package> packList = null;
		List packs = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			packList = this.packageService.getFeatured();
			packs = packageCostService.getAllIn(packList);
			Object[] obj = null;
			for (int i = 0; i < packs.size(); i++) {
				FeaturedPack fpk = new FeaturedPack();
				obj = (Object[]) packs.get(i);
				for (int j = 0; j < obj.length; j++) {
					if (obj[j] instanceof Integer) {
						fpk.setPrice("" + obj[j]);
					} else if (obj[j] instanceof Package) {
						Package pk = (Package) obj[j];
						fpk.setId(pk.getPkId());
						fpk.setCode(pk.getPkCode());
						fpk.setName(pk.getPkName());
						fpk.setDays(pk.getPkDays());
						fpk.setNights(pk.getPkNights());
						fpk.setDesc(pk.getPkDescripton());
						fpk.setImgurl("visiitres/images/package/"
								+ pk.getPkCode() + "/1");
					}
				}
				featuredPacks.add(fpk);
			}
			resultStr = mapper.writeValueAsString(Arrays.asList(featuredPacks));
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultStr;
	}

	// static int l=0, m=0, n=0;
	/*
	 * @RequestMapping(value = "/getPackByCategory", method =
	 * RequestMethod.POST,headers="Accept=application/x-www-form-urlencoded")
	 * public @ResponseBody ModelMap getPackByCategory(@RequestParam("catStr")
	 * String catStr) { ModelMap model = new ModelMap(); List<FeaturedPack>
	 * budgetPacks = new ArrayList<FeaturedPack>(); List<FeaturedPack>
	 * domesticPacks = new ArrayList<FeaturedPack>(); List<FeaturedPack>
	 * internationalPacks = new ArrayList<FeaturedPack>(); List<Package>
	 * packList = null; List packs = null; Category category = null; int l=0;
	 * int m=0; int n=0; try { ObjectMapper mapper = new ObjectMapper();
	 * mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	 * category = new Category(); category.setCatId(Integer.parseInt(catStr));
	 * packList = this.packageService.getByCategory(category);
	 * System.out.println(packList.size()); if(packList.size()>0){ packs =
	 * packageCostService.getAllIn(packList); Object[] obj = null; for (int i =
	 * 0; i < packs.size(); i++) { FeaturedPack bfpk = new FeaturedPack();
	 * FeaturedPack dfpk = new FeaturedPack(); FeaturedPack ifpk = new
	 * FeaturedPack(); boolean isbudget=false, isdomestic=false,
	 * isinternational=false; obj = (Object[]) packs.get(i); for (int j = 0; j <
	 * obj.length; j++) { if(obj[j] instanceof Integer){
	 * bfpk.setPrice(""+obj[j]); }else if(obj[j] instanceof Package){ Package pk
	 * = (Package) obj[j]; if(pk.getPkType() == 1){ isbudget=true;
	 * System.out.println(++l); bfpk.setId(pk.getPkId());
	 * bfpk.setCode(pk.getPkCode()); bfpk.setName(pk.getPkName());
	 * bfpk.setDays(pk.getPkDays()); bfpk.setNights(pk.getPkNights());
	 * bfpk.setDesc(pk.getPkDescripton());
	 * bfpk.setImgurl("resources/images/package/"+pk.getPkCode()+"/1"); }else
	 * if(pk.getPkType() == 2){ isdomestic=true; System.out.println(++m);
	 * dfpk.setId(pk.getPkId()); dfpk.setCode(pk.getPkCode());
	 * dfpk.setName(pk.getPkName()); dfpk.setDays(pk.getPkDays());
	 * dfpk.setNights(pk.getPkNights()); dfpk.setDesc(pk.getPkDescripton());
	 * dfpk.setImgurl("resources/images/package/"+pk.getPkCode()+"/1"); }else
	 * if(pk.getPkType() == 3){ isinternational=true; System.out.println(++n);
	 * ifpk.setId(pk.getPkId()); ifpk.setCode(pk.getPkCode());
	 * ifpk.setName(pk.getPkName()); ifpk.setDays(pk.getPkDays());
	 * ifpk.setNights(pk.getPkNights()); ifpk.setDesc(pk.getPkDescripton());
	 * ifpk.setImgurl("resources/images/package/"+pk.getPkCode()+"/1"); } } }
	 * if(isbudget){ budgetPacks.add(bfpk); }else if(isdomestic){
	 * domesticPacks.add(dfpk); }else
	 * if(isinternational){internationalPacks.add(ifpk);} }} model.put("Status",
	 * "Ok"); model.put("budget", budgetPacks); model.put("domestic",
	 * domesticPacks); model.put("international", internationalPacks);
	 * //resultStr =
	 * "[{\"budget\":"+mapper.writeValueAsString(Arrays.asList(budgetPacks
	 * ))+",\"domestic\":"
	 * +mapper.writeValueAsString(Arrays.asList(domesticPacks)
	 * )+",\"international\":"
	 * +mapper.writeValueAsString(Arrays.asList(internationalPacks))+"}]"; }
	 * catch (Exception e) { e.printStackTrace();
	 * logger.info("Error while getting packages by category " +
	 * e.getMessage()); model.put("Status", "Error"); model.put("Status",
	 * e.getMessage()); } return model; }
	 */

	@RequestMapping(value = "/getPackCode", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getPackCode() {
		String resultStr = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			resultStr = Helper.codeMaker(this.packageService.getMaxCode());
			resultStr = mapper.writeValueAsString(resultStr);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultStr;
	}

	@RequestMapping(value = {"/secure/savePack","/savePack"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap savePack(
			@RequestParam("packStr") String packStr,
			@RequestParam("vendStr") String vendStr,
			@RequestParam("fpkStr") String fpkStr,
			@RequestParam("catArr") String catArr,
			@RequestParam("countryArr") String countryArr,
			@RequestParam("stateArr") String stateArr,
			@RequestParam("cityArr") String cityArr,
			@RequestParam("locArr") String locArr) {
		Package packObj = null;
		ModelMap model = new ModelMap();
		boolean newPack = true;
		List<Country> cntryList = null;
		List<State> stateList = null;
		List<City> cityList = null;
		List<Locations> locList = null;
		List<Category> catList = null;
		List<PackageLocation> packLocations = null;
		List<PackageCategory> packCategory = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			TypeReference<Package> refPack = new TypeReference<Package>() {};
			Package pack = mapper.readValue(packStr, refPack);
			if (pack != null) {
				if (pack.getPkName() == null || pack.getPkName().isEmpty()) {
					throw new Exception(ErrorConstants.NAME_REQUIRED);
				} else if (pack.getPkCode() == null || pack.getPkCode().isEmpty()) {
					String code = Helper.codeMaker(this.packageService.getMaxCode());
					pack.setPkCode(code);
				} else if (pack.getPkDays() == 0) {
					throw new VisiitRunTimeException("Days Required");
				} else if (pack.getPkNights() == 0) {
					throw new VisiitRunTimeException("Nights Required");
				}
			}
			TypeReference<Vendor> refVend = new TypeReference<Vendor>() {};
			Vendor vend = mapper.readValue(vendStr, refVend);
			if (vend == null) {
				throw new VisiitRunTimeException(ErrorConstants.VENDOR_NAME_REQUIRED);
			}
			catList = mapper.readValue(catArr, new TypeReference<ArrayList<Category>>() {});
			if(catList == null || catList.size() == 0){
				throw new VisiitRunTimeException(ErrorConstants.CATEGORY_REQUIRED);
			}
			Category cat = catList.get(0);
			cntryList = mapper.readValue(countryArr,new TypeReference<ArrayList<Country>>() {});
			if (cntryList == null || cntryList.size() == 0) {
				throw new VisiitRunTimeException(ErrorConstants.COUNTRY_REQUIRED);
			}
			stateList = mapper.readValue(stateArr,new TypeReference<ArrayList<State>>() {});
			if (stateList == null || stateList.size() == 0) {
				throw new VisiitRunTimeException(ErrorConstants.STATE_REQUIRED);
			}
			cityList = mapper.readValue(cityArr,new TypeReference<ArrayList<City>>() {});
			if (cityList == null || cityList.size() == 0) {
				throw new VisiitRunTimeException(ErrorConstants.CITY_REQUIRED);
			}
			locList = mapper.readValue(locArr,new TypeReference<ArrayList<Locations>>() {});
			if (locList == null || locList.size() == 0) {
				throw new VisiitRunTimeException(ErrorConstants.LOCATIONS_REQUIRED);
			}
			try {
				if (pack.getPkId() != null && pack.getPkId() != 0){
					newPack = false;
					Package oldvalue = packageService.get(pack.getPkId());
					if(oldvalue != null){
						pack.setIsValid(oldvalue.getIsValid());
						if(oldvalue.getImageUrl()!= null && !oldvalue.getImageUrl().isEmpty()){
						pack.setImageUrl(oldvalue.getImageUrl());}
						if(oldvalue.getTileImageUrl() != null && !oldvalue.getTileImageUrl().isEmpty()){
						pack.setTileImageUrl(oldvalue.getTileImageUrl());}
					}
				}
				
			} catch (NullPointerException ne) {
				ne.printStackTrace();
			}
			pack.setCategory(cat);
			pack.setVendorInformation(vend);
			pack.setPkCost(new BigDecimal(1));
			pack.setPkIsFlexible("N");
			pack.setPkTransfer(Boolean.FALSE);
			pack.setPkIsactive(Boolean.TRUE);
			pack.setPkModifiedBy(Constants.ADMIN);
			pack.setPkModifiedOn(new Timestamp(System.currentTimeMillis()));
			packObj = packageService.saveorupdate(pack);
			packCategory = new ArrayList<PackageCategory>();
			for (Category catlist : catList) {
				packCategory.add(new PackageCategory(packObj.getPkId(),catlist.getCatId(),new Timestamp(System.currentTimeMillis()), Constants.ADMIN));
			}
			packageCategoryService.saveorupdate(packCategory, newPack, pack.getPkId());
			packLocations = addPacklocations(packObj.getPkId(), cntryList,stateList, cityList, locList);
			packageLocationService.saveorupdate(packLocations, newPack,	pack.getPkId());
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(Constants.MESSAGE_STR,Constants.PACKAGE_SUCCESSFULLY_SAVED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	public List<PackageLocation> addPacklocations(int pack,
			List<Country> cntryList, List<State> stateList,
			List<City> cityList, List<Locations> locList) {
		List<PackageLocation> packLocations = new ArrayList<PackageLocation>();
		if (cntryList != null) {
			for (Country cntry : cntryList) {
				packLocations.add(new PackageLocation(cntry.getCountryId(),
						pack, 1, "country", Boolean.TRUE, new Timestamp(System
								.currentTimeMillis()), Constants.ADMIN));
			}
			if (stateList != null) {
				for (State state : stateList) {
					packLocations.add(new PackageLocation(state.getStateId(),
							pack, 2, "city", Boolean.TRUE, new Timestamp(System
									.currentTimeMillis()), Constants.ADMIN));
				}
				if (cityList != null) {
					for (City city : cityList) {
						packLocations.add(new PackageLocation(city.getCityId(),
								pack, 3, "state", Boolean.TRUE, new Timestamp(
										System.currentTimeMillis()),
								Constants.ADMIN));
					}
					if (locList != null) {
						for (Locations loc : locList) {
							packLocations.add(new PackageLocation(loc
									.getLocId(), pack, 4, "location",
									Boolean.TRUE, new Timestamp(System
											.currentTimeMillis()),
									Constants.ADMIN));
						}
					}
				}
			}
		}
		return packLocations;
	}

	@RequestMapping(value = {"/secure/removePack","/removePack"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap removePack(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		try {
			Package pack = packageService.get(Integer.parseInt(packStr));
			if (pack == null) {
				throw new VisiitRunTimeException(ErrorConstants.PACKAGE_DETAIL_NOF_FOUND);
			}
			pack.setIsDeleted(Boolean.TRUE);
			packageService.saveorupdate(pack);
			List<PackageLocation> locations = packageLocationService
					.getAllPackageLocationByPackage(pack.getPkId());
			if (locations != null && !locations.isEmpty()) {
				List<PackageLocation> updatedLocations = new ArrayList<PackageLocation>();
				for (PackageLocation packageLocation : locations) {
					packageLocation.setIsDeleted(Boolean.TRUE);
					updatedLocations.add(packageLocation);
				}
				packageLocationService.saveorupdate(updatedLocations, false,
						pack.getPkId());
			}
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(Constants.MESSAGE_STR, "Package successfully Removed");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while removing Package....\n\n"
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = "/checkIsPackFeatured", method = RequestMethod.POST, headers = "Accept=application/x-www-form-urlencoded")
	public @ResponseBody String getFeaturedPack(
			@RequestParam("packStr") String packStr) {
		String resultStr = "";
		Featuredpackage pack = null;
		try {
			pack = featuredpackageService.getByPack(Integer.parseInt(packStr));
			resultStr = pack != null ? "Y" : "N";
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultStr;
	}

	// Package Location

	@RequestMapping(value = {"/secure/getPackLoc","/getPackLoc"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getPackLoc(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<Category> categories = null;
		List<PackageLocation> packLocations = null;
		List<Country> countries = new ArrayList<Country>();
		List<State> states = new ArrayList<State>();
		List<City> cities = new ArrayList<City>();
		List<Locations> locations = new ArrayList<Locations>();
		try {
			categories = packageCategoryService.getAllCatByPackCategory(Integer.parseInt(packStr));
			Package pack = packageService.get(Integer.parseInt(packStr));
			packLocations = packageLocationService.getAll(Integer.parseInt(packStr));
			for (PackageLocation location : packLocations) {
				if (location.getPklType() == 1) {
					countries.add(countryService.getCountry(location.getPklLocation()));
				} else if (location.getPklType() == 2) {
					states.add(countryService.getState(location.getPklLocation()));
				} else if (location.getPklType() == 3) {
					cities.add(countryService.getCity(location.getPklLocation()));
				} else if (location.getPklType() == 4) {
					locations.add(countryService.getLocation(location.getPklLocation()));
				}
			}
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("categories", categories);
			model.put("countries", countries);
			model.put("states", states);
			model.put("cities", cities);
			model.put("locations", locations);
			model.put("pack", pack);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while retrieving Package location....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	// package category
	
	@RequestMapping(value = {"/secure/getPackCategory","/getPackCategory"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getPackCategory(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<Category> packCategory = null;
		try{
			packCategory = packageCategoryService.getAllCatByPackCategory(Integer.parseInt(packStr));
			model.put("categories", packCategory);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while retrieving Package Category....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	// Package Activity

	@RequestMapping(value = {"/secure/getAllAct","/getAllAct"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getAllAct(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageItinerary> packList = null;
		try {
			packList = packageItineraryService.getAll(Integer.parseInt(packStr));
			if (packList == null || packList.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.ACTIVITY_NOT_FOUND);
			}
			List<PackageActivity> activites = populatePackageActivity(packList);
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("packList", activites);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error occur while getting packActivity List "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/saveAct","/saveAct"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveAct(
			@RequestParam("actStr") String actStr,
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<PackageItinerary> refPackIt = new TypeReference<PackageItinerary>() {};
			PackageItinerary packIt = mapper.readValue(actStr, refPackIt);
			TypeReference<Package> refPack = new TypeReference<Package>() {};
			Package pack = mapper.readValue(packStr, refPack);
			boolean dayExists = packageItineraryService.checkDayExists(pack.getPkId(), packIt.getPkiDay());
			if (dayExists) {
				throw new VisiitRunTimeException("Day :" + packIt.getPkiDay() + " Already Exists");
			}
			pack = packageService.get(pack.getPkId());
			packIt.setPack(pack);
			if(packIt.getPkiSeqId() != null){
				PackageItinerary oldValue = packageItineraryService.get(packIt.getPkiSeqId());
				if(oldValue != null){
					if(oldValue.getImageUrl() != null && !oldValue.getImageUrl().isEmpty()){
						packIt.setImageUrl(oldValue.getImageUrl());
					}
				}
			}
			packIt.setPkiIsactive(Boolean.TRUE);
			packIt.setPkiModifiedBy(Constants.ADMIN);
			packIt.setPkiModifiedOn(new Timestamp(System.currentTimeMillis()));
			packageItineraryService.saveorupdate(packIt);
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(Constants.MESSAGE_STR,Constants.PACKAGE_ITINERARY_SUCCESSFULLY_SAVED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Package Itinerary....\n\n  "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/removeAct","/removeAct"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap removeAct(
			@RequestParam("actStr") String actStr) {
		ModelMap model = new ModelMap();
		try {
			if (actStr != null && !actStr.isEmpty()) {
				PackageItinerary packIt = packageItineraryService.get(Integer.parseInt(actStr));
				if (packIt == null) {throw new VisiitRunTimeException(ErrorConstants.ACTIVITY_NOT_FOUND);}
				packIt.setIsDeleted(Boolean.TRUE);
				packageItineraryService.saveorupdate(packIt);
				model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
				model.put(Constants.MESSAGE_STR,Constants.PACKAGE_ITINERARY_SUCCESSFULLY_REMOVED);
			}
		} catch (Exception e) {
			logger.error("Error Occured while removing Package Itinerary....\n\n "+ e.getMessage());
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	// Package Price

	@RequestMapping(value = {"/secure/getAllPrice","/getAllPrice"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getAllPrice(@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageCost> packList = null;
		try {
			packList = packageCostService.getAll(Integer.parseInt(packStr));
			if (packList == null || packList.isEmpty()) {throw new VisiitRunTimeException(ErrorConstants.PRICE_NOT_FOUND);}
			List<PackagePrice> prices = populatePackagePrice(packList);
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("packList", prices);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while getting Package Cost....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getAllValidPrice","/getAllValidPrice"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllPriceByValidDate(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageCost> packList = null;
		try {
			packList = packageCostService.getAllPrice(Integer.parseInt(packStr));
			if (packList == null || packList.isEmpty()) {throw new VisiitRunTimeException(ErrorConstants.PRICE_NOT_FOUND);}
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("packList", packList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while getting Package Cost....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/savePrice","/savePrice"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap savePrice(
			@RequestParam("priceStr") String priceStr,
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<PackageCost> refPackIt = new TypeReference<PackageCost>() {
			};
			PackageCost packCost = mapper.readValue(priceStr, refPackIt);
			TypeReference<Package> refPack = new TypeReference<Package>() {
			};
			Package pack = mapper.readValue(packStr, refPack);
			pack = packageService.get(pack.getPkId());
			if(pack != null && pack.getPkSpecial() != null && !pack.getPkSpecial().isEmpty() && "hot deals".equalsIgnoreCase(pack.getPkSpecial())){
				if(packCost.getOfferPrice() == null || packCost.getOfferPrice() == 0){
					throw new VisiitRunTimeException("Offer Price Required or Should not be zero");
				}
			}
	
			
			packCost.setPack(pack);
			packCost.setPkcModifiedBy(Constants.ADMIN);
			packCost.setValidDays(calculateDays(packCost.getPkcValidFrom(),
					packCost.getPkcValidTo()));
			packCost.setPkcModifiedOn(new Timestamp(System.currentTimeMillis()));
			
			Date fromDate = CommonUtil.getDateFormat(packCost.getPkcValidFrom(),0,0,0);			
			packCost.setPkcValidFrom(fromDate);
			
		
			Date toDate = CommonUtil.getDateFormat(packCost.getPkcValidTo(),23,59,59);			
			packCost.setPkcValidTo(toDate);
			
			packageCostService.saveorupdate(packCost);

			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(Constants.MESSAGE_STR,
					Constants.PACKAGE_PRICE_SUCCESSFULLY_SAVED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Package Price....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}



	@RequestMapping(value = {"/secure/removePrice","/removePrice"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap removePrice(
			@RequestParam("priceStr") String priceStr) {
		ModelMap model = new ModelMap();
		try {
			if (priceStr != null && !priceStr.isEmpty()) {
				PackageCost packCost = packageCostService.get(Integer
						.parseInt(priceStr));
				packCost.setIsDeleted(Boolean.TRUE);
				packageCostService.saveorupdate(packCost);
				model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
				model.put(Constants.MESSAGE_STR,
						Constants.PACKAGE_PRICE_SUCCESSFULLY_REMOVED);
			} else {
				model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
				model.put(Constants.MESSAGE_STR,
						"Please Select price to delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while removing Package Price....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	private int calculateDays(Date startDate, Date endDate)
			throws ParseException {
		Calendar with = Calendar.getInstance();
		with.setTime(startDate);
		Calendar to = Calendar.getInstance();
		to.setTime(endDate);
		to.set(Calendar.YEAR, with.get(Calendar.YEAR));
		int withDAY = with.get(Calendar.DAY_OF_YEAR);
		int toDAY = to.get(Calendar.DAY_OF_YEAR);
		int diffDay = toDAY - withDAY;
		System.out.println(diffDay);
		return diffDay;
	}

	private List<PackageActivity> populatePackageActivity(
			List<PackageItinerary> packList) {

		List<PackageActivity> packageActivities = new ArrayList<PackageActivity>();

		for (PackageItinerary packageItinerary : packList) {
			PackageActivity packageActivity = new PackageActivity();
			packageActivity.setPkiDay(packageItinerary.getPkiDay());
			packageActivity.setPkiDescription(packageItinerary
					.getPkiDescription());
			packageActivity.setPkiItinerary(packageItinerary.getPkiItinerary());
			packageActivity.setPkiSeqId(packageItinerary.getPkiSeqId());
			packageActivity.setPkId(packageItinerary.getPack().getPkId());
			packageActivity.setImageUrl(packageItinerary.getImageUrl());
			packageActivity.setPkBus(packageItinerary.isPkBus());
			packageActivity.setPkCampfire(packageItinerary.getPkCampfire());
			packageActivity.setPkFerry(packageItinerary.isPkFerry());
			packageActivity.setPkFlight(packageItinerary.isPkFlight());
			packageActivity.setPkFood(packageItinerary.isPkFood());
			packageActivity.setPkGuide(packageItinerary.isPkHotel());
			packageActivity.setPkHotel(packageItinerary.isPkHotel());
			packageActivity.setPkJungle(packageItinerary.getPkJungle());
			packageActivity.setPkPickUp(packageItinerary.getPkPickUp());
			packageActivity.setPkSeaActivity(packageItinerary.getPkSeaActivity());
			packageActivity.setPkShows(packageItinerary.getPkShows());
			packageActivity.setPkSports(packageItinerary.getPkSports());
			packageActivity.setPkTrain(packageItinerary.isPkTrain());
			packageActivities.add(packageActivity);
		}

		return packageActivities;

	}

	private List<PackagePrice> populatePackagePrice(List<PackageCost> packList) {

		List<PackagePrice> packagePrices = new ArrayList<PackagePrice>();

		for (PackageCost packageCost : packList) {
			String fromDate = CommonUtil.getDateFormat(packageCost
					.getPkcValidFrom());
			String toDate = CommonUtil.getDateFormat(packageCost
					.getPkcValidTo());
			PackagePrice packagePrice = new PackagePrice();
			packagePrice.setPkcCost(packageCost.getPkcCost());
			packagePrice.setPkcDescription(packageCost.getPkcDescription());
			packagePrice.setPkcId(packageCost.getPkcId());
			if(packageCost.getValidDays() != null){
				packagePrice.setValidDays(packageCost.getValidDays());
			}
			packagePrice.setPkId(packageCost.getPack().getPkId());
			packagePrice.setValidFrom(packageCost.getPkcValidFrom());
			packagePrice.setValidTo(packageCost.getPkcValidTo());
			packagePrice.setValidFromTime(convertStringToTime(fromDate));
			packagePrice.setValidToTime(convertStringToTime(toDate));
			if (packageCost.getOfferPrice() != null) {
				packagePrice.setOfferPrice(packageCost.getOfferPrice());
			}
			packagePrices.add(packagePrice);
		}

		return packagePrices;

	}
	
	private Timestamp convertStringToTime(String dateStr){
		Timestamp timestamp = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Date parsedDate = dateFormat.parse(dateStr);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		   
		}catch(Exception e){
		 	e.printStackTrace();
		}
		 return timestamp;
	}
	private List<PackageConditionModel> populatePackageCondition(
			List<PackageConditions> packList) {

		List<PackageConditionModel> packageConditions = new ArrayList<PackageConditionModel>();

		for (PackageConditions packageCondition : packList) {
			PackageConditionModel packageConditionModel = new PackageConditionModel();
			packageConditionModel.setPcId(packageCondition.getPcSeqId());
			packageConditionModel.setPcDescription(packageCondition
					.getPcDescriptions());
			packageConditions.add(packageConditionModel);
		}

		return packageConditions;

	}

	@RequestMapping(value = {"/secure/saveConditions","/saveConditions"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveConditions(
			@RequestParam("conStr") String conStr,
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<PackageConditions> refPackIt = new TypeReference<PackageConditions>() {
			};
			PackageConditions packConditions = mapper.readValue(conStr,
					refPackIt);
			TypeReference<Package> refPack = new TypeReference<Package>() {
			};
			Package pack = mapper.readValue(packStr, refPack);
			packConditions.setPack(pack);
			packConditions.setPcIsactive(Boolean.TRUE);
			packConditions.setPcModifiedBy(Constants.ADMIN);
			packConditions.setPcModifiedOn(new Timestamp(System
					.currentTimeMillis()));
			packageConditionService.saveorupdate(packConditions);
			model.put("Status", "Ok");
			model.put("message", "Package Condition successfully saved");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Package Condition....\n\n  "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getAllConditions","/getAllConditions"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getAllConditions(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageConditions> packList = null;
		try {
			packList = packageConditionService
					.getAll(Integer.parseInt(packStr));
			if (packList == null || packList.isEmpty()) {
				throw new VisiitRunTimeException("Conditions Not Found");
			}
			List<PackageConditionModel> packageConditions = populatePackageCondition(packList);
			model.put("Status", "Ok");
			model.put("packList", packageConditions);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error occur while getting packActivity List "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/removeCondition","/removeCondition"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap removeCondition(
			@RequestParam("conStr") String conStr) {
		ModelMap model = new ModelMap();
		try {
			if (conStr != null && !conStr.isEmpty()) {
				PackageConditions packIt = packageConditionService.get(Integer
						.parseInt(conStr));
				if (packIt == null) {
					throw new VisiitRunTimeException(ErrorConstants.CONDITIONS_NOT_FOUND);
				}
				packIt.setIsDeleted(Boolean.TRUE);
				packageConditionService.saveorupdate(packIt);
				model.put("Status", "Ok");
				model.put("message", "Package Condition successfully Removed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while removing Package Itinerary....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getAllCitiesByPackage","/getAllCitiesByPackage"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllCitiesByPackage(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageLocation> packLocations = null;
		List<City> cities = new ArrayList<City>();
		try {
			packLocations = packageLocationService.getAll(Integer
					.parseInt(packStr));
			if (packLocations == null || packLocations.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.CITY_NOT_FOUND);
			}
			for (PackageLocation location : packLocations) {
				if (location.getPklType() == 3) {
					cities.add(countryService.getCity(location.getPklLocation()));
				}
			}
			model.put("Status", "Ok");
			model.put("cities", cities);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while retrieving Package location....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/viewDetails","/viewDetails"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getViewDetails(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		PackageViewDetailsModel packageViewDetailsModel = new PackageViewDetailsModel();
		try {
			// Package Details
			Package pageDetails = packageService.get(Integer.parseInt(packStr));
			List<String> reviewCode = packageLocationService
					.getPackageLocationByPackage(Integer.parseInt(packStr));
			if (reviewCode != null && !reviewCode.isEmpty()) {
				packageViewDetailsModel.setLocationReviewCode(reviewCode);
			}
			packageViewDetailsModel.setImageUrl(pageDetails.getImageUrl());
			packageViewDetailsModel.setPackageName(pageDetails.getPkName());
			packageViewDetailsModel.setPackageId(pageDetails.getPkId());
			packageViewDetailsModel.setDays(pageDetails.getPkDays());
			packageViewDetailsModel.setNights(pageDetails.getPkNights());

			// Lowest Price
			PackageCost packageCost = packageCostService.getminimumPrice(Integer.parseInt(packStr));
			if (packageCost != null) {
				if(packageCost.getOfferPrice() != null && packageCost.getOfferPrice() > 0){
					packageViewDetailsModel.setLowPrize(packageCost.getOfferPrice());
					packageViewDetailsModel.setOriginalPrice(packageCost.getPkcCost());
					packageViewDetailsModel.setOfferPrice(packageCost.getOfferPrice());
				}else {
					packageViewDetailsModel.setLowPrize(packageCost.getPkcCost());
					packageViewDetailsModel.setOriginalPrice(packageCost.getPkcCost());
				}
				packageViewDetailsModel.setLowprizeId(packageCost.getPkcId());
				packageViewDetailsModel.setPriceDescription(packageCost.getPkcDescription());
			} else {
				packageViewDetailsModel.setLowPrize(0);
				packageViewDetailsModel.setPriceDescription("");
			}

			// Package Price list
			List<PackageCost> packageCostList = packageCostService
					.getAllPrice(Integer.parseInt(packStr));
			List<PackagePrice> packagePriceList = populatePackagePriceModel(packageCostList);
			packageViewDetailsModel.setPackagePriceList(packagePriceList);

			// Conditions
			List<PackageConditions> packageConditionList = packageConditionService
					.getAll(Integer.parseInt(packStr));
			List<PackageConditionModel> packageConditionModelList = populatePackageConditionModel(packageConditionList);
			packageViewDetailsModel
					.setPackageConditionModelList(packageConditionModelList);

			// Activity
			List<PackageItinerary> packageItineraryList = packageItineraryService
					.getAll(Integer.parseInt(packStr));
			List<PackageActivity> packageActivityList = populatePackageActivityModel(packageItineraryList);
			packageViewDetailsModel
					.setPackageActivityModel(packageActivityList);

			// Inclusion
			List<PackageInclusion> packageInclusion = packageInclusionService
					.getValideInclusionByPackage(Integer.parseInt(packStr));

			List<PackageInclusionModel> inclusionModelList = populatePackageInclusion(packageInclusion);
			packageViewDetailsModel
					.setPackageInclusionModelList(inclusionModelList);

			// Exclustion
			List<PackageExclusion> packageExclusion = packageExclusionService
					.getValideExclusionByPackage(Integer.parseInt(packStr));

			List<PackageExclusionModel> exclusionModelList = populatePackageExclusion(packageExclusion);
			packageViewDetailsModel
					.setPackageExclusionModelList(exclusionModelList);
			//
			// // Hotels
			List<PackageHotel> packageHotels = packageHotelService
					.getAll(Integer.parseInt(packStr));
			List<HotelModel> hotelModelList = populatePackageHotel(packageHotels);
			packageViewDetailsModel.setHotelModelList(hotelModelList);

			model.put("Status", "Ok");
			model.put("packageViewDetailsModel", packageViewDetailsModel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while retrieving View....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	private List<PackageInclusionModel> populatePackageInclusion(
			List<PackageInclusion> packageInclusion) {
		List<PackageInclusionModel> inclusionModelList = new ArrayList<PackageInclusionModel>();
		for (PackageInclusion inclusion : packageInclusion) {
			PackageInclusionModel inclusionModel = new PackageInclusionModel();
			inclusionModel.setId(inclusion.getPkinSeqId());
			inclusionModel.setDesc(inclusion.getPkinDescription());
			inclusionModel.setPackageId(inclusion.getPack().getPkId());
			inclusionModel.setImageUrl(inclusion.getImageUrl());
			inclusionModelList.add(inclusionModel);
		}
		return inclusionModelList;
	}

	private List<PackageExclusionModel> populatePackageExclusion(
			List<PackageExclusion> packageExclusion) {
		List<PackageExclusionModel> exclusionModelList = new ArrayList<PackageExclusionModel>();

		for (PackageExclusion exclusion : packageExclusion) {
			PackageExclusionModel exclusionModel = new PackageExclusionModel();
			exclusionModel.setId(exclusion.getPkexSeqId());
			exclusionModel.setPackageId(exclusion.getPack().getPkId());
			exclusionModel.setDesc(exclusion.getPkexDescription());
			exclusionModel.setImageUrl(exclusion.getImageUrl());
			exclusionModelList.add(exclusionModel);
		}
		return exclusionModelList;
	}

	private List<HotelModel> populatePackageHotel(List<PackageHotel> hotelList) {
		List<HotelModel> hotelModels = new ArrayList<HotelModel>();
		for (PackageHotel packageHotel : hotelList) {
			HotelModel hotelModel = new HotelModel();
			hotelModel.setHdId(packageHotel.getHotel().getHdSeqId());
			hotelModel.setPkId(packageHotel.getPack().getPkId());
			hotelModel.setCity(packageHotel.getCity());
			hotelModel.setStatus(packageHotel.getStatus());
			hotelModel.setHdName(packageHotel.getHotel().getHdName());
			hotelModel.setImageUrl(packageHotel.getImageUrl());
			hotelModel.setHdType(packageHotel.getHotel().getHdType());
			hotelModel.setHdBar(packageHotel.getHotel().isHdBar());
			hotelModel.setHdFood(packageHotel.getHotel().isHdFood());
			hotelModel.setHdSwimmingpool(packageHotel.getHotel().isHdSwimmingpool());
			hotelModel.setHdGym(packageHotel.getHotel().isHdGym());
			hotelModel.setHafHelthclubYoga(packageHotel.getHotel().isHafHelthclubYoga());
			hotelModel.setHdAirCondition(packageHotel.getHotel().getHdAirCondition());
			hotelModel.setHdDoctorOnCall(packageHotel.getHotel().getHdDoctorOnCall());
			hotelModel.setHdlaundry(packageHotel.getHotel().getHdlaundry());
			hotelModel.setHdLibrary(packageHotel.getHotel().getHdLibrary());
			hotelModel.setHdSPA(packageHotel.getHotel().getHdSPA());
			hotelModel.setHdTravelDesk(packageHotel.getHotel().getHdTravelDesk());
			hotelModel.setHdTVDVD(packageHotel.getHotel().getHdTVDVD());
			hotelModel.setHdBussinessCenter(packageHotel.getHotel().getHdBussinessCenter());
			hotelModel.setHdHeater(packageHotel.getHotel().getHdHeater());
			hotelModel.setHdIroning(packageHotel.getHotel().getHdIroning());
			hotelModel.setHdKids(packageHotel.getHotel().getHdKids());
			hotelModel.setHdDialphone(packageHotel.getHotel().getHdDialphone());
			hotelModel.setHdRoomsafe(packageHotel.getHotel().getHdRoomsafe());
			hotelModel.setHdVilla(packageHotel.getHotel().getHdVilla());
			hotelModel.setHdShower(packageHotel.getHotel().getHdShower());
			hotelModel.setHdPickup(packageHotel.getHotel().getHdPickup());
			hotelModel.setHdFloralRequest(packageHotel.getHotel().getHdFloralRequest());
			hotelModel.setHdDescription(packageHotel.getHotel().gethdDescription());
			hotelModel.setReviewCode(packageHotel.getHotel().getReviewCode());
			hotelModels.add(hotelModel);
		}

		return hotelModels;

	}

	private List<PackageConditionModel> populatePackageConditionModel(
			List<PackageConditions> packageConditionList) {
		List<PackageConditionModel> packageConditionModelList = new ArrayList<PackageConditionModel>();
		for (PackageConditions pack : packageConditionList) {
			PackageConditionModel packageConditionModel = new PackageConditionModel();
			packageConditionModel.setPkgId(pack.getPack().getPkId());
			packageConditionModel.setPcId(pack.getPcSeqId());
			packageConditionModel.setPcDescription(pack.getPcDescriptions());
			packageConditionModelList.add(packageConditionModel);
		}
		return packageConditionModelList;
	}

	private List<PackageActivity> populatePackageActivityModel(
			List<PackageItinerary> packageItineraryList) {
		List<PackageActivity> packageActivityList = new ArrayList<PackageActivity>();
		for (PackageItinerary packageItinerary : packageItineraryList) {
			PackageActivity packageActivity = new PackageActivity();
			packageActivity.setPkiSeqId(packageItinerary.getPkiSeqId());
			packageActivity.setPkId(packageItinerary.getPack().getPkId());
			packageActivity.setPkiDay(packageItinerary.getPkiDay());
			packageActivity.setImageUrl(packageItinerary.getImageUrl());
			packageActivity.setPkiDescription(packageItinerary.getPkiDescription());
			packageActivity.setPkiItinerary(packageItinerary.getPkiItinerary());
			packageActivity.setPkBus(packageItinerary.isPkBus());
			packageActivity.setPkCampfire(packageItinerary.getPkCampfire());
			packageActivity.setPkFerry(packageItinerary.isPkFerry());
			packageActivity.setPkFlight(packageItinerary.isPkFlight());
			packageActivity.setPkFood(packageItinerary.isPkFood());
			packageActivity.setPkGuide(packageItinerary.isPkHotel());
			packageActivity.setPkHotel(packageItinerary.isPkHotel());
			packageActivity.setPkJungle(packageItinerary.getPkJungle());
			packageActivity.setPkPickUp(packageItinerary.getPkPickUp());
			packageActivity.setPkSeaActivity(packageItinerary.getPkSeaActivity());
			packageActivity.setPkShows(packageItinerary.getPkShows());
			packageActivity.setPkSports(packageItinerary.getPkSports());
			packageActivity.setPkTrain(packageItinerary.isPkTrain());
			packageActivityList.add(packageActivity);
		}
		return packageActivityList;
	}

	private List<PackagePrice> populatePackagePriceModel(
			List<PackageCost> packageCostList) {
		List<PackagePrice> packagePriceList = new ArrayList<PackagePrice>();
		for (PackageCost pack : packageCostList) {
			PackagePrice packagePrice = new PackagePrice();
			packagePrice.setPkcId(pack.getPkcId());
			packagePrice.setPkId(pack.getPack().getPkId());
			packagePrice.setPkcCost(pack.getPkcCost());
			packagePrice.setPkcDescription(pack.getPkcDescription());
			if(pack.getOfferPrice() != null){
				packagePrice.setOfferPrice(pack.getOfferPrice());
			}
			packagePriceList.add(packagePrice);
		}
		return packagePriceList;
	}

	@RequestMapping(value = {"/secure/getPackagesByCategory","/getPackagesByCategory"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getPackagesByCategory(
			@RequestParam("catStr") String catStr) {
		ModelMap model = new ModelMap();
		List<Package> packList = null;
		List<PackageModel> packageModels = new ArrayList<PackageModel>();
		Category category = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			category = new Category();
			category.setCatId(Integer.parseInt(catStr));
			packList = this.packageService.getPackByCategory(category);
			if (packList.size() > 0) {
				packageModels = populatePackageModel(packList);
			}
			if (packageModels == null || packageModels.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.PACKAGE_DETAIL_NOF_FOUND);
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("packageModels", packageModels);
			model.put("count", packageModels.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting packages by category "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getAllPackagesByType","/getAllPackagesByType"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllPackagesByType(
			@RequestParam("type") String type) {
		ModelMap model = new ModelMap();
		List<Package> packList = null;
		List<PackageModel> packageModels = new ArrayList<PackageModel>();
		try {
			if (type != null && !type.isEmpty()) {
				packList = this.packageService.getAllPackagesByType(type);
			} else {
				packList = this.packageService.getAllPackages();
			}
			if (packList != null && packList.size() > 0) {
				if (type.equalsIgnoreCase("hot deals")) {
					packageModels = populatePackageDetailsForHotDeal(packList);
				} else {
					packageModels = populatePackageDetails(packList);
				}
			}
			if (packageModels == null || packageModels.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.PACKAGE_DETAIL_NOF_FOUND);}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("packageModels", packageModels);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting packages by type "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	 @RequestMapping(value = {"/secure/searchPacakges","/searchPacakges"}, method = RequestMethod.GET)
	 public @ResponseBody ModelMap searchPacakges(
	   @RequestParam("searchString") String searchString) {
	  ModelMap model = new ModelMap();
	  Map<String, Object> packages = new HashMap<String, Object>();
	  List<Package> packList = null;
	  List<PackageView> packs = null;
	  try {
	   if(searchString != null && !searchString.isEmpty()){
	    packList = this.packageService.searchPackagesByName(searchString);
	    if (packList != null && packList.size() > 0) {
	     packages.put("packages", populateIdNamePair(packList));
	    }
	    packs = this.packageService.searchPackages(searchString);
	    if (packs != null && packs.size() > 0) {
	     Set<IdNamePair> inclusions = populateInclusionAsIdNamePair(packs,searchString);
	     if(inclusions != null && !inclusions.isEmpty()){
	      packages.put("inclusions", inclusions);
	     }
	     List<IdNamePair> exclusions = populateExclusionAsIdNamePair(packs,searchString);
	     if(exclusions != null && !exclusions.isEmpty()){
	      packages.put("exclusions", exclusions);
	     }
	     List<IdNamePair> activities = populateActivitiesAsIdNamePair(packs,searchString);
	     if(activities != null && !activities.isEmpty()){
	      packages.put("activities", activities);
	     }
	     packList = null;
	    } else {
	     throw new VisiitRunTimeException(ErrorConstants.PACKAGE_DETAIL_NOF_FOUND);
	    }
	   }
	   model.put(Constants.STATUS_STR, Constants.OK_STR);
	   model.put("packages", packages);
	  } catch (Exception e) {
	   e.printStackTrace();
	   logger.info("Error while getting packages by searchPacakges "
	     + e.getMessage());
	   model.put(Constants.STATUS_STR, Constants.ERROR_STR);
	   if(e instanceof VisiitRunTimeException){
	    model.put(Constants.MESSAGE_STR, e.getMessage());
	   }else {
	    model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
	   }
	  }
	  return model;
	 }

	private List<PackageModel> populatePackageModel(List<Package> packs) {
		List<PackageModel> packageModels = new ArrayList<PackageModel>();

		for (Package pack : packs) {
			PackageModel packageModel = new PackageModel();
			packageModel.setPackageName(pack.getPkName());
			packageModel.setDays(pack.getPkDays());
			packageModel.setNights(pack.getPkNights());
			packageModel.setPackageOverview(pack.getPkDescripton());
			packageModel.setPkBus(pack.getPkBusModel());
			packageModel.setPkFerry(pack.getPkFerryModel());
			packageModel.setPkFlight(pack.getPkFlightModel());
			packageModel.setPkFood(pack.getPkFoodModel());
			packageModel.setPkHotel(pack.getPkHotelModel());
			packageModel.setPkTrain(pack.getPkTrainModel());
			packageModel.setPkType(pack.getPkType());
			packageModel.setPkId(pack.getPkId());
			
			if (pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()) {
				packageModel.setTileImageUrl(pack.getTileImageUrl());
			} else {
				packageModel.setTileImageUrl("");
			}
			
		/*	if (pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()) {
				String url[] = pack.getImageUrl().split(",");
				if (url != null && url.length > 0) {
					String tile = url[0];
					packageModel.setTileImg(tile);
				} else {
					packageModel.setTileImg("");
				}
			} else {
				packageModel.setTileImg("");
			}*/
			try {
				PackageCost pkc = packageCostService.getminimumPrice(pack
						.getPkId());
				if (pkc != null) {
					Integer priceCost = pkc.getPkcCost();
					if (priceCost != null) {
						packageModel.setPackagePrice(priceCost);
					} else {
						packageModel.setPackagePrice(0);
					}
				} else {
					packageModel.setPackagePrice(0);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			packageModels.add(packageModel);
		}
		return packageModels;
	}

	private List<PackageModel> populatePackageDetailsForHotDeal(
			List<Package> packs) {
		List<PackageModel> packageModels = new ArrayList<PackageModel>();

		for (Package pack : packs) {
			PackageModel packageModel = new PackageModel();
			packageModel.setPackageName(pack.getPkName());
			/*
			 * packageModel.setDays(pack.getPkDays());
			 * packageModel.setNights(pack.getPkNights());
			 */
			packageModel.setPackageOverview(pack.getPkDescripton());
			if (pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()) {
				packageModel.setTileImageUrl(pack.getTileImageUrl());
			} else {
				packageModel.setTileImageUrl("");
			}
			//packageModel.setTileImageUrl("");
			packageModel.setPkBus("");
			packageModel.setPkFerry("");
			packageModel.setPkFlight("");
			packageModel.setPkFood("");
			packageModel.setPkHotel("");
			packageModel.setPkTrain("");
			packageModel.setPkType(pack.getPkType());
			packageModel.setPkId(pack.getPkId());
			packageModel.setSpecial(pack.getPkSpecial());
			packageModel.setDayAndNight(Integer.toString(pack.getPkNights())
					+ " Nights/ " + Integer.toString(pack.getPkDays())
					+ " Days");
			if (pack.getPkOrder() != null) {
				packageModel.setOrder(pack.getPkOrder());
			}
			if (pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()) {
				packageModel.setImageUrl(pack.getImageUrl());
			} else {
				packageModel.setImageUrl("");
			}
			try {
				PackageCost packagecost = packageCostService
						.getHotDealsPrice(pack.getPkId());
				if (packagecost != null) {
					packageModel.setPackagePrice(packagecost.getPkcCost());
					packageModel.setOfferPrice(packagecost.getOfferPrice());
					packageModels.add(packageModel);
				}
				// packageModels.add(packageModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return packageModels;
	}

	private List<PackageModel> populatePackageDetails(List<Package> packs)
			throws Exception {
		List<PackageModel> packageModels = new ArrayList<PackageModel>();

		for (Package pack : packs) {
			PackageModel packageModel = new PackageModel();
			packageModel.setPackageName(pack.getPkName());
			packageModel.setDays(pack.getPkDays());
			packageModel.setNights(pack.getPkNights());
			packageModel.setPackageOverview(pack.getPkDescripton());
			//packageModel.setTileImg("");
			if (pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()) {
				packageModel.setTileImageUrl(pack.getTileImageUrl());
			} else {
				packageModel.setTileImageUrl("");
			}
			packageModel.setPkBus("");
			packageModel.setPkFerry("");
			packageModel.setPkFlight("");
			packageModel.setPkFood("");
			packageModel.setPkHotel("");
			packageModel.setPkTrain("");
			packageModel.setPkType(pack.getPkType());
			packageModel.setPkId(pack.getPkId());
			packageModel.setOfferPrice(0);
			packageModel.setDayAndNight(Integer.toString(pack.getPkNights())
					+ " Nights/ " + Integer.toString(pack.getPkDays())
					+ " Days");
			PackageCost packagecosts = packageCostService.getminimumPrice(pack
					.getPkId());
			if (packagecosts != null) {
				packageModel.setPackagePrice(packagecosts.getPkcCost());
			}
			packageModel.setSpecial(pack.getPkSpecial());
			if (pack.getPkOrder() != null) {
				packageModel.setOrder(pack.getPkOrder());
			}
			if (pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()) {
				packageModel.setImageUrl(pack.getImageUrl());
			} else {
				packageModel.setImageUrl("");
			}
			packageModels.add(packageModel);
		}
		return packageModels;
	}

	private List<PackageModel> populateSearchPackageDetails(List<Package> packs)
			throws Exception {
		List<PackageModel> packageModels = new ArrayList<PackageModel>();

		for (Package pack : packs) {
			PackageModel packageModel = new PackageModel();
			packageModel.setPackageName(pack.getPkName());
			packageModel.setDays(pack.getPkDays());
			packageModel.setNights(pack.getPkNights());
			packageModel.setPackageOverview(pack.getPkDescripton());
			if (pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()) {
				packageModel.setTileImageUrl(pack.getTileImageUrl());
			} else {
				packageModel.setTileImageUrl("");
			}
			//packageModel.setTileImg("");
			packageModel.setPkBus("");
			packageModel.setPkFerry("");
			packageModel.setPkFlight("");
			packageModel.setPkFood("");
			packageModel.setPkHotel("");
			packageModel.setPkTrain("");
			packageModel.setPkType(pack.getPkType());
			packageModel.setPkId(pack.getPkId());
			packageModel.setDayAndNight(Integer.toString(pack.getPkNights())
					+ " Nights/ " + Integer.toString(pack.getPkDays())+ " Days");
			PackageCost packagecosts = packageCostService.getminimumPrice(pack.getPkId());
			if (packagecosts != null) {
				packageModel.setPackagePrice(packagecosts.getPkcCost());
				if(packagecosts.getOfferPrice() != null){
					packageModel.setOfferPrice(packagecosts.getOfferPrice());
				}
			}
			packageModel.setSpecial(pack.getPkSpecial());
			if (pack.getPkOrder() != null) {
				packageModel.setOrder(pack.getPkOrder());
			}
			if (pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()) {
				packageModel.setImageUrl(pack.getImageUrl());
			} else {
				packageModel.setImageUrl("");
			}
			packageModels.add(packageModel);
		}
		return packageModels;
	}

	private List<PackageModel> populatePackageDetailsForOrder(List<Package> packs) {
		List<PackageModel> packageModels = new ArrayList<PackageModel>();

		for (Package pack : packs) {
			PackageModel packageModel = new PackageModel();
			packageModel.setPackageName(pack.getPkName());
			packageModel.setDays(pack.getPkDays());
			packageModel.setNights(pack.getPkNights());
			packageModel.setPackageOverview(pack.getPkDescripton());
			if (pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()) {
				packageModel.setTileImageUrl(pack.getTileImageUrl());
			} else {
				packageModel.setTileImageUrl("");
			}
			//packageModel.setTileImg("");
			packageModel.setPkBus("");
			packageModel.setPkFerry("");
			packageModel.setPkFlight("");
			packageModel.setPkFood("");
			packageModel.setPkHotel("");
			packageModel.setPkTrain("");
			packageModel.setPkType(pack.getPkType());
			packageModel.setPkId(pack.getPkId());
			packageModel.setSpecial(pack.getPkSpecial());
			if (pack.getPkOrder() != null) {
				packageModel.setOrder(pack.getPkOrder());
			}
			if (pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()) {
				packageModel.setImageUrl(pack.getImageUrl());
			} else {
				packageModel.setImageUrl("");
			}
			try {
				List<PackageCost> packagecosts = packageCostService
						.getOfferPrice(pack.getPkId());
				if (packagecosts != null && !packagecosts.isEmpty()) {
					PackageCost pkc = packagecosts.get(0);
					if (pkc != null) {
						packageModel.setPackagePrice(pkc.getPkcCost());
						packageModel.setOfferPrice(pkc.getOfferPrice());
					} else {
						packageModel.setPackagePrice(0);
						packageModel.setOfferPrice(0);
					}
				} else {
					packageModel.setPackagePrice(0);
					packageModel.setOfferPrice(0);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			packageModels.add(packageModel);
		}
		return packageModels;
	}

	@RequestMapping(value = {"/secure/getAllPackageAuto","/getAllPackageAuto"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllPackageAuto(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<Package> packList = null;
		try {
			packList = packageService.getAllPackageAuto(packStr);
			if (packList == null || packList.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.PACKAGE_DETAIL_NOF_FOUND);
			}
			List<SearchField> searchFields = new ArrayList<SearchField>();
			for (Package pack : packList) {
				SearchField searchField = new SearchField();
				searchField.setKey(pack.getPkId().toString());
				searchField.setValue(pack.getPkName());
				searchFields.add(searchField);
			}
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(ConstantUtil.LISTDATA, searchFields);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error occur while getting packActivity List "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getPriceByPackageId","/getPriceByPackageId"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getPriceByPackageId(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageCost> packList = null;
		try {
			packList = packageCostService.getAllPrice(Integer.parseInt(packStr));
			List<SearchField> searchFields = new ArrayList<SearchField>();
			if (packList != null && !packList.isEmpty() && packList.size() > 0) {			
				for (PackageCost packageCost : packList) {
					SearchField searchField = new SearchField();
					searchField.setKey(packageCost.getPkcId().toString());
					searchField.setValue(packageCost.getPkcCost().toString());				
					searchFields.add(searchField);
				}
			}

			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("packList", searchFields);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while getting Package Cost....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getPackagesBySpecial","/getPackagesBySpecial"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getPackagesBySpecial() {
		ModelMap model = new ModelMap();
		Map<String, Object> packs = new HashMap<String, Object>();
		List<Package> packList = null;
		try {
			packList = packageService.getAllPackagesByType("Trending");
			if(packList != null && !packList.isEmpty()){
				packs.put("trending", populateIdNamePair(packList));
			}
			packList = null;
			packList = packageService.getAllPackagesByType("Hot Deals");
			if(packList != null && !packList.isEmpty()){
				packs.put("hot deals", populateIdNamePair(packList));
			}
			packList = null;
			packList = packageService.getAllPackagesByType("Get Aways");
			if(packList != null && !packList.isEmpty()){
				packs.put("get aways", populateIdNamePair(packList));
			}
			packList = null;
			packList = packageService.getAllPackagesByType("Best Sellers");
			if(packList != null && !packList.isEmpty()){
				packs.put("best sellers", populateIdNamePair(packList));
			}
			packList = null;
			packList = packageService.getAllPackagesByType("Summer Specials");
			if(packList != null && !packList.isEmpty()){
				packs.put("summer special", populateIdNamePair(packList));
			}
			model.put("packages", packs);
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while getting Package Cost....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	private List<IdNamePair> populateIdNamePairFromView(List<PackageView> packList){
		List<IdNamePair> special = new ArrayList<IdNamePair>();
		if (packList != null && !packList.isEmpty() && packList.size() > 0) {			
			for (PackageView pack : packList) {
				if(pack.getPkiDescription() != null && !pack.getPkiDescription().isEmpty()){
					IdNamePair name = new IdNamePair();
					name.setId(pack.getId());
					name.setName(pack.getPkiDescription());
					special.add(name);
				}
				if(pack.getPkinDescription() != null && !pack.getPkinDescription().isEmpty()){
					IdNamePair name = new IdNamePair();
					name.setId(pack.getId());
					name.setName(pack.getPkinDescription());
					special.add(name);
				}
				if(pack.getPkexDescription() != null && !pack.getPkexDescription().isEmpty()){
					IdNamePair name = new IdNamePair();
					name.setId(pack.getId());
					name.setName(pack.getPkexDescription());
					special.add(name);
				}
				if(pack.getPackDescription() != null && !pack.getPackDescription().isEmpty()){
					IdNamePair name = new IdNamePair();
					name.setId(pack.getId());
					name.setName(pack.getPackDescription());
					special.add(name);
				}				
			}
		}
		return special;
	}
	
	private Set<IdNamePair> populateInclusionAsIdNamePair(List<PackageView> packList,String searchString){
		Set<IdNamePair> special = new HashSet<IdNamePair>();
		if (packList != null && !packList.isEmpty() && packList.size() > 0) {	
			Set<Integer> ids = new HashSet<Integer>();
			for (PackageView pack : packList) {
				if(pack.getPkinDescription() != null && !pack.getPkinDescription().isEmpty()  && pack.getPkinDescription().contains(searchString)){
						IdNamePair name = new IdNamePair();
						name.setId(pack.getId());
						name.setName(pack.getPkinDescription());
						name.setObjectId(pack.getPkinSeqId());
						if(!ids.contains(pack.getPkinSeqId())){
							special.add(name);
						}
						ids.add(pack.getPkinSeqId());
				}
			}
		}
		return special;
	}
	
	private List<IdNamePair> populateExclusionAsIdNamePair(List<PackageView> packList,String searchString){
		List<IdNamePair> special = new ArrayList<IdNamePair>();
		if (packList != null && !packList.isEmpty() && packList.size() > 0) {	
			Set<Integer> ids = new HashSet<Integer>();
			for (PackageView pack : packList) {
				if(pack.getPkexDescription() != null && !pack.getPkexDescription().isEmpty() && pack.getPkexDescription().contains(searchString)){
					IdNamePair name = new IdNamePair();
					name.setId(pack.getId());
					name.setName(pack.getPkexDescription());
					name.setObjectId(pack.getPkexSeqId());
					if(!ids.contains(pack.getPkexSeqId())){
						special.add(name);
					}
					ids.add(pack.getPkexSeqId());
				}
			}
		}
		return special;
	}
	
	private List<IdNamePair> populateActivitiesAsIdNamePair(List<PackageView> packList,String searchString){
		List<IdNamePair> special = new ArrayList<IdNamePair>();
		if (packList != null && !packList.isEmpty() && packList.size() > 0) {
			Set<Integer> ids = new HashSet<Integer>();
			for (PackageView pack : packList) {
				if(pack.getPkiDescription() != null && !pack.getPkiDescription().isEmpty()  && pack.getPkiDescription().contains(searchString)){
					IdNamePair name = new IdNamePair();
					name.setId(pack.getId());
					name.setName(pack.getPkiDescription());
					name.setObjectId(pack.getPkiSeqId());
					if(!ids.contains(pack.getPkiSeqId())){
						special.add(name);
					}
					ids.add(pack.getPkiSeqId());
				}
			}
		}
		return special;
	}
	
	@RequestMapping(value = {"/secure/searchAllPacakges","/searchAllPacakges"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap searchAllPacakges(
			@RequestParam("searchString") String searchString) {
		ModelMap model = new ModelMap();
		List<Package> packList = null;
		List<PackageModel> packageModels = new ArrayList<PackageModel>();
		try {
			packList = this.packageService.searchAllPackages(searchString);
			if (packList != null && packList.size() > 0) {
				packageModels = populateSearchAllPackageDetails(packList);}
			if (packageModels == null || packageModels.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.PACKAGE_DETAIL_NOF_FOUND);}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("packageModels", packageModels);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting packages by type "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	private List<IdNamePair> populateIdNamePair(List<Package> packList){
		  List<IdNamePair> special = new ArrayList<IdNamePair>();
		  if (packList != null && !packList.isEmpty() && packList.size() > 0) {   
		   for (Package pack : packList) {
		    IdNamePair name = new IdNamePair();
		    name.setId(pack.getPkId());
		    name.setName(pack.getPkName());
		    special.add(name);
		   }
		  }
		  return special;
		 }
	private List<PackageModel> populateSearchAllPackageDetails(List<Package> packs)
			   throws Exception {
			  List<PackageModel> packageModels = new ArrayList<PackageModel>();

			  for (Package pack : packs) {
			   PackageModel packageModel = new PackageModel();
			   packageModel.setPackageName(pack.getPkName());
			   packageModel.setDays(pack.getPkDays());
			   packageModel.setNights(pack.getPkNights());
			   packageModel.setPackageOverview(pack.getPkDescripton());
			   if (pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()) {
			    packageModel.setTileImageUrl(pack.getTileImageUrl());
			   } else {
			    packageModel.setTileImageUrl("");
			   }
			   //packageModel.setTileImg("");
			   packageModel.setPkBus("");
			   packageModel.setPkFerry("");
			   packageModel.setPkFlight("");
			   packageModel.setPkFood("");
			   packageModel.setPkHotel("");
			   packageModel.setPkTrain("");
			   packageModel.setPkType(pack.getPkType());
			   packageModel.setPkId(pack.getPkId());
			   packageModel.setDayAndNight(Integer.toString(pack.getPkNights())
			     + " Nights/ " + Integer.toString(pack.getPkDays())+ " Days");
			   PackageCost packagecosts = packageCostService.getminimumPrice(pack.getPkId());
			   if (packagecosts != null) {
			    packageModel.setPackagePrice(packagecosts.getPkcCost());
			    if(packagecosts.getOfferPrice() != null){
			     packageModel.setOfferPrice(packagecosts.getOfferPrice());
			    }
			   }
			   packageModel.setSpecial(pack.getPkSpecial());
			   if (pack.getPkOrder() != null) {
			    packageModel.setOrder(pack.getPkOrder());
			   }
			   if (pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()) {
			    packageModel.setImageUrl(pack.getImageUrl());
			   } else {
			    packageModel.setImageUrl("");
			   }
			   packageModels.add(packageModel);
			  }
			  return packageModels;
			 }
}
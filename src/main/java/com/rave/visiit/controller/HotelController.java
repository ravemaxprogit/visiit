package com.rave.visiit.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.Country;
import com.rave.visiit.entity.Hotel;
import com.rave.visiit.entity.HotelContact;
import com.rave.visiit.entity.HotelKey;
import com.rave.visiit.entity.Images;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageHotel;
import com.rave.visiit.model.HotelModel;
import com.rave.visiit.service.CountryService;
import com.rave.visiit.service.HotelContactService;
import com.rave.visiit.service.HotelService;
import com.rave.visiit.service.PackageHotelService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.Helper;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class HotelController {

	private static Logger log = LogManager.getLogger(HotelController.class);

	public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	@Autowired(required = true)
	@Qualifier(value = "hotelService")
	private HotelService hotelService;

	@Autowired(required = true)
	@Qualifier(value = "hotelContactService")
	private HotelContactService hotelContactService;

	@Autowired(required = true)
	@Qualifier(value = "countryService")
	private CountryService countryService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageService")
	private PackageService packageService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageHotelService")
	private PackageHotelService packageHotelService;

	@RequestMapping("/hotelMaster")
	public String getHotel(Model model) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		List<Country> cntryList = new ArrayList<Country>();
		if (getHotelService() != null) {
			try {
				hotelList = getHotelService().getAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (getCountryService() != null) {
			try {
				cntryList = getCountryService().getAllCountry();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("hotelList", hotelList);
		model.addAttribute("cntryList", cntryList);
		return "HotelCreation";
	}

	@RequestMapping(value = {"/secure/getAllHotels","/getAllHotels"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllHotels() {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		ModelMap model = new ModelMap();
		try {
			hotelList = getHotelService().getAll();
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			if (hotelList != null && !hotelList.isEmpty()) {
				model.put("hotelList", hotelList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getHotel","/getHotel"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllHotel(
			@RequestParam("hotelId") String hotelIdStr) {
		ModelMap model = new ModelMap();
		List<Images> imgList = null;
		try {
			if (hotelIdStr == null || hotelIdStr.isEmpty()) {
				throw new VisiitRunTimeException("Please Provide hotel to edit");
			}
			Integer hotelId = Integer.parseInt(hotelIdStr);
			Hotel hotel = getHotelService().get(hotelId);
			if (hotel == null) {throw new VisiitRunTimeException(ErrorConstants.HOTEL_INFORMATION_NOT_FOUND);}
			List<HotelContact> hotelContact = getHotelService().getHotelContact(hotelId);
			if(hotelContact != null && hotelContact.size() >0){hotel.setHotelContacts(hotelContact.get(0));}
			imgList = Helper.getImgAsList("Hotel", Integer.toString(hotel.getHdSeqId()));
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("hotel", hotel);
			if(imgList != null && imgList.size() > 0){
				model.put("hotelImg", imgList);
			}
		} catch (Exception e) {
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value ={"/secure/deleteHotel","/deleteHotel"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteHotel(
			@RequestParam("hotelId") String hotelIdStr) {
		ModelMap model = new ModelMap();
		try {
			if (hotelIdStr == null || hotelIdStr.isEmpty()) {
				throw new VisiitRunTimeException("Please Provide hotel to delete");
			}
			Integer hotelId = Integer.parseInt(hotelIdStr);
			boolean isValidToDelete = hotelService.validateVendorRefference(hotelId);
			if(isValidToDelete){throw new VisiitRunTimeException(ErrorConstants.HOTEL_REFERRING_BY_VENDOR_OR_HOTE_PACKAGE);}
			Hotel hotel = getHotelService().get(hotelId);
			if(hotel == null ){throw new VisiitRunTimeException(ErrorConstants.HOTEL_INFORMATION_NOT_FOUND);} 
			hotel.setIsDeleted(Boolean.TRUE);
			getHotelService().saveorupdate(hotel);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("message", "Hotel Information Successfully Deleted..");
		} catch (Exception e) {
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/saveHotel","/saveHotel"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap savehotel(
			@RequestParam("hotelStr") String hotelStr,
			@RequestParam("hotelContStr") String hotelContStr) {
		ModelMap model = new ModelMap();
		String code = null;
		boolean hotelCodeAvail = false;
		try {
			hotelStr = HtmlUtils.htmlUnescape(hotelStr);
			hotelContStr = HtmlUtils.htmlUnescape(hotelContStr);
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Hotel> ref = new TypeReference<Hotel>() {};
			TypeReference<HotelContact> hcRef = new TypeReference<HotelContact>() {};
			Hotel hotel = mapper.readValue(hotelStr, ref);
			validateHotelInformation(hotel);
			HotelContact contact = mapper.readValue(hotelContStr, hcRef);
			validateHotelContactInformation(contact);
			if(hotel.getHdSeqId() != null){
				String imageUrl = getHotelService().getImageUrlById(hotel.getHdSeqId());
				if(imageUrl != null && !imageUrl.isEmpty()){
					hotel.setImageUrl(imageUrl);
				}
			}
			hotel.setHdModifiedOn(new Timestamp(System.currentTimeMillis()));
			hotel.setHdIsactive(Boolean.TRUE);
			hotel.setHdModifiedBy(Constants.ADMIN);
			hotel.setHdModifiedOn(new Timestamp(System.currentTimeMillis()));
			if(hotel.getHdCode() == null || hotel.getHdCode().isEmpty()){
				code = hotelService.generateHotelCode();
				hotel.setHdCode(code);
				hotelCodeAvail = true;
			}
			getHotelService().saveorupdate(hotel);
			contact.setHcIsactive("Y");
			contact.setHotelDetail(hotel.getHdSeqId());
			contact.setHcModifiedBy(Constants.ADMIN);
			contact.setHcModifiedOn(new Timestamp(System.currentTimeMillis()));
			getHotelContactService().saveorupdate(contact);
			if(hotelCodeAvail){
				HotelKey hotelKey = new HotelKey();
				hotelKey.setKey(hotel.getHdSeqId());
				getHotelService().saveorupdateHotelKey(hotelKey);
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("message", "hotel successfully saved");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving hotel master....\n\n"
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}

	private void validateHotelInformation(Hotel hotel) throws Exception {
		if (hotel == null) {
			throw new VisiitRunTimeException(ErrorConstants.PROVIDE_HOTEL_INFORMATION);
		}
		if (hotel.getHdName() == null || hotel.getHdName().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.NAME_REQUIRED);
		}
		if (hotel.getHdType() == null || hotel.getHdType().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.HOTEL_TYPE_REQUIRED);
		}
		if (hotel.getHdPostel() == null || hotel.getHdPostel().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.POSTAL_CODE_REQUIRED);
		}
		if (hotel.getHdPhone() == null || hotel.getHdPhone().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.Phone_REQUIRED);
		}
		if (hotel.getHdEmail() == null || hotel.getHdEmail().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.EMAIL_REQUIRED);
		}
		if (hotel.getHdAddress() == null || hotel.getHdAddress().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.ADDRESS_REQUIRED);
		}
		if (hotel.getHdCity() == null || hotel.getHdCity().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.CITY_REQUIRED);
		}
		if (hotel.getHdCountry() == null || hotel.getHdCountry().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.COUNTRY_REQUIRED);
		}
		if (hotel.getHdState() == null || hotel.getHdState().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.STATE_REQUIRED);
		}
	}

	private void validateHotelContactInformation(HotelContact hotelContact)
			throws Exception {
		if (hotelContact == null) {
			throw new VisiitRunTimeException(ErrorConstants.PROVIDE_CONTACT_INFORMATION);
		}
		if (hotelContact.getHcContactName1() == null
				|| hotelContact.getHcContactName1().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.CONTACT_NAME_REQUIRED);
		}
		if (hotelContact.getHcEmail1() == null
				|| hotelContact.getHcEmail1().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.CONTACT_EMAIL_REQUIRED);
		}
		if (hotelContact.getHcPhone1() == null
				|| hotelContact.getHcPhone1().isEmpty()) {
			throw new VisiitRunTimeException(ErrorConstants.CONTACT_PHONE_REQUIRED);
		}
	}
	
	@RequestMapping(value = {"/secure/getAllPackageHotel","/getAllPackageHotel"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllHotelsByPkId(@RequestParam("pkId") String pkId) {
		List<PackageHotel> hotelList = new ArrayList<PackageHotel>();
		ModelMap model = new ModelMap();
		try {
			hotelList = getHotelService().getAllHotelByPKId(Integer.parseInt(pkId));
			
			if (hotelList != null && !hotelList.isEmpty()) {
				List<HotelModel> hotels = populatePackageHotel(hotelList);
				if(hotels == null || hotels.isEmpty()){throw new VisiitRunTimeException(ErrorConstants.PACKAGE_HOTEL_INFORMATION_NOT_FOUND);}
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put("hotelList", hotels);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	private List<HotelModel> populatePackageHotel(List<PackageHotel> hotelList) throws NumberFormatException, Exception{
		List<HotelModel> hotelModels = new ArrayList<HotelModel>();
		for (PackageHotel packageHotel : hotelList) {
			HotelModel hotelModel = new HotelModel();
			hotelModel.setPhId(packageHotel.getPkhId());
			hotelModel.setCity(packageHotel.getCity());
			hotelModel.setStatus(packageHotel.getStatus());
			hotelModel.setHdName(packageHotel.getHotel().getHdName());
			hotelModel.setHdType(packageHotel.getHotel().getHdType());
			hotelModel.setCityName(countryService.getCity(Integer.parseInt(packageHotel.getCity())).getCityName());
			hotelModel.setHdId(packageHotel.getHotel().getHdSeqId());
			hotelModel.setImageUrl(packageHotel.getImageUrl());
			hotelModels.add(hotelModel);
		}
		return hotelModels;
	}

	@RequestMapping(value = {"/secure/savePackageHotel","/savePackageHotel"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap savePackageHotel(@RequestParam("hotel") String hotelStr,@RequestParam("pkId") String pkId,@RequestParam("hdId") String hdId) {
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			TypeReference<PackageHotel> refPack = new TypeReference<PackageHotel>() {};
			PackageHotel packageHotel = mapper.readValue(hotelStr, refPack);
			Hotel hotel = getHotelService().get(Integer.parseInt(hdId));
			packageHotel.setHotel(hotel);
			Package pack = this.packageService.get(Integer.parseInt(pkId));
			packageHotel.setPkhModifiedOn(new Timestamp(System.currentTimeMillis()));
			packageHotel.setPkhIsActive(Boolean.TRUE);
			packageHotel.setPkhModifiedBy(Constants.ADMIN);
			packageHotel.setPack(pack);
			if(packageHotel.getPkhId() != null){
				PackageHotel packageHotelOld = packageHotelService.get(packageHotel.getPkhId());
				if(packageHotelOld != null){
					if(packageHotelOld.getImageUrl() != null && !packageHotelOld.getImageUrl().isEmpty()){
						packageHotel.setImageUrl(packageHotelOld.getImageUrl());
					}
				}
			}

			this.getPackageHotelService().saveorupdate(packageHotel);
			//String fileName = Helper.saveImgFile("file", "Hotel",Integer.toString(packageHotel.getPkhId()));
			//String url = File.separator + "Hotel" + File.separator + Integer.toString(packageHotel.getPkhId()) + File.separator + fileName;
			//this.getPackageHotelService().saveorupdate(packageHotel);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("message", "Package Hotel Saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	
	public HotelService getHotelService() {
		return hotelService;
	}

	public void setHotelService(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	public HotelContactService getHotelContactService() {
		return hotelContactService;
	}

	public void setHotelContactService(HotelContactService hotelContactService) {
		this.hotelContactService = hotelContactService;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
	
	
	public PackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}
	
	
	public PackageHotelService getPackageHotelService() {
		return packageHotelService;
	}

	public void setPackageHotelService(PackageHotelService packageHotelService) {
		this.packageHotelService = packageHotelService;
	}

	@RequestMapping(value = {"/secure/getPackageHotel","/getPackageHotel"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getHotelByCity(
			@RequestParam("hotelId") String hotelIdStr) {
		ModelMap model = new ModelMap();
		List<Images> imgList = null;
		try {
			if (hotelIdStr == null || hotelIdStr.isEmpty()) {
				throw new VisiitRunTimeException("Please Provide hotel to edit");
			}
			Integer hotelId = Integer.parseInt(hotelIdStr);
			Hotel hotel = getHotelService().get(hotelId);
			if (hotel == null) {throw new VisiitRunTimeException(ErrorConstants.HOTEL_INFORMATION_NOT_FOUND);}
			List<HotelContact> hotelContact = getHotelService().getHotelContact(hotelId);
			if(hotelContact != null && hotelContact.size() >0){hotel.setHotelContacts(hotelContact.get(0));}
			imgList = Helper.getImgAsList("Hotel", Integer.toString(hotel.getHdSeqId()));
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("hotel", hotel);
			if(imgList != null && imgList.size() > 0){
				model.put("hotelImg", imgList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getHotelsByCity","/getHotelsByCity"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getHotelsByCity(
			@RequestParam("cityId") String cityIdStr) {
		ModelMap model = new ModelMap();
		try {
			if (cityIdStr == null || cityIdStr.isEmpty()) {
				throw new VisiitRunTimeException(ErrorConstants.PROVIDE_CITY);
			}
			List<Hotel> hotel = getHotelService().getHotelByCity(cityIdStr);
			if (hotel == null || hotel.isEmpty()) {throw new VisiitRunTimeException(ErrorConstants.HOTEL_INFORMATION_NOT_FOUND);}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("hotel", hotel);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deletePackageHotel","/deletePackageHotel"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deletePackageHotel(
			@RequestParam("hotelId") String hotelIdStr) {
		ModelMap model = new ModelMap();
		try {
			if (hotelIdStr == null || hotelIdStr.isEmpty()) {
				throw new VisiitRunTimeException("Please Provide hotel to delete");
			}
			Integer hotelId = Integer.parseInt(hotelIdStr);
			PackageHotel packageHotel = getPackageHotelService().get(hotelId);
			if(packageHotel == null){throw new VisiitRunTimeException(ErrorConstants.PACKAGE_HOTEL_INFORMATION_NOT_FOUND);}
			packageHotel.setIsDeleted(Boolean.TRUE);
			getPackageHotelService().saveorupdate(packageHotel);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR, "Hotel Information Successfully Deleted..");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting hotels : " + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
}

package com.rave.visiit.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

import com.rave.visiit.entity.Vendor;
import com.rave.visiit.entity.VendorKey;
import com.rave.visiit.service.VendorService;
import com.rave.visiit.service.VendorServiceImpl;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class VendorController {
	
private  static Logger log= LogManager.getLogger(VendorController.class);
	
	@Autowired(required=true)
	@Qualifier(value="vendorService")
    public VendorService vendorService = new VendorServiceImpl();
    
	
	
	public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	@RequestMapping("/vendor")
	public ModelAndView getVendor(){
		List<Vendor> vendorList = new ArrayList<Vendor>();
		ModelMap model = new ModelMap();
		System.out.println("inside of getVendor ::- "+getVendorService()); 
		try{
			if(getVendorService()!=null){
				System.out.println("inside of getVendorService checking..."); 
				vendorList = getVendorService().list();
				if(vendorList != null && vendorList.size() > 0){
					model.put(Constants.STATUS_STR, Constants.OK_STR);
					model.put("vendorList", vendorList);
				}else{
					throw new VisiitRunTimeException(ErrorConstants.VENDOR_NOT_FOUND);
				}
			}
			System.out.println("vendor list ::- "+vendorList.size()); 
		}catch(Exception e){
			e.printStackTrace();
			log.info("Error Occur while getting vendor" + e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return new ModelAndView("VendorCreation","vendorList",vendorList);
	}	
	
	@RequestMapping(value = {"/secure/saveVendor","/saveVendor"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap addVendorInfo(@RequestBody  Vendor vendor){
		log.info("inside of addVendorInfo ::- "+getVendorService()); 
		ModelMap model = new ModelMap();
		boolean viCodeAvail = false;
		try{
			if(getVendorService()!=null){
					validateVendorInformation(vendor);
					vendor.setViModifiedBy(Constants.ADMIN);
					vendor.setViRating(5);
					vendor.setViModifiedOn(new Timestamp(System.currentTimeMillis()));
					vendor.setViRelationship("relation");
					if(vendor.getViCode() == null || vendor.getViCode().isEmpty()){
						vendor.setViCode(getVendorService().generateVendorCode());
						viCodeAvail = true;
					}
					getVendorService().save(vendor);
					if(viCodeAvail){
						VendorKey vendorKey=new VendorKey();
						vendorKey.setKey(vendor.getViSeqId());
						getVendorService().saveVendorKey(vendorKey);
					}
					model.put(Constants.STATUS_STR, Constants.OK_STR);
					model.put(Constants.MESSAGE_STR, Constants.VENDOR_SAVED_SUCCESSFULLY);
			  }
			}catch (Exception e) {
				e.printStackTrace();
				log.info("Error occur while saving vendor information " + e.getMessage());
				model.put(Constants.STATUS_STR, Constants.ERROR_STR);
				if(e instanceof VisiitRunTimeException){
					model.put(Constants.MESSAGE_STR, e.getMessage());
				}else {
					model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
				}
			}
		return model;
	}

	private void validateVendorInformation(Vendor vendor) throws Exception {
		log.info("inside of validateVendorInformation ::- "); 
		if(vendor != null ){
			if(vendor.getViVendorName() == null || vendor.getViVendorName().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.VENDOR_NAME_REQUIRED);
			}
			if(vendor.getViVendorPhone() == null || vendor.getViVendorPhone().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.VENDOR_PHONE_REQUIRED);
			}
			if(vendor.getViVendorEmail() == null || vendor.getViVendorEmail().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.VENDOR_EMAIL_REQUIRED);
			}
			if(vendor.getViContactName1() == null || vendor.getViContactName1().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.CONTACT_NAME_REQUIRED);
			}
			if(vendor.getViContactName2() == null || vendor.getViContactName2().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.CONTACT_NAME_REQUIRED);
			}
			if(vendor.getViContactEmail1() == null || vendor.getViContactEmail1().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.CONTACT_EMAIL_REQUIRED);
			}
			if(vendor.getViContactEmail2() == null || vendor.getViContactEmail2().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.CONTACT_EMAIL_REQUIRED);
			}
			if(vendor.getViContactNo1() == null || vendor.getViContactNo1().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.CONTACT_PHONE_REQUIRED);
			}
			if(vendor.getViContactNo2() == null || vendor.getViContactNo2().isEmpty()){
				throw new VisiitRunTimeException(ErrorConstants.CONTACT_PHONE_REQUIRED);
			}
			if(vendor.getViIsactive() == null){
				throw new VisiitRunTimeException("Active Required");
			}
		} else{
			throw new VisiitRunTimeException(ErrorConstants.PROVIDE_VENDOR_INFORMATION);
		}
	}
	
	@RequestMapping(value = {"/secure/getVendor","/getVendor"}, method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody ModelMap getVendorInJSON() {
		ModelMap model = new ModelMap();
		List<Vendor> vendor = null;
		try {
			vendor = this.vendorService.list();
			if(vendor != null && !vendor.isEmpty()){
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, vendor);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting vendor" +e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getVendorDetails","/getVendorDetails"}, method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody ModelMap getVendorById(@RequestParam("vendorId") String vendorId) {
		ModelMap model = new ModelMap();
		Vendor vendor = null;
		try {
			if(vendorId == null || vendorId.isEmpty()){throw new Exception("Please select Vendor");}
			vendor = this.vendorService.get(Integer.parseInt(vendorId));
			if(vendor == null){throw new VisiitRunTimeException("Vendor Information Not Found");}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("vendor", vendor);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting vendor" +e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deleteVendor","/deleteVendor"}, method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody ModelMap deleteVendor(@RequestParam("vendorId") String vendorId) {
		ModelMap model = new ModelMap();
		try {
			if(vendorId == null || vendorId.isEmpty()){throw new VisiitRunTimeException("Please select Vendor");}
			boolean isValidToDelete = vendorService.validateVendorRefference(Integer.parseInt(vendorId));
			if(!isValidToDelete){
			Vendor vendor = this.vendorService.get(Integer.parseInt(vendorId));
				if(vendor != null){
					vendor.setIsDeleted(Boolean.TRUE);
					this.vendorService.save(vendor);
					model.put(Constants.STATUS_STR, Constants.OK_STR);
					model.put(Constants.MESSAGE_STR, Constants.VENDOR_DELETED_SUCCESSFULLY);
				}else {throw new VisiitRunTimeException(ErrorConstants.VENDOR_NOT_FOUND);}
			}else{throw new Exception(ErrorConstants.VENDOR_REFERRING_BY_VENDOR_OR_HOTE_PACKAGE);}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting vendor" +e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	public VendorService getVendorService() {
		return vendorService;
	}

	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}	

}
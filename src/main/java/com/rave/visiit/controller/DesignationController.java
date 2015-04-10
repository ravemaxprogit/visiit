package com.rave.visiit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.Designation;
import com.rave.visiit.service.DesignationService;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;

@Controller
public class DesignationController {

		
	private  static Logger log = LogManager.getLogger(DesignationController.class);

	@Autowired(required=true)
	@Qualifier(value="designationService")
	private DesignationService designationService;
		
	@RequestMapping(value = {"/secure/designationList","/designationList"},  method = RequestMethod.GET)
	public @ResponseBody ModelMap getDesignations(){
		ModelMap model = new ModelMap();
		List<Designation> designations = new ArrayList<Designation>();
		try{
			designations = designationService.listDesignation();
			model.put("Status", Constants.STATUS_Ok);
			model.put("designationList", designations);
		}catch(Exception e){
			e.printStackTrace();
			log.info("Error Occur while getting vendor" + e.getMessage());
			model.put("Status", Constants.STATUS_ERR);
			model.put("message", e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value ={ "/secure/saveDesignation","/saveDesignation"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveDesignation(@RequestParam("dsgStr") String dsgStr) {
		ModelMap model = new ModelMap();
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Designation> ref = new TypeReference<Designation>() {};
			Designation designation = mapper.readValue(dsgStr, ref);
			if(designation == null){
				throw new Exception(ErrorConstants.DESG_PARAMETER_REQUIRED);
			}
			if(StringUtils.isBlank(designation.getDsgName())){
				throw new Exception(ErrorConstants.DESG_NAME_REQUIRED);
			}
			designationService.saveDesignation(designation);
			
			model.put("Status", Constants.STATUS_Ok);
			model.put("message", Constants.SAVED_SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving designation.....\n\n"+e.getMessage());
			model.put("Status", "Error");
			model.put("message", e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getDesignation","/getDesignation"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getDesignation(@RequestParam("designationId") String designationId) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(designationId)){throw new Exception(ErrorConstants.DESG_SELECT_DESIGNATION);}
			Integer desigId = Integer.parseInt(designationId);
		    Designation designation = designationService.getDesignationById(desigId);
			model.put("Status", Constants.STATUS_Ok);
			model.put("designation", designation);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("Status", Constants.STATUS_ERR);
			model.put("message", e.getMessage());
			log.error("Error Occured while displaying Designation details.....\n\n"+e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/deleteDesignation","/deleteDesignation"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteDesignation(@RequestParam("designationId") String designationId) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(designationId)){throw new Exception(ErrorConstants.DESG_SELECT_DESIGNATION);}
			designationService.deleteDesignation(Integer.parseInt(designationId));
			model.put("Status", Constants.STATUS_Ok);
			model.put("message", Constants.DELETED_SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting category....\n\n "+ e.getMessage());
			model.put("Status", Constants.STATUS_ERR);
			model.put("message", e.getMessage());
		}
		return model;
	}

	public DesignationService getDesignationService() {
		return designationService;
	}

	public void setDesignationService(DesignationService designationService) {
		this.designationService = designationService;
	}

}

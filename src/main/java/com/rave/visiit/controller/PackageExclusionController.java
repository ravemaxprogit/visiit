package com.rave.visiit.controller;

import java.sql.Timestamp;
import java.util.List;

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
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.service.PackageExclusionService;
import com.rave.visiit.util.Constants;

@Controller
public class PackageExclusionController {

	private static Logger logger = LogManager
			.getLogger(PackageExclusionController.class);

	@Autowired(required = true)
	@Qualifier(value = "packageExclusionService")
	PackageExclusionService packageExclusionService;

	@RequestMapping(value = {"/secure/saveExclusion","/saveExclusion"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveExclution(
			@RequestParam("packagesaveExclusionStr") String packagesaveExclusionStr,
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<PackageExclusion> refPackIt = new TypeReference<PackageExclusion>() {
			};
			PackageExclusion packageExclusion = mapper.readValue(
					packagesaveExclusionStr, refPackIt);

			TypeReference<Package> refPack = new TypeReference<Package>() {
			};
			Package pack = mapper.readValue(packStr, refPack);

			packageExclusion.setPack(pack);
			if(packageExclusion.getPkexSeqId() != null && packageExclusion.getPkexSeqId() != 0){
				PackageExclusion packageExclusionOld = packageExclusionService.get(packageExclusion.getPkexSeqId());
				if(packageExclusionOld != null){
					if(packageExclusionOld.getImageUrl() != null && !packageExclusionOld.getImageUrl().isEmpty()){
						packageExclusion.setImageUrl(packageExclusionOld.getImageUrl());
					}
				}
			}
			packageExclusion.setPkexModifiedBy(Constants.ADMIN);
			packageExclusion.setPkexStatus(Boolean.TRUE);
			packageExclusion.setPkexActive(Boolean.TRUE);
			packageExclusion.setPkexModifiedOn(new Timestamp(System.currentTimeMillis()));
			packageExclusionService.saveorupdate(packageExclusion);
			
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(Constants.MESSAGE_STR, "Package Exclusion successfully saved");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Package Exclusion....\n\n "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = {"/secure/deleteExclusion","/deleteExclusion"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteExclusion(
			@RequestParam("exclusionStr") String packagesaveExclusionStr) {
		ModelMap model = new ModelMap();
		try {
			if (packagesaveExclusionStr != null
					&& !packagesaveExclusionStr.isEmpty()) {
				PackageExclusion packageExclusion = packageExclusionService
						.get(Integer.parseInt(packagesaveExclusionStr));
				packageExclusion.setPkexStatus(Boolean.FALSE);
				packageExclusion.setIsDeleted(Boolean.TRUE);
				packageExclusionService.saveorupdate(packageExclusion);
				model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
				model.put(Constants.MESSAGE_STR, "Package Exclusion successfully Removed");
			} else {
				model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
				model.put(Constants.MESSAGE_STR, "Please Select Exclusion to delete");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while removing Package Exclusion....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getExclusionByPackage","/getExclusionByPackage"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getExclusionByPackage(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageExclusion> packageExclusion = null;
		try {
			packageExclusion = packageExclusionService.getExclusionByPackage(Integer.parseInt(packStr));
			if (packageExclusion == null || packageExclusion.isEmpty()) {throw new Exception("Package Exclusion Not Found");}
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("packageExclution", packageExclusion);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error occur while getting Exclusion List "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}
	
	
}

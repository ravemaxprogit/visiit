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
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.service.PackageInclusionService;
import com.rave.visiit.util.Constants;

@Controller
public class PackageInclusionController {

	private static Logger logger = LogManager
			.getLogger(PackageInclusionController.class);

	@Autowired(required = true)
	@Qualifier(value = "packageInclusionService")
	PackageInclusionService packageInclusionService;

	@RequestMapping(value = {"/secure/saveInclusion","/saveInclusion"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveInclusion(
			@RequestParam("packageInclusionStr") String packageInclusionStr,
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<PackageInclusion> refPackIt = new TypeReference<PackageInclusion>() {
			};
			PackageInclusion packageInclusion = mapper.readValue(
					packageInclusionStr, refPackIt);

			TypeReference<Package> refPack = new TypeReference<Package>() {
			};
			Package pack = mapper.readValue(packStr, refPack);

			packageInclusion.setPack(pack);
			if(packageInclusion.getPkinSeqId() != null && packageInclusion.getPkinSeqId() != 0){
				PackageInclusion packageInclusionOld = packageInclusionService.get(packageInclusion.getPkinSeqId());
				if(packageInclusionOld != null){
					if(packageInclusionOld.getImageUrl() != null && !packageInclusionOld.getImageUrl().isEmpty()){
						packageInclusion.setImageUrl(packageInclusionOld.getImageUrl());
					}
				}
			}
			packageInclusion.setPkinModifiedBy(Constants.ADMIN);
			packageInclusion.setPkinStatus(Boolean.TRUE);
			packageInclusion.setPkinActive(Boolean.TRUE);
			packageInclusion.setPkinModifiedOn(new Timestamp(System.currentTimeMillis()));
			packageInclusionService.saveorupdate(packageInclusion);
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put(Constants.MESSAGE_STR, "Package Inclusion successfully saved");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while saving Package Inclusion....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = {"/secure/deleteInclusion","/deleteInclusion"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteInclusive(
			@RequestParam("inclusionStr") String inclusionStr) {
		ModelMap model = new ModelMap();
		try {
			if (inclusionStr != null && !inclusionStr.isEmpty()) {
				PackageInclusion packageInclusion = packageInclusionService
						.get(Integer.parseInt(inclusionStr));
				if(packageInclusion == null){throw new Exception("Package Inclusion Not Found");}
				packageInclusion.setPkinActive(Boolean.FALSE);
				packageInclusion.setIsDeleted(Boolean.TRUE);
				packageInclusionService.saveorupdate(packageInclusion);
				model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
				model.put(Constants.MESSAGE_STR, "Package Inclusion successfully Removed");
			} else {
				model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
				model.put(Constants.MESSAGE_STR, "Please Package Inclusion to delete");
			}
			// packageCostService.delete(Integer.parseInt(priceStr));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured while delete Package Inclusion....\n\n "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getInclusionByPackage","/getInclusionByPackage"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getInclusionByPackage(
			@RequestParam("packStr") String packStr) {
		ModelMap model = new ModelMap();
		List<PackageInclusion> packageInclusion = null;
		try {
			packageInclusion = packageInclusionService
					.getInclusionByPackage(Integer.parseInt(packStr));
			if (packageInclusion == null || packageInclusion.isEmpty()) {
				throw new Exception("Package Inclusion Not Found");
			}
			model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
			model.put("packageInclusion", packageInclusion);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error occur while getting Inclusion List "
					+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.STATUS_ERR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	
}

package com.rave.visiit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rave.visiit.service.CustomerEnquiryService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.service.TravellerDetailService;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class DashboardController {
	
	@Autowired(required = true)
	@Qualifier(value = "travellerDetailService")
	TravellerDetailService travellerDetailService;

	@Autowired(required = true)
	@Qualifier(value = "packageService")
	PackageService packageService;

	@Autowired(required = true)
	@Qualifier(value = "customerEnquiryService")
	private CustomerEnquiryService customerEnquiryService;

	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/secure/getStatistics", "/getStatistics" }, method = RequestMethod.GET)
	public @ResponseBody ModelMap getStatistics() {
		ModelMap model = new ModelMap();
		try {

			Integer packageBooked = travellerDetailService.getNoPackageBooked();
			Integer tripCompleted = travellerDetailService.getNoTripCompleted();
			Integer quiryRequest = customerEnquiryService.getNoQuiryRequest();
			Integer quiryReply = customerEnquiryService.getNoQuiryReply();
			Integer validPack = packageService.getAllPackages().size();
//			List<Package> totalPack = packageService.getAll();
			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put("packageBooked", packageBooked);
			model.put("tripCompleted", tripCompleted);
			model.put("quiryRequest", quiryRequest);
			model.put("quiryReply", quiryReply);
			try{
				Map<String, Integer> totalPack = packageService.getPackCount();
				model.addAllAttributes(totalPack);
				System.out.println(totalPack.toString());
			}catch(Exception e){
				System.out.println("Exception on pack count: "+e);
			}
		} catch (Exception e) {
			if (e instanceof VisiitRunTimeException) {
				model.put(ConstantUtil.MESSAGE, e.getMessage());
			} else {
				model.put(ConstantUtil.MESSAGE,
						ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);

		}
		return model;

	}

}

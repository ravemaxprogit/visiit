package com.rave.visiit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rave.visiit.entity.Salutation;
import com.rave.visiit.service.MasterService;

@Controller
public class MasterController {

	@Autowired(required = true)
	@Qualifier(value = "masterService")
	MasterService masterService;

	@RequestMapping(value = {"/secure/salutationList","/salutationList"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getSalutation() {
		ModelMap model = new ModelMap();
		List<Salutation> salutation = null;
		try {

			salutation = this.masterService.getAllSalutation();
			model.put("salutation", salutation);
		} catch (Exception e) {
			System.out.println(e);
		}
		return model;
	}

}

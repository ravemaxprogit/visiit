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

import com.rave.visiit.entity.Module;
import com.rave.visiit.service.ModuleService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;

@Controller
public class ModuleController {

		
	private  static Logger log = LogManager.getLogger(ModuleController.class);

	@Autowired(required=true)
	@Qualifier(value="moduleService")
	private ModuleService moduleService;
		
	@RequestMapping(value = {"/secure/moduleList","/moduleList"},  method = RequestMethod.GET)
	public @ResponseBody ModelMap getModules(){
		ModelMap model = new ModelMap();
		List<Module> modules = new ArrayList<Module>();
		try{
			modules = moduleService.listModule();
			model.put("Status", Constants.STATUS_Ok);
			model.put("moduleList", modules);
		}catch(Exception e){
			e.printStackTrace();
			log.info("Error Occur while getting module" + e.getMessage());
			model.put("Status", Constants.STATUS_ERR);
			model.put("message", e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getSubModulesByModuleIds","/getSubModulesByModuleIds"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getSubModulesByModuleIds(@RequestParam("moduleIds") String moduleIds) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(moduleIds)){throw new Exception(ErrorConstants.MOD_SELECT_MODULE);}
			List<Integer> parentids = CommonUtil.covertStringToIntegerList(moduleIds);
			System.out.println("parentids :::: "+parentids);
		    List<Module> module = moduleService.getSubModulesByModuleIds(parentids);
			model.put("Status", Constants.STATUS_Ok);
			model.put("subModuleList", module);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("Status", Constants.STATUS_ERR);
			model.put("message", e.getMessage());
			log.error("Error Occured while displaying Module details.....\n\n"+e.getMessage());
		}
		return model;
	}
	

}

package com.rave.visiit.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.rave.visiit.entity.RoleMaster;
import com.rave.visiit.service.RoleMasterService;
import com.rave.visiit.service.RoleMasterServiceImpl;

@Controller
public class RoleController {

	private static Logger log = LogManager.getLogger(RoleController.class);

	@Autowired(required = true)
	@Qualifier(value = "roleMasterService")
	public RoleMasterService roleMasterService = new RoleMasterServiceImpl();

	@RequestMapping("/roleMaster")
	public ModelAndView getRole() {
		List<RoleMaster> roleList = new ArrayList<RoleMaster>();
		if (getRoleMasterService() != null) {
			try {
				roleList = getRoleMasterService().getAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("UserRoleMaster", "roleList", roleList);
	}

	@RequestMapping(value = {"/secure/editRole","/editRole"}, method = RequestMethod.POST)
	public @ResponseBody RoleMaster editRole(
			@RequestParam("params") String params) {
		RoleMaster role = null;
		try {
			Integer id = Integer.parseInt(params);
			role = getRoleMasterService().get(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return role;
	}

	@RequestMapping(value = {"/secure/saveRole","/saveRole"}, method = RequestMethod.POST)
	public @ResponseBody String saveRole(@RequestParam("roleStr") String roleStr) {
		String resultStr = null;
		try {
			roleStr = HtmlUtils.htmlUnescape(roleStr);
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<RoleMaster> ref = new TypeReference<RoleMaster>() {};
			RoleMaster role = mapper.readValue(roleStr, ref);
			String err = validateRole(role);
			if(err!=null && !err.trim().equals("") ){
				throw new Exception(err.toString());
			}
			role.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			getRoleMasterService().saveorupdate(role);
			resultStr = "Role successfully saved";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultStr = "Error Occured while saving role master....\n\n"+e.getMessage();
		}
		return resultStr;
	}
	
	private String validateRole (RoleMaster role){
		StringBuffer sb = new StringBuffer();
		if(role.getRoleTitle()==null || role.getRoleTitle().equals("")){
			sb.append("* Role Title is mandatory field.\n\n");
		}
		if(role.getRoleTitle()==null || role.getRoleTitle().equals("")){ 
			sb.append("* Role Description is mandatory field.\n\n");
		}
		return sb.toString();
	}

	@RequestMapping(value = {"/secure/deleteRole","/deleteRole"}, method = RequestMethod.POST)
	public @ResponseBody String deleteRole(@RequestParam("params") String params) {
		String resultStr = "";
		try {
			Integer id = Integer.parseInt(params);
			RoleMaster role = getRoleMasterService().get(id);
			getRoleMasterService().delete(role);
			resultStr = "Role successfully deleted.";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return resultStr;
	}

	public RoleMasterService getRoleMasterService() {
		return roleMasterService;
	}

	public void setRoleMasterService(RoleMasterService roleMasterService) {
		this.roleMasterService = roleMasterService;
	}
}
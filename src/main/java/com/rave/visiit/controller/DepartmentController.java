package com.rave.visiit.controller;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.Department;
import com.rave.visiit.service.DepartmentService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;

@Controller
public class DepartmentController {
	
	private static Logger log = LogManager.getLogger(DepartmentController.class);

	@Autowired(required = true)
	@Qualifier(value = "departmentService")
	private DepartmentService departmentService;
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	@Autowired(required=true)
	@Qualifier(value="departmentService")
	public void setDepartmentService(DepartmentService ds){
		this.departmentService = ds;
	}
	
	@RequestMapping(value = "/department", method = RequestMethod.GET)
    public String listDepartment(Model model) {
        model.addAttribute("tid", 1);
        return "AdministrationMaster";
    }

	@RequestMapping(value = {"/secure/saveDepartment","/saveDepartment"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveDepartment(@RequestParam("departmentStr") String departmentStr) {

		ModelMap model = new ModelMap();
		Department department = new Department();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Department> departmentIt = new TypeReference<Department>() {
			};
			department = mapper.readValue(departmentStr, departmentIt);
			
			if(department != null){
				if(department.getDeptName() == null || department.getDeptName().isEmpty()){throw new Exception(ErrorConstants.DEPT_NAME_REQUIRED);}
				int existingDeptCount = departmentService.checkExistingDepartmentName(department.getDeptName(), department.getDeptId());
				if(existingDeptCount>0){
					throw new Exception(ErrorConstants.DEPT_NAME_ALREADY_EXISTS);
				}
				if(null==department.getDeptId()){
					int maxDeptId = departmentService.getMaxDepartmentId();
					String nextDeptCode = CommonUtil.generateNextCode("DEP", maxDeptId);
					department.setDeptCode(nextDeptCode);
				}
				
				if(StringUtils.isBlank(department.getDeptCreatedBy())){
					department.setDeptCreatedBy(Constants.ADMIN);
				}
				if(null==department.getDeptCreatedOn()){
					department.setDeptCreatedOn(new Timestamp(System.currentTimeMillis()));
				}
				department.setDeptModifiedBy(Constants.ADMIN);
				department.setDeptModifiedOn(new Timestamp(System.currentTimeMillis()));
				departmentService.saveDepartment(department);
				
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR, Constants.DEPT_SAVED_SUCCESSFULLY);
			} else {
				throw new Exception(ErrorConstants.PROVIDE_DEPARTMENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving department.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof ConstraintViolationException){
				model.put(Constants.MESSAGE_STR, department.getDeptCode() + " : " +ErrorConstants.THIS_CODE_ALREADY_EXISTS);
			} else {
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getDepartments","/getDepartments"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getDepartments() {

		ModelMap model = new ModelMap();
		List<Department> department = null;
		try {
			department = this.departmentService.listDepartments();
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.DEPARTMENT_STR, department);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while getting departments....."+e.getMessage());
		}

		return model;
	}

	@RequestMapping(value = {"/secure/deleteDepartment","/deleteDepartment"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteDepartment(@RequestParam("departmentId") String departmentId) {

		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(departmentId)){throw new Exception(ErrorConstants.PLEASE_SELECT_DEPARTMENT);}
			if(departmentService.checkFieldRefInAnyWhere(Integer.parseInt(departmentId))){
				throw new Exception("Department not deleted because it refered in somewhere");
			}
			Department department = departmentService.getDepartmentById(Integer.parseInt(departmentId));
			if(department != null){
				if(department.getIsDeleted()){
					throw new Exception(ErrorConstants.DEPT_ALREADY_DELETED);
				}
				department.setDeptName(Constants.DELETE_TOKEN+CommonUtil.getLastForDigitsOfNowTimeStamp()+department.getDeptName());
				department.setIsDeleted(Boolean.TRUE);
				department.setDeptModifiedBy(Constants.ADMIN);
				department.setDeptModifiedOn(new Timestamp(System.currentTimeMillis()));
				departmentService.updateDepartment(department);
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR, Constants.DELETED_SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting category....\n\n "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}

		return model;
	}
	
	@RequestMapping(value = {"/secure/getDepartmentById","/getDepartmentById"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getDepartmentById(@RequestParam("departmentId") String departmentId) {

		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(departmentId)){throw new Exception(ErrorConstants.PLEASE_SELECT_DEPARTMENT);}
			Integer deptId = Integer.parseInt(departmentId);

			Department department = departmentService.getDepartmentById(deptId );
		    if(department == null){
		    	throw new Exception("Department not found for given id "+deptId );
		    }

			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.DEPARTMENT_STR, department);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
			log.error("Error Occured while displaying Department details.....\n\n"+e.getMessage());
		}

		return model;
	}

	
}


package com.rave.visiit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rave.visiit.entity.Department;
import com.rave.visiit.entity.Designation;
import com.rave.visiit.entity.Employees;
import com.rave.visiit.service.DepartmentService;
import com.rave.visiit.service.DesignationService;
import com.rave.visiit.service.EmployeeService;
import com.rave.visiit.util.Helper;

@Controller
public class EmployeeRegistrationController {
	
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	private DesignationService designationService;
	
	@Autowired(required=true)
	@Qualifier(value="employeeService")
	public void setEmployeeService(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@Autowired(required=true)
	@Qualifier(value="departmentService")
	public void setDepartmentService(DepartmentService ds){
		this.departmentService = ds;
	}
	
	@Autowired(required=true)
	@Qualifier(value="designationService")
	public void setDesignationService(DesignationService ds){
		this.designationService = ds;
	}
	
	@RequestMapping(value = "/login1", method = RequestMethod.GET)
    public String listEmployees(Model model) throws Exception {
        model.addAttribute("employee", new Employees());
        model.addAttribute("listEmployees", this.employeeService.listEmployees());
        model.addAttribute("department", new Department());
        model.addAttribute("departmentList", this.departmentService.listDepartments());
        model.addAttribute("designation", new Designation());
        model.addAttribute("designationList", this.designationService.listDesignation());
        return "UserCreation";
    }
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String postLogin(Model model) {
        //model.addAttribute("employee", new Employees());
        //model.addAttribute("listEmployees", this.employeeService.listEmployees());
        //model.addAttribute("department", new Departments());
        //model.addAttribute("departmentList", this.departmentService.listDepartments());
        //model.addAttribute("designation", new Designation());
        //model.addAttribute("designationList", this.designationService.listDesignation());
        return "AdminDashboard";
    }
     
	
	@RequestMapping("/employeeRegistration")
	public String createEmp(HttpServletRequest request){
		Employees employee=new Employees();
		employee.setEmpCode(request.getParameter("empCode"));
		employee.setEmpFirstName(request.getParameter("empFirstName"));
		employee.setEmpLastName(request.getParameter("empLastName"));
		employee.setEmpMail(request.getParameter("empPersonalMail"));
		employee.setEmpPhone(request.getParameter("empPhone"));
		employee.setEmpPassportNo(request.getParameter("empPassportNo"));
		employee.setDepartments(Integer.parseInt(request.getParameter("empDept")));
		employee.setDesignation(Integer.parseInt(request.getParameter("empDsgn")));
		employee.setEmpPfNo(request.getParameter("empPfNO"));
		employee.setEmpDate(Helper.getSqlDate(request.getParameter("empDob")));
		System.out.println(request.getParameter("empDoj")+" ------------------>>>>>>");
		employee.setEmpDoj(Helper.getSqlDate(request.getParameter("empDoj")));
		employee.setEmpPanNo(request.getParameter("empPanNo"));
		employee.setEmpEsiNo(request.getParameter("empEsiNo"));
		employee.setEmpOfficeMail(request.getParameter("empOfficeMail"));
		employee.setEmpExtentionNo("22");
		employee.setEmpStatus("y");
		employee.setEmpUpdatedBy("admin");
		employee.setEmpUpdatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		System.out.println("<-------------- true");
		
		employeeService.addEmployees(employee);
		
		System.out.println("<-------------- true again");
		return "redirect:/login";
	}

}

package com.rave.visiit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerCareController {
	@RequestMapping("/customerCare")
	public ModelAndView getDepartment(){
		return new ModelAndView("CustomerCare","Country","added successfully !");
	}
}

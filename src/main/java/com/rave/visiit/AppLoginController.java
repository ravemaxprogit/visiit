package com.rave.visiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.rave.visiit.service.AppLoginService;

@Controller
public class AppLoginController {
	
	private AppLoginService appLoginService;
	
	@Autowired(required=true)
	@Qualifier(value="appLoginService")
	public void setAppLoginService(AppLoginService appLoginService) {
		this.appLoginService = appLoginService;
	}

}

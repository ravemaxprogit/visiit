package com.rave.visiit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.controller.DepartmentController;
import com.rave.visiit.util.Helper;

@Controller
public class SecurityQuestionController {
	
	private static Logger logger = LogManager.getLogger(DepartmentController.class);
	
	@RequestMapping("/testfrm")
	public String test(){
		return "Test";
	}
	
	@RequestMapping(value = "/getImg", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody String getImg(){
		String resultStr = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
//			resultStr = Helper.base64Img(Helper.getDirectory(), "images.jpg");
			resultStr =mapper.writeValueAsString(resultStr);
			} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(resultStr+" ------------------->");
		return resultStr;

	}
	
	 @RequestMapping(value = "/uploadImg", method = RequestMethod.POST, headers="Accept=application/multipart")
	   public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {  
		
		 String name = file.getName();
		 System.out.println("yes !!!!!! "+name);
		 if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();	 
	                // Create the file on server
	                File serverFile = new File(Helper.getDirectory().getAbsolutePath()
	                        + File.separator + name);
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                logger.info("Server File Location="
	                        + serverFile.getAbsolutePath());
	 
	                return "You successfully uploaded file=" + name;
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name
	                    + " because the file was empty.";
	        }
	 
	  }
	 

}

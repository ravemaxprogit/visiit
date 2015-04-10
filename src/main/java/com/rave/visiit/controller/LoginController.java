package com.rave.visiit.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

import com.rave.visiit.entity.Module;
import com.rave.visiit.entity.User;
import com.rave.visiit.service.EmployeeService;
import com.rave.visiit.service.UserService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantMailMapperUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.MD5;
import com.rave.visiit.util.MailUtil;

@Controller
public class LoginController {

	private static Logger log = Logger.getLogger(LoginController.class);

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired
	private ServletContext servletContext;

	private String status;
	private String statusMessage;

	@RequestMapping("/registration")
	public ModelAndView registration() {
		System.out.println("Enter Registration Page");
		return new ModelAndView("custRegistration");
	}

	//@RequestMapping(value = "/secure/login", method = RequestMethod.POST)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ModelMap login(
			@RequestParam("userName") String a_userName,
			@RequestParam("password") String a_password,
			@RequestParam("skey") String a_skey, HttpServletResponse response) {
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;
		ModelMap model = new ModelMap();

		try {

			User user = userService.getUserByUserId(a_userName);

			String message = getUserValidation(a_password, a_skey, user);
			if (message.equalsIgnoreCase(ConstantUtil.LOGIN_SUCCESS_MESSAGE)) {
				status = ConstantUtil.OK;
				statusMessage = message;

				model.put(ConstantUtil.FIRSTNAME, user.getUserFirstName());
				model.put(ConstantUtil.LASTNAME, user.getUserLastName());

				model.put(Constants.USER_SEQ_ID_STR, user.getUserSeqId());
				model.put(Constants.USER_ID_STR, user.getUserId());
				model.put(Constants.USER_MODULES_STR, user.getUserModules());
				model.put(Constants.USER_SUB_MODULES_STR, user.getUserSubModules());
				String moduleIds = getModuleIds(user.getUserModules());
				String subModuleIds = getModuleIds(user.getUserSubModules());
				String moduleNames = getModuleNames(user.getUserModules());
				String subModulenames = getModuleNames(user.getUserSubModules());
				//String userDetails = CommonUtil.encodeString(user.getUserId() + "##$" + moduleIds + "##$" + subModuleIds);
				String userDetails = CommonUtil.encodeString(user.getUserId());
				//String userDetails = user.getUserId() + "," + moduleIds + "," + subModuleIds;
				//String userDetails = CommonUtil.encodeString(user.getUserId());
				saveCookie(Constants.USER_DETAILS_STR, userDetails, Constants.COOKIE_MAX_TIME, response);
				saveCookie(Constants.USER_MODULE_NAMES_STR, moduleNames, Constants.COOKIE_MAX_TIME, response);
				saveCookie(Constants.USER_SUB_MODULE_NAMES_STR, subModulenames, Constants.COOKIE_MAX_TIME, response);
				saveCookie(Constants.FIRST_NAME, user.getUserFirstName(), Constants.COOKIE_MAX_TIME, response);
			}
			if (message
					.equalsIgnoreCase(ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED)) {
				statusMessage = message;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}

		return model;
	}

	private String getModuleIds(List<Module> modules) {
		String moduleIds="";
		if(modules!=null && modules.size()>0){
			for(Module mod:modules){
				moduleIds=moduleIds+mod.getModId()+",";
			}
			if(moduleIds.length()>0){
				moduleIds=moduleIds.substring(0, moduleIds.length()-1);
			}
		}
		return moduleIds;
	}
	
	private String getModuleNames(List<Module> modules) {
		String moduleNames="";
		if(modules!=null && modules.size()>0){
			for(Module mod:modules){
				moduleNames=moduleNames+mod.getModName()+",";
			}
			if(moduleNames.length()>0){
				moduleNames=moduleNames.substring(0, moduleNames.length()-1);
			}
		}
		return moduleNames;
	}

	private String getUserValidation(String a_password, String a_skey,
			User user) {
		String message = ConstantUtil.LOGIN_ERROR_MESSAGE;
		if (user != null) {
			if (user.getUserIsActive() == true) {
				String userPassword = user.getUserPassword();
				userPassword = userPassword.concat(a_skey);
				MD5 md5 = new MD5();
				String newPassword = md5.md5(userPassword);

				if (newPassword.equals(a_password)) {
					message = ConstantUtil.LOGIN_SUCCESS_MESSAGE;
					Date expirationDate = user.getUserPwdExpDate();
					if(expirationDate.compareTo(new Date()) >= 0) {
						message = ConstantUtil.LOGIN_SUCCESS_MESSAGE;
					} else {
						message = ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED;
					}
				}
			}

		}
		return message;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public @ResponseBody ModelMap forgetPassword(
			@RequestParam("custEmail") String a_custEmail,
			HttpServletRequest request) {
		ModelMap model = new ModelMap();
		statusMessage = ConstantUtil.FORGOT_PASSWORD_ERROR_MESSAGE;
		status = ConstantUtil.ERROR;
		try {
			User user = userService.getUserByUserId(a_custEmail);

			if (user != null) {

				String newPassword = RandomStringUtils.randomAlphanumeric(10);

				MD5 md5 = new MD5();
				String newEncriptPassword = md5.md5(newPassword);
				user.setUserPassword(newEncriptPassword);
				user.setUserPwdExpDate(new Timestamp(new Date().getTime()));
				userService.updateUser(user);

				String firstName = user.getUserFirstName() != null ? user.getUserFirstName() : "";
				String lastName = user.getUserLastName() != null ? user.getUserLastName() : "";
				HashMap<String, String> nameValues = ConstantMailMapperUtil
						.getForgotPasswordMapValue(a_custEmail, firstName,
								lastName, newPassword);
				HashMap<String, String> nameValuesLoginURL = ConstantMailMapperUtil
						.getAdminLoginURLMapValue(nameValues);

				String[] recipitent = { user.getUserMail() };
				String[] bccRecipients = {};
				MailUtil mailUtil = new MailUtil();
				String message = mailUtil.parseFile(
						ConstantUtil.FORGOT_PASSWORD_REQUEST_FILE, nameValues);

				mailUtil.sendMail(recipitent, bccRecipients,
						ConstantUtil.REG_VISIIT_RESET_PASSWORD, message);
				status = ConstantUtil.OK;
				statusMessage = ConstantUtil.FORGOT_PASSWORD_SUCCESS_MESSAGE;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;
	}

	@RequestMapping(value = {"/secure/resetpassword","/resetpassword"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap resetPassword(
			@PathVariable String a_userpassword) {
		ModelMap model = new ModelMap();
		statusMessage = ConstantUtil.RESETPASSWORD_ERROR_MESSAGE;
		status = ConstantUtil.ERROR;

		if (a_userpassword != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodedBytes;
			try {
				decodedBytes = decoder.decodeBuffer(a_userpassword);
				String userpasword = new String(decodedBytes);

				String[] usernamePasword = userpasword
						.split(ConstantUtil.COLON_PUNCTUATION);
				String userName = usernamePasword[0];
				String password = usernamePasword[1];
				User user = userService.getUserByUserId(userName);
				if (user != null) {
					if (password.equals(user.getUserPassword())) {
						model.put(ConstantUtil.USERID, userName);
						status = ConstantUtil.OK;
						statusMessage = ConstantUtil.RESETPASSWORD_SUCCESS_MESSAGE;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		model.put(ConstantUtil.STATUS, status);
		model.put(ConstantUtil.MESSAGE, statusMessage);
		return model;
	}

	@RequestMapping(value = {"/secure/changepassword","/changepassword"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap chagePassword(
			@RequestParam("userName") String a_userName,
			@RequestParam("oldPassword") String a_oldPassword,
			@RequestParam("skey") String a_skey,
			@RequestParam("password") String a_password,
			@RequestParam("confirmPassword") String a_confirmPassword, HttpServletResponse response) {

		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;

		ModelMap model = new ModelMap();
		try {
			if (a_userName != null && a_password != null
					&& a_confirmPassword != null) {

				User user = userService.getUserByUserId(a_userName);
				String message = getUserValidation(a_oldPassword, a_skey, user);
				MD5 mds= new MD5();
				if (message
						.equalsIgnoreCase(ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED)
						|| message
								.equalsIgnoreCase(ConstantUtil.LOGIN_SUCCESS_MESSAGE)) {
				
		
			
					if (a_password.equals(a_confirmPassword)) {
						BASE64Decoder decoder = new BASE64Decoder();
						byte[] decodedBytes;			
						decodedBytes = decoder.decodeBuffer(a_password);						
						String pasword = new String(decodedBytes);
					
					
						if (mds.md5(pasword).equals(user.getUserPassword())) {

							statusMessage = ConstantUtil.EXISTING_PASSWORD_AND_NEW_PASSWORD_SHOULD_NOT_BE_SAME;
						} else {

							user.setUserPassword(mds.md5(pasword));
							Date nextYear = CommonUtil.getExpiredDate();
							 user.setUserPwdExpDate(new Timestamp(nextYear.getTime()));
							userService.updateUser(user);

							
							HashMap<String, String> nameValues = ConstantMailMapperUtil
									.getRegisterationMapValue(a_userName, user.getUserFirstName(),
											user.getUserLastName(),pasword);

							String[] recipitent = { a_userName };
							String[] bccRecipients = {};
							MailUtil mailUtil = new MailUtil();
							String mailmessage = mailUtil.parseFile(
									ConstantUtil.FORGOT_PASSWORD_FILE, nameValues);

							mailUtil.sendMail(recipitent, bccRecipients,
									ConstantUtil.REG_VISIIT_CHANGE_PASSWORD_CHANGE, mailmessage);
							
							
							model.put(ConstantUtil.FIRSTNAME,user.getUserFirstName());
							model.put(ConstantUtil.LASTNAME,user.getUserLastName());
							model.put(Constants.USER_SEQ_ID_STR, user.getUserSeqId());
							model.put(Constants.USER_ID_STR, user.getUserId());
							model.put(Constants.USER_MODULES_STR, user.getUserModules());
							model.put(Constants.USER_SUB_MODULES_STR, user.getUserSubModules());
							String moduleIds = getModuleIds(user.getUserModules());
							String subModuleIds = getModuleIds(user.getUserSubModules());
							String moduleNames = getModuleNames(user.getUserModules());
							String subModulenames = getModuleNames(user.getUserSubModules());
							//String userDetails = CommonUtil.encodeString(user.getUserId() + "##$" + moduleIds + "##$" + subModuleIds);
							String userDetails = CommonUtil.encodeString(user.getUserId() );
							//String userDetails = user.getUserId() + "##$" + moduleIds + "##$" + subModuleIds;
							saveCookie(Constants.USER_DETAILS_STR, userDetails, Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.LOGGED_IN_USER_ID_STR, CommonUtil.encodeString(user.getUserId()), Constants.COOKIE_MAX_TIME, response); //Setting Max cookie time as 30 Mins
							//saveCookie(Constants.USER_MODULE_IDS_STR, CommonUtil.encodeString(moduleIds), Constants.COOKIE_MAX_TIME, response);
							//saveCookie(Constants.USER_SUB_MODULE_IDS_STR, CommonUtil.encodeString(subModuleIds), Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.USER_MODULE_NAMES_STR, moduleNames, Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.USER_SUB_MODULE_NAMES_STR, subModulenames, Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.FIRST_NAME, user.getUserFirstName(), Constants.COOKIE_MAX_TIME, response);
							//model.put("user", user);
							status = ConstantUtil.OK;
							statusMessage = ConstantUtil.PASSWORD_CHANGED_SUCCESSFULLY;
						}
					}
				} else {
					statusMessage = ConstantUtil.CHANGEPASSWORD_ERROR_MESSAGE;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		saveCookie(Constants.USER_DETAILS_STR, "", Constants.COOKIE_RESET_TIME, response);

		return new ModelAndView("login");
	}
	
	private void saveCookie(String cookieName, String value, int maxAge, HttpServletResponse response) {
	    //Cookie cookie = new Cookie(cookieName, value);
		Cookie cookie;
		try {
			cookie = new Cookie(cookieName, URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(maxAge);
		  //  cookie.setPath("/visiit");
		    response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    
  }

}

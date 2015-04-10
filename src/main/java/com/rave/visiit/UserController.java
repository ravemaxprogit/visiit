package com.rave.visiit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.Module;
import com.rave.visiit.entity.User;
import com.rave.visiit.service.UserService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantMailMapperUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.MD5;
import com.rave.visiit.util.MailUtil;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class UserController {

	private static Logger log = LogManager.getLogger(UserController.class);

	private UserService userService;

	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody ModelMap saveUser(@RequestParam("userStr") String userStr) {

		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<User> userDetail = new TypeReference<User>() {
			};
			User newOrUpdatedUserObj = mapper.readValue(userStr, userDetail);
			
			if(newOrUpdatedUserObj == null){
				throw new VisiitRunTimeException(ErrorConstants.PROVIDE_USER);
			}
			if(StringUtils.isBlank(newOrUpdatedUserObj.getUserId())){	throw new VisiitRunTimeException(ErrorConstants.USER_ID_REQUIRED);}
			if(StringUtils.isBlank(newOrUpdatedUserObj.getUserFirstName())){	throw new VisiitRunTimeException(ErrorConstants.USER_FIRSTNAME_REQUIRED);}
			if(StringUtils.isBlank(newOrUpdatedUserObj.getUserLastName())){	throw new VisiitRunTimeException(ErrorConstants.USER_LASTNAME_REQUIRED);}
			if(StringUtils.isBlank(newOrUpdatedUserObj.getUserMail())){	throw new VisiitRunTimeException(ErrorConstants.USER_MAIL_ID_REQUIRED);}
			if(StringUtils.isBlank(newOrUpdatedUserObj.getUserPhone())){	throw new VisiitRunTimeException(ErrorConstants.USER_PHONE_REQUIRED);}
			
			if(null==newOrUpdatedUserObj.getUserSeqId()){
				newOrUpdatedUserObj.setUserCreatedBy(Constants.ADMIN);
				newOrUpdatedUserObj.setUserModifiedBy(Constants.ADMIN);
				newOrUpdatedUserObj.setUserCreatedOn(new Timestamp(System.currentTimeMillis()));
				newOrUpdatedUserObj.setUserModifiedOn(new Timestamp(System.currentTimeMillis()));
				newOrUpdatedUserObj.setUserPwdExpDate(new Timestamp(System.currentTimeMillis()));

				String tempPassword = RandomStringUtils.randomAlphanumeric(10);
				MD5 md5 = new MD5();
				String newEncriptPassword = md5.md5(tempPassword);
				newOrUpdatedUserObj.setUserPassword(newEncriptPassword);

				Long existingUserCount = userService.checkExistingUserId(newOrUpdatedUserObj.getUserId());
				if(existingUserCount>0){
					throw new VisiitRunTimeException(ErrorConstants.USER_ID_ALREADY_EXISTS);
				}
				int maxUserSeqId = userService.getMaxUserSeqId();
				String nextUserCode = CommonUtil.generateNextCode("USR", maxUserSeqId);
				newOrUpdatedUserObj.setUserCode(nextUserCode);
				userService.saveUser(newOrUpdatedUserObj);
				sendMailForUserCreation(newOrUpdatedUserObj, tempPassword);
			}else{
				newOrUpdatedUserObj.setUserModifiedBy(Constants.ADMIN);
				newOrUpdatedUserObj.setUserModifiedOn(new Timestamp(System.currentTimeMillis()));
				User existingUser = userService.getUsersById(newOrUpdatedUserObj.getUserSeqId());
				if(null!=existingUser){
					populateUpdatedValuesToUser(existingUser, newOrUpdatedUserObj);
				}
				userService.updateUser(existingUser);
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR, Constants.USER_SAVED_SUCCESSFULLY);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving user.....\n\n"+e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}

		}

		return model;

	}

	private void sendMailForUserCreation(User newOrUpdatedUserObj, String tempPassword) throws IOException, URISyntaxException {
		
		try {
			HashMap<String, String> nameValues = ConstantMailMapperUtil.getNewUserCreationMapValue(newOrUpdatedUserObj.getUserMail(), newOrUpdatedUserObj.getUserFirstName(), newOrUpdatedUserObj.getUserLastName(), tempPassword, newOrUpdatedUserObj.getUserId());
			nameValues = ConstantMailMapperUtil.getAdminLoginURLMapValue(nameValues);
			String[] recipitent = { newOrUpdatedUserObj.getUserId() };
			String[] bccRecipients = {};
			MailUtil mailUtil = new MailUtil();
			String message = mailUtil.parseFile(ConstantUtil.NEW_USER_CREATION_MAIL_TEMPLATE, nameValues);
			mailUtil.sendMail(recipitent, bccRecipients, Constants.NEW_USER_CREATED + " for " + newOrUpdatedUserObj.getUserFirstName(), message);
			log.info("New User Creation Mail Sent successfully.");

		}catch(Exception e){
			log.error("Error in sending new user creation email for user : " + newOrUpdatedUserObj.getUserMail());
			e.printStackTrace();
		}
	}

	private void populateUpdatedValuesToUser(User existingUser, User updatedUserObj){

		/* For existing User, no need to update these fields : userSeqId, userId, userCode, userPassword, userCreatedBy, userCreatedOn & userPwdExpDate */
		existingUser.setUserId(updatedUserObj.getUserId());
		existingUser.setUserFirstName(updatedUserObj.getUserFirstName());
		existingUser.setUserLastName(updatedUserObj.getUserLastName());
		existingUser.setUserMail(updatedUserObj.getUserMail());
		existingUser.setUserPhone(updatedUserObj.getUserPhone());
		existingUser.setUserOfficeMail(updatedUserObj.getUserOfficeMail());
		existingUser.setUserExtentionNo(updatedUserObj.getUserExtentionNo());
		existingUser.setUserDeptId(updatedUserObj.getUserDeptId());
		existingUser.setUserDsgId(updatedUserObj.getUserDsgId());
		existingUser.setUserDoj(updatedUserObj.getUserDoj());
		existingUser.setUserDob(updatedUserObj.getUserDob());
		existingUser.setUserEsiNo(updatedUserObj.getUserEsiNo());
		existingUser.setUserPfNo(updatedUserObj.getUserPfNo());
		existingUser.setUserPanNo(updatedUserObj.getUserPanNo());
		existingUser.setUserPassportNo(updatedUserObj.getUserPassportNo());
		existingUser.setUserModifiedBy(updatedUserObj.getUserModifiedBy());
		existingUser.setUserModifiedOn(new Timestamp(System.currentTimeMillis()));
		existingUser.setUserModules(updatedUserObj.getUserModules());
		existingUser.setUserSubModules(updatedUserObj.getUserSubModules());
		if(null!=updatedUserObj.getUserIsActive()){
			existingUser.setUserIsActive(updatedUserObj.getUserIsActive());
		}
		if(null!=updatedUserObj.getIsDeleted()){
			existingUser.setIsDeleted(updatedUserObj.getIsDeleted());
		}
	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public @ResponseBody ModelMap getAllUsers() {
		ModelMap model = new ModelMap();
		List<User> users = new ArrayList<User>();
		try {
			users = userService.listUsers();
			model.put("Status", Constants.STATUS_Ok);
			model.put("userList", users);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error Occur while getting vendor" + e.getMessage());
			model.put("Status", Constants.STATUS_ERR);
			model.put("message", e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody ModelMap getUser(@RequestParam("uSeqId") String uSeqId) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(uSeqId)){throw new VisiitRunTimeException(ErrorConstants.USR_SELECT_USER);}
			Integer uid = Integer.parseInt(uSeqId);
		    User user = userService.getUsersById(uid);
			model.put("Status", Constants.STATUS_Ok);
			model.put("User", user);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			log.error("Error Occured while displaying User details.....\n\n"+e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/getUserByUserId", method = RequestMethod.GET)
	public @ResponseBody ModelMap getUserByUserId(@RequestParam("userId") String userId) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(userId)){throw new VisiitRunTimeException(ErrorConstants.USR_SELECT_USER);}
		    User user = userService.getUserByUserId(userId);
			model.put("Status", Constants.STATUS_Ok);
			model.put("User", user);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			log.error("Error Occured while displaying User details.....\n\n"+e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/getUserModules", method = RequestMethod.GET)
	public @ResponseBody ModelMap getUserModules(@RequestParam("uSeqId") String uSeqId) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(uSeqId)){throw new VisiitRunTimeException(ErrorConstants.USR_SELECT_USER);}
			Integer uid = Integer.parseInt(uSeqId);
		    List<Module> modules= userService.getUserModules(uid);
			model.put("Status", Constants.STATUS_Ok);
			model.put("UserModuleList", modules);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			log.error("Error Occured while displaying User details.....\n\n"+e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/getUserSubModules", method = RequestMethod.GET)
	public @ResponseBody ModelMap getUserSubModules(@RequestParam("uSeqId") String uSeqId) {
		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(uSeqId)){throw new VisiitRunTimeException(ErrorConstants.USR_SELECT_USER);}
			Integer uid = Integer.parseInt(uSeqId);
		    List<Module> modules= userService.getUserSubModules(uid);
			model.put("Status", Constants.STATUS_Ok);
			model.put("UserModuleList", modules);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			log.error("Error Occured while displaying User details.....\n\n"+e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteUser(@RequestParam("userSeqId") String userSeqId) {

		ModelMap model = new ModelMap();
		try {
			if(StringUtils.isBlank(userSeqId)){throw new VisiitRunTimeException(ErrorConstants.PROVIDE_USER);}
			User user = userService.getUsersById(Integer.parseInt(userSeqId));
			if(user != null){
				if(user.getIsDeleted()){
					throw new Exception(ErrorConstants.USR_DELETED_ALREADY);
				}
				user.setIsDeleted(Boolean.TRUE);
				user.setUserModifiedBy(Constants.ADMIN);
				user.setUserModifiedOn(new Timestamp(System.currentTimeMillis()));
				user.setUserId(Constants.DELETE_TOKEN + CommonUtil.getLastForDigitsOfNowTimeStamp() + user.getUserId());
				userService.updateUser(user);
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR, "User " + Constants.DELETED_SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting category....\n\n "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}

		return model;
	}
}

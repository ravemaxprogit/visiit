package com.rave.visiit.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import sun.misc.BASE64Decoder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.model.CustomerModel;
import com.rave.visiit.service.CustRegistrationService;
import com.rave.visiit.service.CustRegistrationServiceImpl;
import com.rave.visiit.service.TravellerDetailService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantMailMapperUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.MD5;
import com.rave.visiit.util.MailUtil;
import com.rave.visiit.util.SMSUtil;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
@SessionAttributes(value = { "user", "userJSON" })
public class CustRegistrationController {

	private static Logger log = Logger
			.getLogger(CustRegistrationController.class);
	String status;
	String statusMessage;
	@Autowired(required = true)
	private MailSender mailSender;

	
	@Autowired(required = true)
	@Qualifier(value = "travellerDetailService")
	TravellerDetailService travellerDetailService;
	
	@Autowired(required = true)
	@Qualifier(value = "custRegistrationService")
	private CustRegistrationService custRegistrationService = new CustRegistrationServiceImpl();

	@RequestMapping(value = {"/secure/registerCustomer","/registerCustomer"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveCustomerRegistration(
			@RequestParam("custRegStr") String a_customer) {

		status = ConstantUtil.ERROR;
		ModelMap model = new ModelMap();
		ObjectMapper mapper = new ObjectMapper();
		try {

			TypeReference<CustRegistration> custRegistrationIt = new TypeReference<CustRegistration>() {
			};
			CustRegistration custRegistration = mapper.readValue(a_customer,
					custRegistrationIt);
			validateCustomer(custRegistration);
			CustRegistration customerObj = getCustRegistrationService().get(
					custRegistration.getCustomerEmail());
			boolean emailExist =  customerObj!= null;

			if (!emailExist) {
				custRegistration.setUpdatedDate(new Date());
				custRegistration.setIsActive(true);
				custRegistration.setIsDelete(false);
				custRegistration.setExpirationDate(CommonUtil.getExpiredDate());		
				
				String pasword = PasswordDecode(custRegistration.getPassword());					
					
				MD5 md5 = new MD5();				
				custRegistration.setPassword(md5.md5(pasword));
				getCustRegistrationService().saveorupdate(custRegistration);

				String[] recipients = new String[1];
				String[] bccRecipients = {};
				recipients[0] = custRegistration.getCustomerEmail();
				MailUtil mailutil = new MailUtil();

				status = ConstantUtil.OK;
				statusMessage = ConstantUtil.REGISTRATION_SUCCESSFUL;

				final HashMap<String, String> nameValues = ConstantMailMapperUtil
						.getRegisterationMapValue(
								custRegistration.getCustomerEmail(),
								custRegistration.getCustomerFirstName(), custRegistration.getCustomerLastName(), pasword);

				model.put(ConstantUtil.STATUS, status);
				model.put("sms", SMSUtil.sms(SMSUtil.registration(custRegistration.getCustomerFirstName()), custRegistration.getPhone()));
				getCustomerModel(model, custRegistration);

				String message = mailutil.parseFile(
						ConstantUtil.REGISTRATION,
						nameValues);
				if (mailutil.sendMail(recipients, bccRecipients,
						ConstantUtil.GREETING_FROM_VISIIT, message)) {

					log.info("Email Sent successfully on registration !");
					System.out
							.println("Email Sent successfully on registration !");
				} else {
					log.info("Error Email Sent on registration !");
				}

			} else {
				if(custRegistration.getPhone().equals(ConstantUtil.FB_NUMBER)){
					status = ConstantUtil.OK;
					statusMessage = ConstantUtil.LOGIN_SUCCESS_MESSAGE;
					model.put(ConstantUtil.STATUS, status);
					getCustomerModel(model, customerObj);
				}else{
				statusMessage = ConstantUtil.ENTERED_EMAIL_ID_ALREADY_REGISTERED;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while creating customer registration"
					+ e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}

		return model;
	}

	private String PasswordDecode(String password) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes;			
		decodedBytes = decoder.decodeBuffer(password);						
		String pasword = new String(decodedBytes);
		return pasword;
	}

	@RequestMapping(value = {"/secure/customerlogin","/customerlogin"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap customerLogin(
			@RequestParam("emailId") String a_emailId,
			@RequestParam("password") String a_password,
			@RequestParam("skey") String a_skey, HttpServletResponse response) {
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;
		ModelMap model = new ModelMap();
		try {

			CustRegistration customerRegistration = getCustRegistrationService()
					.get(a_emailId);

			String message = getUserValidation(a_password, a_skey,
					customerRegistration);
			if (message.equalsIgnoreCase(ConstantUtil.LOGIN_SUCCESS_MESSAGE)) {

				status = ConstantUtil.OK;
				statusMessage = message;
				getCustomerModel(model, customerRegistration);
			}
			if (message
					.equalsIgnoreCase(ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED)) {
				statusMessage = message;
			}
			// Added basic details
			String userDetails = CommonUtil.encodeString(customerRegistration.getCustomerEmail() + "##$" + customerRegistration.getCustomerFirstName() + "##$" + customerRegistration.getCustomerLastName() + "##$");
			saveCookie(Constants.USER_DETAILS_STR, userDetails, Constants.COOKIE_MAX_TIME, response);
			saveCookie(Constants.CUS_FIRST_NAME, customerRegistration.getCustomerFirstName(), Constants.COOKIE_MAX_TIME, response);
			saveCookie(Constants.CUS_LAST_NAME, customerRegistration.getCustomerLastName(), Constants.COOKIE_MAX_TIME, response);
			saveCookie(Constants.CUSTOMER_ID, Integer.toString(customerRegistration.getCustomerId()), Constants.COOKIE_MAX_TIME, response);
			saveCookie(Constants.EMAIL, customerRegistration.getCustomerEmail(), Constants.COOKIE_MAX_TIME, response);
			saveCookie(Constants.PHONE, customerRegistration.getPhone(), Constants.COOKIE_MAX_TIME, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}

		return model;
	}
	
	private void saveCookie(String cookieName, String value, int maxAge, HttpServletResponse response) {
	    Cookie cookie = new Cookie(cookieName, value);
	    cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
  }

	@RequestMapping(value = {"/secure/customerforgotpassword","/customerforgotpassword"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap forgetPassword(
			@RequestParam("custEmail") String a_custEmail,
			HttpServletRequest request) {
		ModelMap model = new ModelMap();
		statusMessage = ConstantUtil.FORGOT_PASSWORD_ERROR_MESSAGE;
		status = ConstantUtil.ERROR;
		try {
			CustRegistration customerRegistration = getCustRegistrationService()
					.get(a_custEmail);

			if (customerRegistration != null) {

				String newPassword = RandomStringUtils.randomAlphanumeric(10);

				MD5 md5 = new MD5();
				String newEncriptPassword = md5.md5(newPassword);
				customerRegistration.setPassword(newEncriptPassword);
				customerRegistration.setExpirationDate(new Date());
				customerRegistration.setUpdatedDate(new Date());
				getCustRegistrationService().saveorupdate(customerRegistration);

				String firstName = customerRegistration.getCustomerFirstName() != null ? customerRegistration
						.getCustomerFirstName() : "";
				String lastName = customerRegistration.getCustomerLastName() != null ? customerRegistration
						.getCustomerLastName() : "";
				String phone = customerRegistration.getPhone();
				HashMap<String, String> nameValues = ConstantMailMapperUtil
						.getForgotPasswordMapValue(a_custEmail, firstName,
								lastName, newPassword);

				String[] recipitent = { a_custEmail };
				String[] bccRecipients = {};
				if(phone!=null && !phone.equals(ConstantUtil.FB_NUMBER))
				model.put("sms", SMSUtil.sms(SMSUtil.changePassword(firstName, newPassword), phone));
				MailUtil mailUtil = new MailUtil();
				String message = mailUtil.parseFile(
						ConstantUtil.FORGOT_PASSWORD_REQUEST_FILE, nameValues);

				mailUtil.sendMail(recipitent, bccRecipients,
						ConstantUtil.REG_VISIIT_RESET_PASSWORD , message);

				status = ConstantUtil.OK;
				statusMessage = ConstantUtil.FORGOT_PASSWORD_SUCCESS_MESSAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while forget password"
					+ e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;
	}

	@RequestMapping(value = {"/secure/customerchangepassword","/customerchangepassword"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap chagePassword(
			@RequestParam("email") String a_email,
			@RequestParam("oldPassword") String a_oldPassword,
			@RequestParam("skey") String a_skey,
			@RequestParam("password") String a_password,
			@RequestParam("confirmPassword") String a_confirmPassword, HttpServletResponse response) {

		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;

		ModelMap model = new ModelMap();
		try {
			if (a_email != null && a_password != null
					&& a_confirmPassword != null) {

				CustRegistration customerRegistration = getCustRegistrationService()
						.get(a_email);
				String message = getUserValidation(a_oldPassword, a_skey,
						customerRegistration);
				
				MD5 mds= new MD5();

				if (message
						.equalsIgnoreCase(ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED)
						|| message
								.equalsIgnoreCase(ConstantUtil.LOGIN_SUCCESS_MESSAGE)) {

					if (a_password.equals(a_confirmPassword)) {
						String pasword = PasswordDecode(a_password);	
						
						if (mds.md5(pasword).equals(customerRegistration
								.getPassword())) {

							statusMessage = ConstantUtil.EXISTING_PASSWORD_AND_NEW_PASSWORD_SHOULD_NOT_BE_SAME;
						} else {
							
							customerRegistration.setPassword(mds.md5(pasword));
							Date nextYear = CommonUtil.getExpiredDate();
							customerRegistration.setExpirationDate(nextYear);
							getCustRegistrationService().saveorupdate(
									customerRegistration);
							getCustomerModel(model, customerRegistration);
							
							
							String firstName = customerRegistration.getCustomerFirstName() != null ? customerRegistration
									.getCustomerFirstName() : "";
							String lastName = customerRegistration.getCustomerLastName() != null ? customerRegistration
									.getCustomerLastName() : "";
							HashMap<String, String> nameValues = ConstantMailMapperUtil
									.getRegisterationMapValue(a_email, firstName,
											lastName, pasword);

							String[] recipitent = { a_email };
							String[] bccRecipients = {};
							MailUtil mailUtil = new MailUtil();
							String mailmessage = mailUtil.parseFile(
									ConstantUtil.FORGOT_PASSWORD_FILE, nameValues);

							mailUtil.sendMail(recipitent, bccRecipients,
									ConstantUtil.REG_VISIIT_CHANGE_PASSWORD_CHANGE, mailmessage);
							String userDetails = CommonUtil.encodeString(customerRegistration.getCustomerEmail() + "##$" + customerRegistration.getCustomerFirstName() + "##$" + customerRegistration.getCustomerLastName() + "##$");
							saveCookie(Constants.USER_DETAILS_STR, userDetails, Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.CUS_FIRST_NAME, customerRegistration.getCustomerFirstName(), Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.CUS_LAST_NAME, customerRegistration.getCustomerLastName(), Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.CUSTOMER_ID, Integer.toString(customerRegistration.getCustomerId()), Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.EMAIL, customerRegistration.getCustomerEmail(), Constants.COOKIE_MAX_TIME, response);
							saveCookie(Constants.PHONE, customerRegistration.getPhone(), Constants.COOKIE_MAX_TIME, response);
							status = ConstantUtil.OK;
							statusMessage = ConstantUtil.PASSWORD_CHANGED_SUCCESSFULLY;
						}
					} else {
						statusMessage = ConstantUtil.CHANGEPASSWORD_ERROR_MESSAGE;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while customer change password" + e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;
	}

	@RequestMapping(value = {"/secure/getCustomer","/getCustomer"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getCustomer(
			@RequestParam("customerStr") String a_customerId) {
		ModelMap model = new ModelMap();
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;

		try {
			CustRegistration customerRegistration = getCustRegistrationService()
					.getById(Integer.parseInt(a_customerId));
			if (customerRegistration != null) {
				CustomerModel customerModel =new CustomerModel(customerRegistration);

				model.put(ConstantUtil.CUSTOMER, customerModel);
				status = ConstantUtil.OK;
				statusMessage = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while get Customer" + e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;

	}
	
	
	@RequestMapping(value = {"/secure/getCustomerByEmail","/getCustomerByEmail"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getCustomerById(
			@RequestParam("customerStr") String a_customerEmail) {
		ModelMap model = new ModelMap();
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;

		try {
			CustRegistration customerRegistration = getCustRegistrationService()
					.get(a_customerEmail);
			if (customerRegistration != null) {
				CustomerModel customerModel =new CustomerModel(customerRegistration);

				model.put(ConstantUtil.CUSTOMER, customerModel);
				status = ConstantUtil.OK;
				statusMessage = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while get customer " + e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;

	}


	@RequestMapping(value = {"/secure/changeEmail","/changeEmail"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap changeEmail(
			@RequestParam("cust_id") String a_cust_id,
			@RequestParam("old_email") String a_old_email,
			@RequestParam("new_email") String a_new_email,
			@RequestParam("password") String a_password,
			@RequestParam("skey") String a_skey) {
		ModelMap model = new ModelMap();
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.LOGIN_ERROR_MESSAGE;

		try {
			CustRegistration customerRegistration = getCustRegistrationService()
					.getById(Integer.parseInt(a_cust_id));

			String message = getUserValidation(a_password, a_skey,
					customerRegistration);

			if (message
					.equalsIgnoreCase(ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED)
					|| message
							.equalsIgnoreCase(ConstantUtil.LOGIN_SUCCESS_MESSAGE)) {

				boolean emailExist = getCustRegistrationService().get(
						customerRegistration.getCustomerEmail()) != null;
				if (emailExist) {
					statusMessage = ConstantUtil.ENTERED_EMAIL_ID_ALREADY_REGISTERED;
				} else {

					customerRegistration.setCustomerEmail(a_new_email);
					getCustRegistrationService().saveorupdate(
							customerRegistration);
//					travellerDetailService.g
					
					status = ConstantUtil.OK;
					statusMessage = "Email Id Change Succesfully";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while change mail " + e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}
		return model;

	}

	@RequestMapping(value = {"/secure/updateCustomer","/updateCustomer"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap updateCustomer(
			@RequestParam("custRegStr") String a_customer) {

		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.ERROR_WHILE_UPDATEING_CUSTOMER;
		ModelMap model = new ModelMap();
		ObjectMapper mapper = new ObjectMapper();
		try {

			TypeReference<CustRegistration> custRegistrationIt = new TypeReference<CustRegistration>() {
			};

			CustRegistration custReg = mapper.readValue(a_customer,
					custRegistrationIt);
			if (custReg != null) {
				CustRegistration custRegistration = getCustRegistrationService()
						.getById(custReg.getCustomerId());

				custRegistration = getUpdatedCustomer(custReg, custRegistration);
				getCustRegistrationService().saveorupdate(custRegistration);

				status = ConstantUtil.OK;
				statusMessage = ConstantUtil.UPDATED_SUCCESSFULLY;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while customer Update" + e.getMessage());
			if(e instanceof VisiitRunTimeException){
				statusMessage=  e.getMessage();
			}else {
				statusMessage  = ErrorConstants.SERVICE_NOT_AVAILABLE;
			}
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);

		}
		return model;
	}

	private CustRegistration getUpdatedCustomer(CustRegistration custReg,
			CustRegistration custRegistration) {
		custRegistration.setUpdatedDate(new Date());
		custRegistration.setCustomerEmail(custReg.getCustomerEmail());
		custRegistration.setCustomerFirstName(custReg.getCustomerFirstName());
		custRegistration.setCustomerLastName(custReg.getCustomerLastName());
		custRegistration.setPhone(custReg.getPhone());
		custRegistration.setAge(custReg.getAge());
		custRegistration.setCity(custReg.getCity());
		custRegistration.setState(custReg.getState());
		custRegistration.setContactAddress(custReg.getContactAddress());
		custRegistration.setContactpreference(custReg.getContactpreference());
		custRegistration.setCountry(custReg.getCountry());
		custRegistration.setDateOfBirth(custReg.getDateOfBirth());
		custRegistration.setPostalCode(custReg.getPostalCode());
		return custRegistration;
	}



	private String getUserValidation(String a_password, String a_skey,
			CustRegistration customer) {
		String message = ConstantUtil.LOGIN_ERROR_MESSAGE;

		if (customer != null) {
			if (customer.getIsActive() == true) {
				String userPassword = customer.getPassword();
				userPassword = userPassword.concat(a_skey);
				MD5 md5 = new MD5();
				String newPassword = md5.md5(userPassword);

				if (newPassword.equals(a_password)) {

					Date expirationDate = customer.getExpirationDate();
					if (expirationDate.compareTo(new Date()) >= 0) {

						message = ConstantUtil.LOGIN_SUCCESS_MESSAGE;

					} else {
						message = ConstantUtil.YOUR_PASSWORD_HAS_EXPIRED;
					}
				}
			}

		}

		return message;
	}

	private void validateCustomer(CustRegistration custRegistration)
			throws Exception {
		if (custRegistration == null) {
			throw new VisiitRunTimeException(ConstantUtil.PLEASE_PROVIDE_PERSONDETAIL);
		}
		if (custRegistration.getCustomerFirstName() == null
				|| custRegistration.getCustomerFirstName().isEmpty()) {
			throw new VisiitRunTimeException(ConstantUtil.FIRSTNAME_REQUIRED);
		}
		if (custRegistration.getCustomerLastName() == null
				|| custRegistration.getCustomerLastName().isEmpty()) {
			throw new VisiitRunTimeException(ConstantUtil.LASTNAME_REQUIRED);
		}
		if (custRegistration.getPassword() == null
				|| custRegistration.getPassword().isEmpty()) {
			throw new VisiitRunTimeException(ConstantUtil.PASSWORD_REQUIRED);
		}
		if (custRegistration.getPhone() == null
				|| custRegistration.getPhone().isEmpty()) {
			throw new VisiitRunTimeException(ConstantUtil.PHONE_REQUIRED);
		}
		if (custRegistration.getCustomerEmail() == null
				|| custRegistration.getCustomerEmail().isEmpty()) {
			throw new VisiitRunTimeException(ConstantUtil.EMAIL_REQUIRED);
		}
		if (custRegistration.getCustomerEmail() != null
				&& !custRegistration.getCustomerEmail().isEmpty()
				&& !custRegistration.getCustomerEmail().matches(
						ConstantUtil.EMAIL_REGEX)) {
			throw new VisiitRunTimeException(ConstantUtil.PLEASE_PROVIDE_VALID_EMAIL);
		}
	}

	private void getCustomerModel(ModelMap model,
			CustRegistration custRegistration) {
		model.put(ConstantUtil.EMAIL, custRegistration.getCustomerEmail());
		model.put(ConstantUtil.CUSTOMER_ID, custRegistration.getCustomerId());
		model.put(ConstantUtil.CUSTOMER_FIRST_NAME,
				custRegistration.getCustomerFirstName());
		model.put(ConstantUtil.CUSTOMER_LAST_NAME,
				custRegistration.getCustomerLastName());
		model.put(ConstantUtil.PHONE,
				custRegistration.getPhone());
		}

	public CustRegistrationService getCustRegistrationService() {
		return custRegistrationService;
	}

	public void setCustRegistrationService(
			CustRegistrationService custRegistrationService) {
		this.custRegistrationService = custRegistrationService;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}
package com.rave.visiit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.model.CustomerEnquiryModel;
import com.rave.visiit.model.Enquiry;
import com.rave.visiit.model.PersonDetailModel;
import com.rave.visiit.model.TravellerDetailModel;
import com.rave.visiit.service.CustomerEnquiryService;
import com.rave.visiit.service.TravellerDetailService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.MailUtil;
import com.rave.visiit.util.SMSUtil;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class CustomerEnquiryController {
	private static Logger log = LogManager
			.getLogger(CustomerEnquiryController.class);

	@Autowired(required = true)
	@Qualifier(value = "travellerDetailService")
	TravellerDetailService travellerDetailService;

	@Autowired(required = true)
	@Qualifier(value = "customerEnquiryService")
	private CustomerEnquiryService customerEnquiryService;

	private String status;

	private String statusMessage;

	@RequestMapping(value = {"/secure/saveCustomerEnquiry","/saveCustomerEnquiry"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveCustomerEnquiry(
			@RequestParam("customerEnquiryStr") String customerEnquiryStr,
			@RequestParam("customerStr") String customerStr) {
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.ERROR;
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<CustomerEnquiry> customerEnquiryIt = new TypeReference<CustomerEnquiry>() {
			};
			CustomerEnquiry customerEnquiry = setCustomerEnquiry(
					customerEnquiryStr, mapper, customerEnquiryIt);
			if (customerStr != null && !customerStr.isEmpty()
					&& !customerStr.equals("")) {

				CustRegistration customer = new CustRegistration();
				customer.setCustomerId(Integer.parseInt(customerStr));

				customerEnquiry.setCustomer(customer);
			}

			customerEnquiryService.saveandupdate(customerEnquiry);
			customerEnquiry.setEnqCode(ConstantUtil.ENQ
					+ (1000 + customerEnquiry.getEnqId()));
			customerEnquiryService.saveandupdate(customerEnquiry);
			
			String[] recipitent = { customerEnquiry.getEmail() };
			CommonUtil.sendEnquiryMail(customerEnquiry,
					ConstantUtil.CUSTOMER_ENQUIRY_REQUEST_FILE, recipitent,
					ConstantUtil.REG_ENQUIRY + ConstantUtil.HYPHEN_WITH_SPACE + customerEnquiry.getEnqCode() + ConstantUtil.HYPHEN_WITH_SPACE + customerEnquiry.getEnqSubject() );
			
			String[] adminmail = { MailUtil.getAdminMail() };			
			CommonUtil.sendEnquiryMail(customerEnquiry,
					ConstantUtil.CUSTOMER_ENQUIRY_INTIMATION, adminmail,
					ConstantUtil.REG_ENQUIRY + ConstantUtil.HYPHEN_WITH_SPACE + customerEnquiry.getEnqCode() + ConstantUtil.HYPHEN_WITH_SPACE + customerEnquiry.getEnqSubject() );

			// sendMailToAdmin(customerEnquiry);
			status = ConstantUtil.OK;
			statusMessage = ConstantUtil.ENQUIRY_SUCCESSFULLY_CREATED;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error save customer Enquiry" + e.getMessage());
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

	@RequestMapping(value = {"/secure/updateCustomerEnquiry","/updateCustomerEnquiry"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap updateCustomerEnquiry(
			@RequestParam("customerEnquiryStr") String customerEnquiryStr,
			@RequestParam("travellerStr") String travellerStr) {
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.ERROR;
		String enquiryTxt = null;
		TravellerDetail travellerDetail = null;
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<CustomerEnquiryModel> customerEnquiryIt = new TypeReference<CustomerEnquiryModel>() {
			};
			CustomerEnquiryModel customerEnquiryModel = mapper.readValue(
					customerEnquiryStr, customerEnquiryIt);
			CustomerEnquiry customerEnquiry = customerEnquiryService
					.getById(customerEnquiryModel.getEnqId());
			if (travellerStr != null && !travellerStr.isEmpty()
					&& !travellerStr.equals("")) {
				TypeReference<TravellerDetailModel> travellerIt = new TypeReference<TravellerDetailModel>() {
				};
				TravellerDetailModel travellerDetailModel = mapper.readValue(
						travellerStr, travellerIt);
				travellerDetail = customerEnquiry
						.getTravellerDetail();

				travellerDetail.setFinalAmount(travellerDetailModel
						.getFinalAmount());

				travellerDetailService.saveorupdateTraveler(travellerDetail,
						travellerDetail.getPack().getPkId());
				enquiryTxt = travellerDetail.getPack().getPkName();
			}
			customerEnquiry.setEnqReplay(customerEnquiryModel.getEnqReplay());
			customerEnquiry.setEnqStatus(customerEnquiryModel.getEnqStatus());
			customerEnquiry.setEnqUpdatedOn(new Date());
			customerEnquiryService.saveandupdate(customerEnquiry);
			String[] recipitent = { customerEnquiry.getEmail() };
			
			if(enquiryTxt==null){
				enquiryTxt = customerEnquiry.getEnqStatus();
			}
			model.put("sms", SMSUtil.sms(SMSUtil.packageEnquiryReplied(customerEnquiry.getEnqCustomerName(), enquiryTxt, customerEnquiry.getEnqStatus(), "details"), customerEnquiry.getEnqMobile()));
			CommonUtil.sendEnquiryMail(customerEnquiry,
					ConstantUtil.CUSTOMER_ENQUIRY_REPLAY_FILE, recipitent,
					ConstantUtil.REG_ENQUIRY + ConstantUtil.HYPHEN_WITH_SPACE + customerEnquiry.getEnqCode() + ConstantUtil.HYPHEN_WITH_SPACE + customerEnquiry.getEnqSubject() );

			status = ConstantUtil.OK;
			statusMessage = ConstantUtil.ENQUIRY_SUCCESSFULLY_UPDATED;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while update customer Enquiry" + e.getMessage());
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

	private CustomerEnquiry setCustomerEnquiry(String customerEnquiryStr,
			ObjectMapper mapper,
			TypeReference<CustomerEnquiry> customerEnquiryIt)
			throws IOException, JsonParseException, JsonMappingException {
		CustomerEnquiry customerEnquiry = mapper.readValue(customerEnquiryStr,
				customerEnquiryIt);
		validateCustomerEnquiry(customerEnquiry);
		if ((customerEnquiry.getEnqStatus() == null)
				|| (customerEnquiry.getEnqStatus().isEmpty() || (customerEnquiry
						.getEnqStatus().equals("")))) {
			customerEnquiry.setEnqStatus(ConstantUtil.OPEN);
		}
		customerEnquiry.setEnqSumbitedDate(new Date());
		customerEnquiry.setEnqCreatedOn(new Date());
		customerEnquiry.setEnqUpdatedOn(new Date());
		return customerEnquiry;
	}

	@RequestMapping(value = {"/secure/getCustomerEnquiry","/getCustomerEnquiry"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap getCustomerEnquiry(
			@RequestParam("searchFieldsStr") String searchFieldsStr,
			@RequestParam("sortTypeStr") String sortTypeStr,
			@RequestParam("sortValueStr") String sortValueStr,
			@RequestParam("filterType") String filterTypeStr,
			@RequestParam("startIndexStr") String startIndexStr,
			@RequestParam("endIndexStr") String endIndexStr) {
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.ERROR;
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject myObject = new JSONObject(searchFieldsStr);
			Map searchFieldMap = CommonUtil.JsonObjectToMap(myObject);
			Enquiry enquiry = customerEnquiryService.getAllEnquiry(
					searchFieldMap, sortTypeStr, sortValueStr, filterTypeStr,
					startIndexStr, endIndexStr);

			List<CustomerEnquiryModel> customerEnquiryModelList = new ArrayList<CustomerEnquiryModel>();
			for (CustomerEnquiry customerEnquiry : enquiry.getCustomerEnquiry()) {
				CustomerEnquiryModel customerEnquiryModel = new CustomerEnquiryModel(
						customerEnquiry);

				customerEnquiryModelList.add(customerEnquiryModel);

			}

			status = ConstantUtil.OK;
			statusMessage = ConstantUtil.OK;
			model.put(ConstantUtil.TOTAL_RECORDS, enquiry.getMaxRecord());
			model.put(ConstantUtil.LISTDATA, customerEnquiryModelList);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while get customer Enquiry" + e.getMessage());
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

	@RequestMapping(value = {"/secure/getCustomerEnquiryById","/getCustomerEnquiryById"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getCustomerEnquiry(
			@RequestParam("enquriyIdStr") String enquriyIdStr) {
		status = ConstantUtil.ERROR;
		statusMessage = ConstantUtil.ERROR;
		ModelMap model = new ModelMap();
		try {

			CustomerEnquiry customerEnquiry = customerEnquiryService
					.getById(Integer.parseInt(enquriyIdStr));
			CustomerEnquiryModel customerEnquiryModel = new CustomerEnquiryModel(
					customerEnquiry);
			if (customerEnquiry.getTravellerDetail() != null) {
				TravellerDetailModel travellerDetailModel = new TravellerDetailModel(
						customerEnquiry.getTravellerDetail());

				List<PersonDetail> personDetailList = travellerDetailService
						.getPersonDetailByTraverlerId(customerEnquiry
								.getTravellerDetail());
				List<PersonDetailModel> personDetailModelList = new ArrayList<PersonDetailModel>();
				for (PersonDetail personDetail : personDetailList) {
					PersonDetailModel personDetailModel = new PersonDetailModel(
							personDetail);
					personDetailModelList.add(personDetailModel);
				}
				model.put(ConstantUtil.PERSON, personDetailModelList);
				model.put(ConstantUtil.TRIP, travellerDetailModel);
			}
			status = ConstantUtil.OK;
			statusMessage = "";
			model.put(ConstantUtil.LISTDATA, customerEnquiryModel);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while get customer Enquiry" + e.getMessage());
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

	private void validateCustomerEnquiry(CustomerEnquiry customerEnquiry) {

		try {
			if (customerEnquiry == null) {

				throw new VisiitRunTimeException(ConstantUtil.PLEASE_PROVIDE_PERSONDETAIL);

			}
			if (customerEnquiry.getEnqCustomerName() == null
					|| customerEnquiry.getEnqCustomerName().isEmpty()) {
				throw new VisiitRunTimeException(ConstantUtil.NAME_REQUIRED);
			}
			if (customerEnquiry.getEmail() == null
					|| customerEnquiry.getEmail().isEmpty()) {
				throw new VisiitRunTimeException(ConstantUtil.EMAIL_REQUIRED);
			}
			if (customerEnquiry.getEmail() != null
					&& !customerEnquiry.getEmail().isEmpty()
					&& !customerEnquiry.getEmail().matches(
							ConstantUtil.EMAIL_REGEX)) {
				throw new VisiitRunTimeException(ConstantUtil.PLEASE_PROVIDE_VALID_EMAIL);
			}
			if (customerEnquiry.getEnqMobile() == null
					|| customerEnquiry.getEnqMobile().isEmpty()) {
				throw new VisiitRunTimeException(ConstantUtil.MOBILE_REQUIRED);
			}
			if (customerEnquiry.getEnqSubject() == null
					|| customerEnquiry.getEnqSubject().isEmpty()) {
				throw new VisiitRunTimeException(ConstantUtil.SUBJECT_REQUIRED);
			}
			if (customerEnquiry.getEnqMessage() == null
					|| customerEnquiry.getEnqMessage().isEmpty()) {
				throw new VisiitRunTimeException(ConstantUtil.MESSAGE_REQUIRED);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

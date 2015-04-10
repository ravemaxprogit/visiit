package com.rave.visiit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.rave.visiit.entity.CustRegistration;
import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.PackageConditions;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.PaymentTransaction;
import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.entity.Vendor;
import com.rave.visiit.model.Payment;
import com.rave.visiit.model.PersonDetailModel;
import com.rave.visiit.model.TravellerDetailModel;
import com.rave.visiit.model.TravellerModel;
import com.rave.visiit.model.TripReceipt;
import com.rave.visiit.service.CustRegistrationService;
import com.rave.visiit.service.CustomerEnquiryService;
import com.rave.visiit.service.FileUploadService;
import com.rave.visiit.service.PackageConditionService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.service.PaymentTransactionService;
import com.rave.visiit.service.TravellerDetailService;
import com.rave.visiit.service.VendorService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.ConstantMailMapperUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.Helper;
import com.rave.visiit.util.MailUtil;
import com.rave.visiit.util.SMSUtil;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class TravellerDetailController {
	private static Logger log = LogManager
			.getLogger(TravellerDetailController.class);

	@Autowired(required = true)
	@Qualifier(value = "travellerDetailService")
	TravellerDetailService travellerDetailService;

	@Autowired(required = true)
	@Qualifier(value = "packageService")
	PackageService packageService;

	@Autowired(required = true)
	@Qualifier(value = "paymentTransactionService")
	PaymentTransactionService paymentTransactionService;

	@Autowired(required = true)
	@Qualifier(value = "fileUploadService")
	FileUploadService fileUploadService;

	@Autowired(required = true)
	@Qualifier(value = "packageConditionService")
	PackageConditionService packageConditionService;

	@Autowired(required = true)
	@Qualifier(value = "customerEnquiryService")
	private CustomerEnquiryService customerEnquiryService;

	@Autowired(required = true)
	@Qualifier(value = "custRegistrationService")
	private CustRegistrationService custRegistrationService;

	@Autowired(required = true)
	@Qualifier(value = "vendorService")
	private VendorService vendorService;

	@RequestMapping(value = { "/secure/saveTravellerDetail",
			"/saveTravellerDetail" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveTravellerDetail(
			@RequestParam("packStr") String packStr,
			@RequestParam("personDetailStr") String personDetailsStr,
			@RequestParam("travellerDetailStr") String travellerDetailStr,
			@RequestParam("customerStr") String customerStr,
			@RequestParam("prizeStr") String prizeStr) {
		ModelMap model = new ModelMap();
		try {

			// TravellerDetail travellerDetail = saveAndGetTravellerDetail(
			// packStr, personDetailsStr, travellerDetailStr, customerStr,
			// prizeStr);

			com.rave.visiit.entity.Package pack = packageService.get(Integer
					.parseInt(packStr));

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<TravellerDetail> travellerdetailIt = new TypeReference<TravellerDetail>() {
			};
			TravellerDetail travellerDetail = null;

			travellerDetail = mapper.readValue(travellerDetailStr,
					travellerdetailIt);

			validateTravellerDetail(travellerDetail);

			TypeReference<List<PersonDetail>> personDetailIt = new TypeReference<List<PersonDetail>>() {
			};

			List<PersonDetail> personDetailList = mapper.readValue(
					personDetailsStr, personDetailIt);

			PackageCost packageCost = new PackageCost();
			packageCost.setPkcId(Integer.parseInt(prizeStr));

			for (PersonDetail personDetail : personDetailList) {
				validatePersonDetail(personDetail);
			}

			travellerDetail = setTravellerDetails(customerStr, travellerDetail,
					personDetailList, packageCost);
			if (pack != null) {
				travellerDetail.setPack(pack);
			}
			travellerDetailService.saveorupdateTraveler(travellerDetail,
					Integer.parseInt(packStr));

			travellerDetailService.saveorupdatePerson(personDetailList,
					travellerDetail.getId(), Integer.parseInt(packStr));
			// Random Number generation
			Random rand = new Random();
			int randomNum = rand.nextInt(100000);

			String tripCode = ConstantUtil.TRIP + travellerDetail.getId()
					+ randomNum;
			travellerDetail.setTripcode(tripCode);

			travellerDetailService.saveorupdateTraveler(travellerDetail,
					Integer.parseInt(packStr));

			Payment payment = new Payment();
			payment.setKey(Constants.PAYMENT_GATEWAY_CODE);
			payment.setTxnid(travellerDetail.getTripcode());
			// payment.setAmount(travellerDetail.getPackageAmount());
			if (travellerDetail.getFinalAmount() != null) {
				payment.setAmount(travellerDetail.getFinalAmount());
			} else {
				payment.setAmount(travellerDetail.getPackageAmount());
			}
			payment.setSurl(CommonUtil
					.getPropertyValue(Constants.PAYMENT_SUCCESS_URL));
			payment.setCurl(CommonUtil
					.getPropertyValue(Constants.PAYMENT_CANCEL_URL));
			payment.setFurl(CommonUtil
					.getPropertyValue(Constants.PAYMENT_FAILURE_URL));
			payment.setProductinfo(pack.getPkDescripton());
			payment.setFirstname(travellerDetail.getFirstname());
			payment.setEmail(travellerDetail.getEmail());
			payment.setPhone(travellerDetail.getPhone());
			payment.setHash(Helper.sha512(payment.getPayUHash()));

			model.put(ConstantUtil.TRIPCODE, travellerDetail.getTripcode());
			model.put(Constants.PAYMENT, payment);
			model.put(Constants.AMOUNT, Double.toString(payment.getAmount()));
			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put(ConstantUtil.MESSAGE,
					ConstantUtil.TRAVELER_SAVE_SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);
			model.put(ConstantUtil.MESSAGE, e.getMessage());
		}
		return model;
	}

	private TravellerDetail setTravellerDetails(String customerStr,
			TravellerDetail travellerDetail,
			List<PersonDetail> personDetailList, PackageCost packageCost)
			throws Exception {
		if (travellerDetail.getPaymentStatus() == null
				|| travellerDetail.getPaymentStatus().equals("")
				|| travellerDetail.getPaymentStatus().isEmpty()) {
			travellerDetail.setPaymentStatus(Constants.PENING);
		}

		if (travellerDetail.getApprovedStatus() == null
				|| travellerDetail.getApprovedStatus().equals("")
				|| travellerDetail.getApprovedStatus().isEmpty()) {
			travellerDetail.setApprovedStatus(Constants.PENING);
		}

		if (travellerDetail.getTotalAmount() == null
				|| travellerDetail.getTotalAmount().equals("")) {

			double totalAmount = personDetailList.size()
					* travellerDetail.getPackageAmount();

			travellerDetail.setTotalAmount(totalAmount);
			travellerDetail.setFinalAmount(totalAmount);
		}
		travellerDetail.setVoucher(Constants.NOT_AVAILABLE);
		CustRegistration customer = new CustRegistration();
		if (customerStr == null || customerStr.isEmpty()
				|| customerStr.equals("")) {
			CustRegistration custRegistration = custRegistrationService
					.get(travellerDetail.getEmail());
			if (custRegistration != null) {
				customer.setCustomerId(custRegistration.getCustomerId());
				travellerDetail.setCustomer(customer);
			}
		} else {
			customer.setCustomerId(Integer.parseInt(customerStr));
			travellerDetail.setCustomer(customer);
		}

		travellerDetail.setPackageCost(packageCost);
		return travellerDetail;
	}

	private void paymentTripMail(TravellerDetail travellerDetail,
			List<PersonDetail> personDetailList, String tripCode)
			throws IOException, URISyntaxException {
		try {
			MailUtil mailUtil = new MailUtil();
			String[] recipitent = { travellerDetail.getEmail() };
			int i = 1;
			String persion = " ";

			for (PersonDetail personDetail : personDetailList) {

				final HashMap personNameValue = ConstantMailMapperUtil
						.getPersonDetailMapValue(personDetail);

				persion += mailUtil.parseFile("PersonDetail.txt",
						personNameValue);

			}
			TravellerDetail traverDetail = travellerDetailService
					.getTravellerDetailByTripCode(travellerDetail.getTripcode());
			List<PackageConditions> packageConditinsList = packageConditionService
					.getAll(travellerDetail.getPack().getPkId());
			String conditions = "<UL>";
			for (PackageConditions packageConditions : packageConditinsList) {

				conditions += "<li>" + packageConditions.getPcDescriptions()
						+ "</li>";

			}
			conditions += "</UL>";

			NumberFormat currencyFormatter;
			String currencyOut = "";
			currencyFormatter = NumberFormat.getCurrencyInstance(new Locale(
					Constants.EN, Constants.IN));
			currencyOut = currencyFormatter.format(traverDetail
					.getFinalAmount());

			final HashMap nameValues = ConstantMailMapperUtil
					.getTripDetailMapValue(traverDetail, tripCode, persion,
							currencyOut, conditions);

			String message = mailUtil.parseFile(ConstantUtil.TRIP_RECEIPT_FILE,
					nameValues);

			mailUtil.sendMail(recipitent, recipitent, ConstantUtil.TRIP_RECEIPT
					+ traverDetail.getPack().getPkName() + " "
					+ +traverDetail.getPack().getPkDays() + ConstantUtil.DAYS
					+ " " + + +traverDetail.getPack().getPkNights()
					+ ConstantUtil.NIGHTS + " " + ConstantUtil.PACKAGE, message);
		} catch (Exception e) {
			log.info("Error Occur while sending mail" + e.getMessage());
		}
	}

	private void validateTravellerDetail(TravellerDetail travellerDetail)
			throws Exception {
		if (travellerDetail == null) {
			throw new Exception(
					ConstantUtil.PLEASE_PROVIDE_TRAVELER_INFORMATION);
		}

		if (travellerDetail.getAddress() == null
				|| travellerDetail.getAddress().isEmpty()) {
			throw new Exception(ConstantUtil.ADDRESS_REQUIRED);
		}
		if (travellerDetail.getCity() == null
				|| travellerDetail.getCity().isEmpty()) {
			throw new Exception(ConstantUtil.CITY_REQUIRED);
		}
		if (travellerDetail.getCountry() == null
				|| travellerDetail.getCountry().isEmpty()) {
			throw new Exception(ConstantUtil.COUNTRY_REQUIRED);
		}

		if (travellerDetail.getState() == null
				|| travellerDetail.getState().isEmpty()) {
			throw new Exception(ConstantUtil.STATE_REQUIRED);
		}

		if (travellerDetail.getFirstname() == null
				|| travellerDetail.getFirstname().isEmpty()) {
			throw new Exception(ConstantUtil.FIRSTNAME_REQUIRED);
		}
		if (travellerDetail.getLastname() == null
				|| travellerDetail.getLastname().isEmpty()) {
			throw new Exception(ConstantUtil.LASTNAME_REQUIRED);
		}
		if (travellerDetail.getPhone() == null
				|| travellerDetail.getPhone().isEmpty()) {
			throw new Exception(ConstantUtil.PHONE_REQUIRED);
		}
		if (travellerDetail.getPostelCode() == null
				|| travellerDetail.getPostelCode().isEmpty()) {
			throw new Exception(ConstantUtil.POSTAL_CODE_REQUIRED);
		}
		if (travellerDetail.getTravelDate() == null) {
			throw new Exception(ConstantUtil.TRAVEL_DATE_REQUIRED);
		}
		if (travellerDetail.getEmail() != null
				&& !travellerDetail.getEmail().isEmpty()
				&& !travellerDetail.getEmail()
						.matches(ConstantUtil.EMAIL_REGEX)) {
			throw new Exception(ConstantUtil.PLEASE_PROVIDE_VALID_EMAIL);
		}

	}

	private void validatePersonDetail(PersonDetail personDetail)
			throws Exception {
		if (personDetail == null) {
			throw new Exception(ConstantUtil.PLEASE_PROVIDE_PERSONDETAIL);
		}
		if (personDetail.getSalutation() == null
				|| personDetail.getSalutation().isEmpty()) {
			throw new Exception(ConstantUtil.SALUTATION_REQUIRED);
		}
		if (personDetail.getFirstname() == null
				|| personDetail.getFirstname().isEmpty()) {
			throw new Exception(ConstantUtil.FIRSTNAME_REQUIRED);
		}
		if (personDetail.getLastname() == null
				|| personDetail.getLastname().isEmpty()) {
			throw new Exception(ConstantUtil.LASTNAME_REQUIRED);
		}
		if (personDetail.getAge() == null) {
			throw new Exception(ConstantUtil.AGE_REQUIRED);
		}
	}

	@RequestMapping(value = { "/secure/saveTransaction", "/saveTransaction" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveTransaction(
			@RequestParam("paymentStr") String paymentStr) {
		ModelMap model = new ModelMap();
		TravellerDetail travellerDetail = null;
		List<PersonDetail> personDetail = null;
		TripReceipt tripReceipt = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<PaymentTransaction> paymentTransactionIt = new TypeReference<PaymentTransaction>() {
			};
			PaymentTransaction paymentTransaction = mapper.readValue(
					paymentStr, paymentTransactionIt);
			paymentTransaction.setUpdatedon(new Timestamp(System
					.currentTimeMillis()));
			paymentTransactionService.saveorupdate(paymentTransaction);

			if (paymentTransaction.getStatus().equalsIgnoreCase(
					Constants.SUCCESS)) {
				travellerDetail = travellerDetailService
						.getTravellerDetailByTripCode(paymentTransaction
								.getTxnid());
                
				personDetail = travellerDetailService
						.getPersonDetailByTraverlerId(travellerDetail);
				model.put("sms",SMSUtil.sms(SMSUtil.packageBooked(travellerDetail.getFirstname(), travellerDetail.getPack().getPkName()), travellerDetail.getPhone()));
				paymentTripMail(travellerDetail, personDetail,
						paymentTransaction.getTxnid());
			}

			if (travellerDetail != null) {
				travellerDetail.setApprovedStatus(Constants.PENING);
				if (paymentTransaction.getStatus() != null
						&& paymentTransaction.getStatus().equalsIgnoreCase(
								Constants.SUCCESS)) {
					travellerDetail.setPaymentStatus(Constants.SUCCESS);
				} else if (paymentTransaction.getStatus() != null
						&& paymentTransaction.getStatus().equalsIgnoreCase(
								Constants.CANCEL)) {
					travellerDetail.setPaymentStatus(Constants.PENING);
				} else if (paymentTransaction.getStatus() != null
						&& paymentTransaction.getStatus().equalsIgnoreCase(
								Constants.FAILURE)) {
					travellerDetail.setPaymentStatus(Constants.PENING);
				}
				travellerDetail.setPaymentDate(new Timestamp(System
						.currentTimeMillis()));
				travellerDetailService.saveorupdateTraveler(travellerDetail,
						travellerDetail.getPack().getPkId());
				if (personDetail == null || personDetail.isEmpty()) {
					personDetail = travellerDetailService
							.getAllPersonByTraverlerId(travellerDetail.getId());
				}
				tripReceipt = populateTripReceipt(travellerDetail,
						personDetail, travellerDetail.getTripcode());
			}
			Vendor vendor = null;
			vendor = travellerDetail.getPack().getVendorInformation();
			if (vendor == null) {
				vendor = vendorService.get(travellerDetail.getPack()
						.getVendorInformation().getViVendorId());
			}

			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR,
					"Payment Transaction Saved Successfully.");
			model.put("tripCode", paymentTransaction.getTxnid());

			model.put("vendor", travellerDetail.getPack()
					.getVendorInformation());

			model.put("vendor", vendor);

			/* model.put("travellerDetail", travellerDetail); */
			model.put("tripReceipt", tripReceipt);

		} catch (Exception e) {
			log.info("Error Occur while saving Payment Transaction  "
					+ e.getMessage());
			e.printStackTrace();
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = { "/secure/viewTripDetails", "/viewTripDetails" }, method = RequestMethod.GET)
	public @ResponseBody ModelMap viewTripDetails(
			@RequestParam("tripStr") String tripStr) {

		ModelMap model = new ModelMap();

		try {
			TravellerDetail travellerDetail = travellerDetailService
					.getTravellerDetailByTripCode(tripStr);

			TravellerDetailModel travellerDetailModel = new TravellerDetailModel(
					travellerDetail);
			List<PersonDetail> personDetailList = travellerDetailService
					.getPersonDetailByTraverlerId(travellerDetail);

			List<PersonDetailModel> personDetailModelList = new ArrayList<PersonDetailModel>();
			for (PersonDetail personDetail : personDetailList) {
				PersonDetailModel personDetailModel = new PersonDetailModel(
						personDetail);
				personDetailModelList.add(personDetailModel);
			}
			model.put(ConstantUtil.STATUS, Constants.OK_STR);
			model.put("tripDetail", travellerDetailModel);
			model.put("personDetail", personDetailModelList);

		} catch (Exception e) {
			log.info("Error Occur while saving Payment Transaction  "
					+ e.getMessage());
			e.printStackTrace();
			model.put(ConstantUtil.STATUS, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = { "/secure/getTripDetail", "/getTripDetail" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap getTripDetail(
			@RequestParam("searchFieldsStr") String searchFieldsStr,
			@RequestParam("sortTypeStr") String sortTypeStr,
			@RequestParam("sortValueStr") String sortValueStr,
			@RequestParam("filterType") String filterTypeStr,
			@RequestParam("startIndexStr") String startIndexStr,
			@RequestParam("endIndexStr") String endIndexStr) {
		String status = ConstantUtil.ERROR;
		String statusMessage = ConstantUtil.ERROR;
		ModelMap model = new ModelMap();
		try {
			JSONObject myObject = new JSONObject(searchFieldsStr);
			Map searchFieldMap = CommonUtil.JsonObjectToMap(myObject);
			TravellerModel travellerModel = travellerDetailService
					.getTravellerDetails(searchFieldMap, sortTypeStr,
							sortValueStr, filterTypeStr, startIndexStr,
							endIndexStr);
			List<TravellerDetail> travellerDetailList = travellerModel
					.getTravellerDetail();
			List<TravellerDetailModel> travellerDetailModels = new ArrayList<TravellerDetailModel>();
			for (TravellerDetail travellerDetail : travellerDetailList) {
				TravellerDetailModel travellerDetailModel = new TravellerDetailModel(
						travellerDetail);

				travellerDetailModels.add(travellerDetailModel);
			}
			status = ConstantUtil.OK;
			statusMessage = ConstantUtil.OK;
			model.put(ConstantUtil.TOTAL_RECORDS, travellerModel.getMaxRecord());
			model.put(ConstantUtil.LISTDATA, travellerDetailModels);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}

		return model;
	}

	@RequestMapping(value = { "/secure/updateTravellerDetail",
			"/updateTravellerDetail" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap updateTravellerDetail(
			@RequestParam("tripCodeStr") String tripCodeStr,
			@RequestParam("approvestatusStr") String approvestatusStr,
			@RequestParam("approvecomandsStr") String approvecomandsStr,
			@RequestParam("paymentstatusStr") String paymentstatusStr,
			@RequestParam("paymentcomandsStr") String paymentcomandsStr,
			@RequestParam("finalAmount") String finalAmount) {
		ModelMap model = new ModelMap();
		try {
			TravellerDetail travellerDetail = travellerDetailService
					.getTravellerDetailByTripCode(tripCodeStr);
			travellerDetail.setApprovedStatus(approvestatusStr);
			travellerDetail.setStatusCommands(approvecomandsStr);
			travellerDetail.setPaymentStatus(paymentstatusStr);
			travellerDetail.setPayementCommands(paymentcomandsStr);
			travellerDetail.setFinalAmount(Double.parseDouble(finalAmount));

			if (travellerDetail.getPaymentDate() == null
					&& paymentstatusStr != null
					&& paymentstatusStr.endsWith(Constants.SUCCESS)) {
				travellerDetail.setPaymentDate(new Date());

			}

			travellerDetailService.saveorupdateTraveler(travellerDetail,
					travellerDetail.getPack().getPkId());

			com.rave.visiit.entity.Package pack = packageService
					.get(travellerDetail.getPack().getPkId());

			String[] recipitent = { travellerDetail.getEmail() };

			String[] bccRecipients = {};
			MailUtil mailUtil = new MailUtil();
			String message = "";
			String subject = "";
			HashMap<String, String> nameValues = ConstantMailMapperUtil
					.getTripMapValue(travellerDetail);
			if (approvestatusStr != null
					&& approvestatusStr.endsWith(Constants.APPROVED)) {

				message = mailUtil
						.parseFile(ConstantUtil.CUSTOMER_PAYMENT_APPROVAL_FILE,
								nameValues);
				subject = ConstantUtil.REG_YOUR + pack.getPkName() + " "
						+ pack.getPkDays() + " " + ConstantUtil.DAYS + " "
						+ pack.getPkNights() + " " + ConstantUtil.NIGHTS
						+ ConstantUtil.TRIP_CONFIRMATION_FROM_VISIIT;
			} else {

				if (approvestatusStr != null
						&& approvestatusStr.endsWith(Constants.PARTIAL_REFUND)) {
					message = mailUtil.parseFile(
							ConstantUtil.CUSTOMER_PAYMENT_PRTIALREFUND_FILE,
							nameValues);
				}
				if (approvestatusStr != null
						&& approvestatusStr.endsWith(Constants.REFUND)) {
					message = mailUtil.parseFile(
							ConstantUtil.CUSTOMER_PAYMENT_REFUND_FILE,
							nameValues);
				}
				subject = ConstantUtil.REG_YOUR + pack.getPkName() + " "
						+ pack.getPkDays() + " " + ConstantUtil.DAYS + " "
						+ pack.getPkNights() + " " + ConstantUtil.NIGHTS + " "
						+ approvestatusStr;
			}
			mailUtil.sendMail(recipitent, bccRecipients, subject, message);
			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put(ConstantUtil.MESSAGE,
					ConstantUtil.TRAVELER_UPDATE_SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);
			model.put(ConstantUtil.MESSAGE, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = { "/secure/saveTripandEnquiry",
			"/saveTripandEnquiry" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveTripandEnquiry(
			@RequestParam("packStr") String packStr,
			@RequestParam("personDetailStr") String personDetailsStr,
			@RequestParam("travellerDetailStr") String travellerDetailStr,
			@RequestParam("customerStr") String customerStr,
			@RequestParam("prizeStr") String prizeStr,
			@RequestParam("customerEnquiryStr") String customerEnquiryStr) {
		String status = ConstantUtil.ERROR;
		String statusMessage = ConstantUtil.ERROR;
		String packName = null;
		ModelMap model = new ModelMap();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<CustomerEnquiry> customerEnquiryIt = new TypeReference<CustomerEnquiry>() {
			};

			com.rave.visiit.entity.Package pack = packageService.get(Integer
					.parseInt(packStr));

			CustomerEnquiry customerEnquiry = mapper.readValue(
					customerEnquiryStr, customerEnquiryIt);

			TravellerDetail travellerDetail = null;

			TypeReference<TravellerDetail> travellerdetailIt = new TypeReference<TravellerDetail>() {
			};

			travellerDetail = mapper.readValue(travellerDetailStr,
					travellerdetailIt);

			validateTravellerDetail(travellerDetail);

			TypeReference<List<PersonDetail>> personDetailIt = new TypeReference<List<PersonDetail>>() {
			};

			List<PersonDetail> personDetailList = mapper.readValue(
					personDetailsStr, personDetailIt);

			PackageCost packageCost = new PackageCost();
			packageCost.setPkcId(Integer.parseInt(prizeStr));

			for (PersonDetail personDetail : personDetailList) {
				validatePersonDetail(personDetail);
			}

			travellerDetail = setTravellerDetails(customerStr, travellerDetail,
					personDetailList, packageCost);

			travellerDetailService.saveorupdateTraveler(travellerDetail,
					Integer.parseInt(packStr));

			travellerDetailService.saveorupdatePerson(personDetailList,
					travellerDetail.getId(), Integer.parseInt(packStr));
			// Random Number generation
			Random rand = new Random();
			int randomNum = rand.nextInt(100000);

			String tripCode = ConstantUtil.TRIP + travellerDetail.getId()
					+ randomNum;
			travellerDetail.setTripcode(tripCode);

			travellerDetailService.saveorupdateTraveler(travellerDetail,
					Integer.parseInt(packStr));

			// customerEnquiry.setEnqPackageName(pack.getPkName());
			customerEnquiry.setTravellerDetail(travellerDetail);
			customerEnquiry.setEnqCustomerName(travellerDetail.getFirstname()
					+ " " + travellerDetail.getLastname());
			customerEnquiry.setEmail(travellerDetail.getEmail());
			customerEnquiry.setEnqMobile(travellerDetail.getPhone());
			if ((customerEnquiry.getEnqStatus() == null)
					|| (customerEnquiry.getEnqStatus().isEmpty() || (customerEnquiry
							.getEnqStatus().equals("")))) {
				customerEnquiry.setEnqStatus(ConstantUtil.OPEN);
			}
			customerEnquiry.setEnqSumbitedDate(new Date());
			customerEnquiry.setEnqCreatedOn(new Date());
			customerEnquiry.setEnqUpdatedOn(new Date());
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
			if(customerEnquiry.getEnqPackageName() != null){
				packName = customerEnquiry.getEnqPackageName();
			}else{
				if(pack != null)
				    packName = pack.getPkName();
				if(packName==null)
					packName = "VISIIT's tour and travel services";
			}
			model.put("sms",SMSUtil.sms(SMSUtil.packageEnquiryRecieved(customerEnquiry.getEnqCustomerName(), customerEnquiry.getEnqPackageName(),customerEnquiry.getEnqCode()), travellerDetail.getPhone()));
			CommonUtil.sendEnquiryMail(
					customerEnquiry,
					ConstantUtil.CUSTOMER_ENQUIRY_REQUEST_FILE,
					recipitent,
					ConstantUtil.REG_ENQUIRY + ConstantUtil.HYPHEN_WITH_SPACE
							+ customerEnquiry.getEnqCode()
							+ ConstantUtil.HYPHEN_WITH_SPACE
							+ customerEnquiry.getEnqSubject());

			String[] adminmail = { MailUtil.getAdminMail() };
			CommonUtil.sendEnquiryMail(
					customerEnquiry,
					ConstantUtil.CUSTOMER_ENQUIRY_INTIMATION,
					adminmail,
					ConstantUtil.REG_ENQUIRY + ConstantUtil.HYPHEN_WITH_SPACE
							+ customerEnquiry.getEnqCode()
							+ ConstantUtil.HYPHEN_WITH_SPACE
							+ customerEnquiry.getEnqSubject());
			status = ConstantUtil.OK;
			statusMessage = ConstantUtil.ENQUIRY_SUCCESSFULLY_CREATED;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			model.put(ConstantUtil.STATUS, status);
			model.put(ConstantUtil.MESSAGE, statusMessage);
		}

		return model;
	}

	private TripReceipt populateTripReceipt(TravellerDetail travellerDetail,
			List<PersonDetail> personDetails, String tripCode)
			throws IOException, URISyntaxException {
		TripReceipt tripReceipt = new TripReceipt();
		try {
			com.rave.visiit.entity.Package pack = packageService
					.get(travellerDetail.getPack().getPkId());
			tripReceipt.setAddress(travellerDetail.getAddress());
			tripReceipt.setCity(travellerDetail.getCity());
			tripReceipt.setPostalCode(travellerDetail.getPostelCode());
			tripReceipt.setTripCode(tripCode);
			if (travellerDetail.getTravelDate() != null) {
				tripReceipt.setTripDate(CommonUtil
						.getDateFormat(travellerDetail.getTravelDate()));
			}
			NumberFormat currencyFormatter;
			String currencyOut = "";
			currencyFormatter = NumberFormat.getCurrencyInstance(new Locale(
					Constants.EN, Constants.IN));
			currencyOut = currencyFormatter.format(travellerDetail
					.getFinalAmount());
			tripReceipt.setTotalAmount(currencyOut);

			if (travellerDetail.getPaymentDate() != null) {
				tripReceipt.setBookDate(CommonUtil
						.getDateFormat(travellerDetail.getPaymentDate()));
			}

			tripReceipt.setPackageName(pack.getPkName());
			tripReceipt.setDayNight(pack.getPkDays() + "DAYS/"
					+ pack.getPkNights() + "Nights");
			if (travellerDetail.getPackageCost() != null
					&& travellerDetail.getPackageCost().getPkcDescription() != null) {
				tripReceipt.setHotels(travellerDetail.getPackageCost()
						.getPkcDescription());
			}

			tripReceipt.setContact(travellerDetail.getPhone());
			tripReceipt.setFirstName(travellerDetail.getFirstname());
			tripReceipt.setLastName(travellerDetail.getLastname());
			tripReceipt.setState(travellerDetail.getState());
			tripReceipt.setCountry(travellerDetail.getCountry());

			List<PersonDetailModel> personDetailList = new ArrayList<PersonDetailModel>();
			for (PersonDetail personDetail : personDetails) {
				PersonDetailModel personDetailModel = new PersonDetailModel(
						personDetail);
				personDetailList.add(personDetailModel);
			}
			tripReceipt.setPersons(personDetailList);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error Occur while populateTripReceipt" + e.getMessage());
		}
		return tripReceipt;
	}

	@RequestMapping(value = { "/secure/saveTravellerDetailByAdmin",
			"/saveTravellerDetailByAdmin" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveTravellerDetailByAdmin(
			@RequestParam("packStr") String packStr,
			@RequestParam("personDetailStr") String personDetailsStr,
			@RequestParam("travellerDetailStr") String travellerDetailStr,
			@RequestParam("customerStr") String customerStr,
			@RequestParam("prizeStr") String prizeStr) {
		ModelMap model = new ModelMap();
		try {

			// TravellerDetail travellerDetail = saveAndGetTravellerDetail(
			// packStr, personDetailsStr, travellerDetailStr, customerStr,
			// prizeStr);

			TravellerDetail travellerDetail = null;

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<TravellerDetail> travellerdetailIt = new TypeReference<TravellerDetail>() {
			};

			travellerDetail = mapper.readValue(travellerDetailStr,
					travellerdetailIt);

			validateTravellerDetail(travellerDetail);

			if (travellerDetail.getPaymentStatus() != null
					&& !travellerDetail.getPaymentStatus().equals("")
					&& !travellerDetail.getPaymentStatus().isEmpty()) {
				travellerDetail.setPaymentDate(new Date());
			}

			TypeReference<List<PersonDetail>> personDetailIt = new TypeReference<List<PersonDetail>>() {
			};

			List<PersonDetail> personDetailList = mapper.readValue(
					personDetailsStr, personDetailIt);

			PackageCost packageCost = new PackageCost();
			packageCost.setPkcId(Integer.parseInt(prizeStr));

			for (PersonDetail personDetail : personDetailList) {
				validatePersonDetail(personDetail);
			}

			travellerDetail = setTravellerDetails(customerStr, travellerDetail,
					personDetailList, packageCost);

			travellerDetailService.saveorupdateTraveler(travellerDetail,
					Integer.parseInt(packStr));

			travellerDetailService.saveorupdatePerson(personDetailList,
					travellerDetail.getId(), Integer.parseInt(packStr));
			// Random Number generation
			Random rand = new Random();
			int randomNum = rand.nextInt(100000);

			String tripCode = ConstantUtil.TRIP + travellerDetail.getId()
					+ randomNum;
			travellerDetail.setTripcode(tripCode);

			travellerDetailService.saveorupdateTraveler(travellerDetail,
					Integer.parseInt(packStr));
			if (travellerDetail.getPaymentStatus() != null
					&& travellerDetail.getPaymentStatus().equals(
							Constants.SUCCESS)
					&& !travellerDetail.getPaymentStatus().isEmpty()) {
				paymentTripMail(travellerDetail, personDetailList,
						travellerDetail.getTripcode());

			}
			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put(ConstantUtil.MESSAGE,
					ConstantUtil.TRAVELER_SAVE_SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);
			model.put(ConstantUtil.MESSAGE, e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = { "/secure/getPaymentDetailByTripCode",
			"/getPaymentDetailByTripCode" }, method = RequestMethod.GET)
	public @ResponseBody ModelMap getPaymentDetailByTripCode(
			@RequestParam("tripCode") String tripCode) {
		ModelMap model = new ModelMap();
		try {

			TravellerDetail travellerDetail = travellerDetailService
					.getTravellerDetailByTripCode(tripCode);

			com.rave.visiit.entity.Package pack = packageService
					.get(travellerDetail.getPack().getPkId());

			Payment payment = new Payment();
			payment.setKey(Constants.PAYMENT_GATEWAY_CODE);
			payment.setTxnid(travellerDetail.getTripcode());
			// payment.setAmount(travellerDetail.getPackageAmount());
			if (travellerDetail.getFinalAmount() != null) {
				payment.setAmount(travellerDetail.getFinalAmount());
			} else {
				payment.setAmount(travellerDetail.getPackageAmount());
			}
			payment.setSurl(CommonUtil
					.getPropertyValue(Constants.PAYMENT_SUCCESS_URL));
			payment.setCurl(CommonUtil
					.getPropertyValue(Constants.PAYMENT_CANCEL_URL));
			payment.setFurl(CommonUtil
					.getPropertyValue(Constants.PAYMENT_FAILURE_URL));
			payment.setProductinfo(pack.getPkDescripton());
			payment.setFirstname(travellerDetail.getFirstname());
			payment.setEmail(travellerDetail.getEmail());
			payment.setPhone(travellerDetail.getPhone());
			payment.setHash(Helper.sha512(payment.getPayUHash()));

			model.put(ConstantUtil.TRIPCODE, travellerDetail.getTripcode());
			model.put(Constants.PAYMENT, payment);
			model.put(Constants.AMOUNT, Double.toString(payment.getAmount()));
			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put("payment", payment);

		} catch (Exception e) {
			e.printStackTrace();
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);
			model.put(ConstantUtil.MESSAGE, e.getMessage());
		}
		return model;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/secure/getDownloadTripRecipt",
			"/getDownloadTripRecipt" }, method = RequestMethod.GET)
	public @ResponseBody ModelMap getDownloadTripRecipt(
			@RequestParam("tripCode") String tripCode) {
		ModelMap model = new ModelMap();
		try {

			TravellerDetail travellerDetail = travellerDetailService
					.getTravellerDetailByTripCode(tripCode);

			List<PersonDetail> personDetailList = travellerDetailService
					.getAllPersonByTraverlerId(travellerDetail.getId());

			List<PackageConditions> packageConditinsList = packageConditionService
					.getAll(travellerDetail.getPack().getPkId());

			TripReceipt tripReceipt = populateTripReceipt(travellerDetail,
					personDetailList, travellerDetail.getTripcode());

			List<String> packageCondiation = new ArrayList<String>();

			for (PackageConditions packageCondion : packageConditinsList) {
				packageCondiation.add(packageCondion.getPcDescriptions());
			}

			tripReceipt.setPackageConditions(packageCondiation);

			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put("tripReceipt", tripReceipt);

			model.put("vendorContectNo", travellerDetail.getPack()
					.getVendorInformation().getViContactNo1());
		}

		catch (Exception e) {
			e.printStackTrace();
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);
			model.put(ConstantUtil.MESSAGE, e.getMessage());
		}
		return model;

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/secure/getStatistic", "/getStatistic" }, method = RequestMethod.GET)
	public @ResponseBody ModelMap getStatistics() {
		ModelMap model = new ModelMap();
		try {

			Integer packageBooked = travellerDetailService.getNoPackageBooked();
			Integer tripCompleted = travellerDetailService.getNoTripCompleted();
			Integer quiryRequest = customerEnquiryService.getNoQuiryRequest();
			Integer quiryReply = customerEnquiryService.getNoQuiryReply();

			model.put(ConstantUtil.STATUS, ConstantUtil.OK);
			model.put("packageBooked", packageBooked);
			model.put("tripCompleted", tripCompleted);
			model.put("quiryRequest", quiryRequest);
			model.put("quiryReply", quiryReply);
		} catch (Exception e) {
			if (e instanceof VisiitRunTimeException) {
				model.put(ConstantUtil.MESSAGE, e.getMessage());
			} else {
				model.put(ConstantUtil.MESSAGE,
						ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			model.put(ConstantUtil.STATUS, ConstantUtil.ERROR);

		}
		return model;

	}
}
package com.rave.visiit.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.NewsLetter;
import com.rave.visiit.entity.NewsLetterSubscriber;
import com.rave.visiit.entity.TravellerDetail;
import com.rave.visiit.entity.TripVoucher;
import com.rave.visiit.service.FileUploadService;
import com.rave.visiit.service.TravellerDetailService;
import com.rave.visiit.util.ConstantMailMapperUtil;
import com.rave.visiit.util.ConstantUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.MailUtil;
import com.rave.visiit.util.SMSUtil;

@Controller
public class FileUploadController {

	private static Logger logger = LogManager
			.getLogger(FileUploadController.class);

	@Autowired(required = true)
	@Qualifier(value = "fileUploadService")
	private FileUploadService fileUploadService;

	@Autowired(required = true)
	@Qualifier(value = "travellerDetailService")
	private TravellerDetailService travellerDetailService;

	public FileUploadService getFileUploadService() {
		return fileUploadService;
	}

	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@RequestMapping(value = { "/secure/saveTripVoucher", "/saveTripVoucher" }, method = RequestMethod.POST, headers = "Accept=application/multipart")
	public @ResponseBody String saveTripVoucher(
			@RequestParam("tripCode") String tripCode,
			@RequestParam("file") MultipartFile file) throws Exception {
		logger.info("saveTripVoucher Start");
		ModelMap model = new ModelMap();
		ObjectMapper mapper = new ObjectMapper();
		String resultStr = null;
		if (!file.isEmpty()) {
			String name = file.getOriginalFilename();
			try {
				// byte[] bytes = file.getBytes();
				TripVoucher tripVoucher = new TripVoucher();
				tripVoucher.setData(file.getBytes());
				tripVoucher.setFileName(file.getOriginalFilename());
				tripVoucher.setIsDeleted(Boolean.FALSE);
				tripVoucher.setMimeType(file.getContentType());
				tripVoucher.setTripCode(tripCode);
				tripVoucher.setUploadedBy(Constants.ADMIN);
				tripVoucher.setUploadedOn(new Timestamp(System
						.currentTimeMillis()));
				tripVoucher.setModifiedBy(Constants.ADMIN);
				tripVoucher.setModifiedOn(new Timestamp(System
						.currentTimeMillis()));
				fileUploadService.saveTripVoucher(tripVoucher);
				TravellerDetail travellerDetail = travellerDetailService
						.getTravellerDetailByTripCode(tripCode);
				if (travellerDetail != null) {
					travellerDetail.setVoucher(Constants.AVAILABLE);
					travellerDetailService.saveorupdateTraveler(
							travellerDetail, travellerDetail.getPack()
									.getPkId());
					if(travellerDetail.getPhone() != null && !travellerDetail.getPhone().equals(""))
					model.put("sms", SMSUtil.sms(SMSUtil.packageVoucher(travellerDetail.getFirstname(), travellerDetail.getVoucher()), travellerDetail.getPhone()));
					mailSendVoucherDetails(tripVoucher, travellerDetail);

				}
				model.put(Constants.STATUS_STR, Constants.OK_STR);
				model.put(Constants.MESSAGE_STR,
						"You successfully uploaded file=" + name);
				resultStr = mapper.writeValueAsString(Arrays.asList(model));
				// return "You successfully uploaded file=" + name;
				return resultStr;
			} catch (Exception e) {
				e.printStackTrace();
				model.put(Constants.STATUS_STR, Constants.ERROR_STR);
				model.put(Constants.MESSAGE_STR, "You failed to upload " + name
						+ " => " + e.getMessage());
				resultStr = mapper.writeValueAsString(Arrays.asList(model));
				return resultStr;
				// return "You failed to upload " + name + " => " +
				// e.getMessage();
			}
		} else {
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR,
					"You failed to upload because the file was empty.");
			resultStr = mapper.writeValueAsString(Arrays.asList(model));
			return resultStr;
			// return "You failed to upload because the file was empty.";
		}

	}

	private void mailSendVoucherDetails(TripVoucher tripVoucher,
			TravellerDetail travellerDetail ) throws IOException,
			URISyntaxException {
		HashMap<String, String> nameValues = ConstantMailMapperUtil
				.getTripMapValue(travellerDetail);

		String[] recipitent = { travellerDetail.getEmail() };
		String[] bccRecipients = {};
		MailUtil mailUtil = new MailUtil();
		String message = mailUtil.parseFile(
				ConstantUtil.TRIP_ATTACH_VOUCHERS_FILE, nameValues);


		try {
			mailUtil.sendEmailWithVoucherAttachment(
					recipitent,
					bccRecipients,
					ConstantUtil.REG_VOUCHER_SUBJECT + ""
							+ tripVoucher.getTripCode(), message,tripVoucher);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = { "/secure/getTripVoucher", "/getTripVoucher" }, method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public @ResponseBody byte[] getTripVoucher(
			@RequestParam("tripCode") String tripCode,
			HttpServletResponse response) {
		logger.info("getTripVoucher Start");
		ModelMap model = new ModelMap();
		TripVoucher tripVoucher = null;
		try {
			tripVoucher = fileUploadService.getTripVoucher(tripCode);
			if (tripVoucher == null) {
				throw new Exception("Trip Voucher not found or Already Deleted");
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("tripVoucher", tripVoucher);
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ tripVoucher.getFileName() + "\"");
			response.setContentLength(tripVoucher.getData().length);
			response.setContentType(tripVoucher.getMimeType());
			return tripVoucher.getData();
		} catch (Exception e) {
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = { "/secure/deleteTripVoucher", "/deleteTripVoucher" }, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteTripVoucher(
			@RequestParam("tripCode") String tripCode) {
		logger.info("deleteTripVoucher Start");
		ModelMap model = new ModelMap();
		try {
			TripVoucher tripVoucher = fileUploadService
					.getTripVoucher(tripCode);
			if (tripVoucher == null) {
				throw new Exception("Trip Voucher not found or Already Deleted");
			}
			fileUploadService.deleteTripVoucherById(tripVoucher.getVoucherId());
			/*tripVoucher.setIsDeleted(Boolean.TRUE);
			tripVoucher.setModifiedBy(Constants.ADMIN);
			tripVoucher.setModifiedOn(new Timestamp(System.currentTimeMillis()));
			fileUploadService.saveTripVoucher(tripVoucher);*/
			TravellerDetail travellerDetail = travellerDetailService
					.getTravellerDetailByTripCode(tripCode);
			if (travellerDetail != null) {
				travellerDetail.setVoucher(Constants.NOT_AVAILABLE);
				travellerDetailService.saveorupdateTraveler(travellerDetail,
						travellerDetail.getPack().getPkId());
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR, "Voucher Deleted Successfully.");
			return model;
		} catch (Exception e) {
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
			return model;
		}
	}

	@RequestMapping(value = { "/secure/getTripVoucherFileName",
			"/getTripVoucherFileName" }, method = RequestMethod.GET)
	public @ResponseBody ModelMap getTripVoucherFileName(
			@RequestParam("tripCode") String tripCode) {
		logger.info("getTripVoucherFileName Start");
		ModelMap model = new ModelMap();
		TripVoucher tripVoucher = null;
		try {
			tripVoucher = fileUploadService.getTripVoucher(tripCode);
			if (tripVoucher == null) {
				throw new Exception("Trip Voucher not found or Already Deleted");
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("fileName", tripVoucher.getFileName());
			return model;
		} catch (Exception e) {
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			model.put(Constants.MESSAGE_STR, e.getMessage());
			return model;
		}
	}
	
	@RequestMapping(value = {"/secure/saveNewsLetter","/saveNewsLetter"}, method = RequestMethod.POST, headers="Accept=application/multipart")
	   public @ResponseBody String saveNewsLetter(@RequestParam("file") MultipartFile file) throws Exception {  
		 logger.info("saveNewsLetter Start");
		 ModelMap model = new ModelMap();
		 ObjectMapper mapper = new ObjectMapper();
		 String resultStr=null;
		 if (!file.isEmpty()) {
			 String name = file.getOriginalFilename();
	            try {
	            	fileUploadService.deleteNewsLetter();
	                NewsLetter newsLetter = new NewsLetter();
	                newsLetter.setData(file.getBytes());
	                newsLetter.setFileName(file.getOriginalFilename());
	                newsLetter.setMimeType(file.getContentType());
	                newsLetter.setUploadedBy(Constants.ADMIN);
	                newsLetter.setUploadedOn(new Timestamp(System.currentTimeMillis()));
	                fileUploadService.saveNewsLetter(newsLetter);
	                model.put(Constants.STATUS_STR, Constants.OK_STR);
	                model.put(Constants.MESSAGE_STR, "You successfully uploaded file=" + name);
	                model.put("fileName", name);
	                fileUploadService.bulkUpdateNewsLetterSubscriber();
	                resultStr = mapper.writeValueAsString(Arrays.asList(model));
	                return resultStr;
	            } catch (Exception e) {
	            	e.printStackTrace();
	            	model.put(Constants.STATUS_STR, Constants.ERROR_STR);
	                model.put(Constants.MESSAGE_STR, "You failed to upload " + name + " => " + e.getMessage());
	                resultStr = mapper.writeValueAsString(Arrays.asList(model));
	                return resultStr;
	            }
	        } else {
	        	model.put(Constants.STATUS_STR, Constants.ERROR_STR);
	        	model.put(Constants.MESSAGE_STR, "You failed to upload because the file was empty.");
	        	resultStr = mapper.writeValueAsString(Arrays.asList(model));
	        	return resultStr;
	        }
	 
	  }
	  
	  
	  @RequestMapping(value = {"/secure/saveSubscriber","/saveSubscriber"}, method = RequestMethod.POST)
	   public @ResponseBody ModelMap saveSubscriber(@RequestParam("subscriberEmail") String subscriberEmail) {  
		 logger.info("saveSubscriber Start");
		 	ModelMap model = new ModelMap();
		 	NewsLetterSubscriber newsLetterSubscriber = new NewsLetterSubscriber();
	            try {
	            	newsLetterSubscriber.setIsSent(Boolean.FALSE);
	            	newsLetterSubscriber.setSubscriberEmailId(subscriberEmail);
	            	newsLetterSubscriber.setSubscribedBy(subscriberEmail);
	            	newsLetterSubscriber.setSubscribedOn(new Timestamp(System.currentTimeMillis()));
	            	newsLetterSubscriber = fileUploadService.saveSubscriber(newsLetterSubscriber);
	                model.put(Constants.STATUS_STR, Constants.OK_STR);
	                model.put(Constants.MESSAGE_STR, "Subscribed Successfully.");
	                return model;
	            } catch (Exception e) {
	            	e.printStackTrace();
	            	if(e instanceof ConstraintViolationException){
	            		model.put(Constants.STATUS_STR, Constants.OK_STR);
	            		model.put(Constants.MESSAGE_STR, "Subscribed Successfully.");
	            	}else {
	            		model.put(Constants.STATUS_STR, Constants.ERROR_STR);
	            		model.put(Constants.MESSAGE_STR, e.getMessage());
	            	}
	            	
	            	
	            	return model;
	            }
	  }
	  
	  @RequestMapping(value = {"/secure/getNewsLetter","/getNewsLetter"}, method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	   public @ResponseBody byte[] getNewsLetter(HttpServletResponse response) throws Exception {  
		 logger.info("getNewsLetter Start");
		 ModelMap model = new ModelMap();
	            try {
	                byte[] data = null;
	                NewsLetter newsLetter =  fileUploadService.getNewsLetter();
	                if(newsLetter != null){
	                	data= newsLetter.getData();
	                }
	                model.put(Constants.STATUS_STR, Constants.OK_STR);
	                model.put("newsLetter", newsLetter);
	                response.setHeader("Content-Disposition", "attachment; filename=\""
	    					+ newsLetter.getFileName() + "\"");
	    			response.setContentLength(newsLetter.getData().length);
	    			response.setContentType(newsLetter.getMimeType());
	                return data;
	            } catch (Exception e) {
	            	e.printStackTrace();
	            	model.put(Constants.STATUS_STR, Constants.ERROR_STR);
	                model.put(Constants.MESSAGE_STR,  e.getMessage());
	                return new byte[0];
	            }
	  }
	  
	  @RequestMapping(value = { "/secure/getNewsLetterFileName",
		"/getNewsLetterFileName" }, method = RequestMethod.GET)
	  public @ResponseBody ModelMap getNewsLetterFileName() {
	logger.info("getTripVoucherFileName Start");
	ModelMap model = new ModelMap();
	NewsLetter newsLetter = null;
	try {
		newsLetter = fileUploadService.getNewsLetter();
		if (newsLetter == null) {
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("fileName", "");
		}
		model.put(Constants.STATUS_STR, Constants.OK_STR);
		model.put("fileName", newsLetter.getFileName());
		return model;
	} catch (Exception e) {
		model.put(Constants.STATUS_STR, Constants.ERROR_STR);
		model.put(Constants.MESSAGE_STR, e.getMessage());
		return model;
	}
}
}

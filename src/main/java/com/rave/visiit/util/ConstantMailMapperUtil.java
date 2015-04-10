package com.rave.visiit.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import com.rave.visiit.entity.CustomerEnquiry;
import com.rave.visiit.entity.PersonDetail;
import com.rave.visiit.entity.TravellerDetail;

public class ConstantMailMapperUtil {

	public static final String $AGE$ = "$AGE$";
	public static final String $DAY$ = "$DAY$";
	public static final String $NAME$ = "$NAME$";
	public static final String $EMAIL$ = "$EMAIL$";
	public static final String $NIGHT$ = "$NIGHT$";
	public static final String $STATUS$ = "$STATUS$";
	public static final String $USER_ID$ = "$USER_ID$";
	public static final String $ADUILTS$ = "$ADUILTS$";
	public static final String $LASTNAME$ = "$LASTNAME$";
	public static final String $TRIPCODE$ = "$TRIPCODE$";
	public static final String $PASSWORD$ = "$PASSWORD$";
	public static final String $CHILDREN$ = "$CHILDREN$";
	public static final String $RESET_URL$ = "$RESET_URL$";
	public static final String $LOGIN_URL$ = "$LOGIN_URL$";
	public static final String $FIRSTNAME$ = "$FIRSTNAME$";
	public static final String $SALUTATION$ = "$SALUTATION$";
	public static final String $TRAVELDATE$ = "$TRAVELDATE$";
	public static final String $TRIPDETAILS$ = "$TRIPDETAILS$";
	public static final String $TOTALAMOUNT$ = "$TOTALAMOUNT$";
	public static final String $VISIIT_SATE$ = "$VISIIT_SATE$";
	public static final String $COMMENTS_END$ = "$COMMENTS_END$";
	public static final String $PACKAGE_NAME$ = "$PACKAGE_NAME$";
	public static final String $PAYMENT_DATE$ = "$PAYMENT_DATE$";
	public static final String $NEW_PASSWORD$ = "$NEW_PASSWORD$";
	public static final String $BILLING_CITY$ = "$BILLING_CITY$";
	public static final String $ENQUIRY_CODE$ = "$ENQUIRY_CODE$";
	public static final String $QUERY_MESSAGE$ = "$QUERY_MESSAGE$";
	public static final String $BILLING_STATE$ = "$BILLING_STATE$";
	public static final String $VISIIT_ADDRESS$ = "$VISIIT_ADDRESS$";
	public static final String $VISIIT_COUNTRY$ = "$VISIIT_COUNTRY$";
	public static final String $APPROVE_STATUS$ = "$APPROVE_STATUS$";
	public static final String $PAYMENT_STATUS$ = "$PAYMENT_STATUS$";
	public static final String $ENQUIRY_REPLAY$ = "$ENQUIRY_REPLAY$";
	public static final String $COMMENTS_START$ = "$COMMENTS_START$";
	public static final String $PACKAGE_CONTACT$ = "$PACKAGE_CONTACT$";
	public static final String $BILLING_ADDRESS$ = "$BILLING_ADDRESS$";
	public static final String $PAYMENT_COMMENTS$ = "$PAYMENT_COMMENTS$";
	public static final String $APPROVE_COMMENTS$ = "$APPROVE_COMMENTS$";
	public static final String $TRIP_COMMENTS_END$ = "$TRIP_COMMENTS_END$";
	public static final String $PACKAGE_PRIZE_DESC$ = "$PACKAGE_PRIZE_DESC$";
	public static final String $PACKAGE_CONDITIONS$ = "$PACKAGE_CONDITIONS$";
	public static final String $BILLING_POSTEL_CODE$ = "$BILLING_POSTEL_CODE$";
	public static final String $TRIP_COMMENTS_START$ = "$TRIP_COMMENTS_START$";

	public static HashMap<String, String> getForgotPasswordMapValue(
			String a_custEmail, String firstname, String lastName,
			String encodedPassword) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($EMAIL$, a_custEmail);
		nameValues.put($NEW_PASSWORD$, encodedPassword);
		nameValues.put($LASTNAME$, lastName);
		nameValues.put($FIRSTNAME$, firstname);

		return nameValues;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, String> getRegisterationMapValue(
			String a_email, String a_firstname, String a_lastName,
			String a_password) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($EMAIL$, a_email);
		nameValues.put($FIRSTNAME$, a_firstname);
		nameValues.put($LASTNAME$, a_lastName);
		nameValues.put($PASSWORD$, a_password);

		return nameValues;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap<String, String> getRegisterationMapValue(
			String a_email, String a_firstname, String a_lastName) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($EMAIL$, a_email);
		nameValues.put($FIRSTNAME$, a_firstname);
		nameValues.put($LASTNAME$, a_lastName);

		return nameValues;
	}

	public static HashMap<String, String> getTravelerDetailMapValue(
			TravellerDetail travellerDetail, String tripCode, String persion,
			String currencyOut) {
		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($FIRSTNAME$, travellerDetail.getFirstname());
		nameValues.put($LASTNAME$, travellerDetail.getLastname());
		nameValues.put($EMAIL$, travellerDetail.getEmail());
		nameValues.put($TRIPCODE$, tripCode);
		nameValues.put($TRIPDETAILS$, persion);
		nameValues.put($TOTALAMOUNT$, currencyOut);
		nameValues.put($TRAVELDATE$,
				CommonUtil.getDateFormat(travellerDetail.getTravelDate()));
		return nameValues;
	}

	public static HashMap<String, String> getTripDetailMapValue(
			TravellerDetail travellerDetail, String tripCode, String persion,
			String currencyOut, String conditions) {
		final HashMap<String, String> nameValues = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream("config.properties");
		try {
			prop.load(input);
			nameValues.put($VISIIT_ADDRESS$, prop.getProperty("visiitAddress"));
			nameValues.put($VISIIT_SATE$, prop.getProperty("visiitSate"));
			nameValues.put($VISIIT_COUNTRY$, prop.getProperty("visiitCountry"));

			nameValues.put($BILLING_ADDRESS$, travellerDetail.getAddress());
			nameValues.put($BILLING_CITY$, travellerDetail.getCity());
			nameValues.put($BILLING_POSTEL_CODE$,
					travellerDetail.getPostelCode());
			if (travellerDetail.getPaymentDate() != null) {
				nameValues.put($PAYMENT_DATE$, new SimpleDateFormat(
						"dd/MM/yyyy").format(travellerDetail.getPaymentDate()));
			} else {
				nameValues.put($PAYMENT_DATE$, new SimpleDateFormat(
						"dd/MM/yyyy").format(new Date()));
			}
			nameValues.put($FIRSTNAME$, travellerDetail.getFirstname());
			nameValues.put($LASTNAME$, travellerDetail.getLastname());
			nameValues.put($EMAIL$, travellerDetail.getEmail());
			nameValues.put($TRIPCODE$, tripCode);
			nameValues.put($TRIPDETAILS$, persion);
			nameValues.put($TOTALAMOUNT$, currencyOut);

			nameValues.put($PACKAGE_NAME$, travellerDetail.getPack()
					.getPkName());
			if (travellerDetail.getPack().getPkDays() != null) {
				nameValues.put($DAY$, travellerDetail.getPack().getPkDays()
						.toString());
			}
			if (travellerDetail.getPack().getPkNights() != null) {
				nameValues.put($NIGHT$, travellerDetail.getPack().getPkNights()
						.toString());
			}
			if (travellerDetail.getTravelDate() != null) {
				nameValues.put($TRAVELDATE$, new SimpleDateFormat("dd/MM/yyyy")
						.format(travellerDetail.getTravelDate()));
			} else {
				nameValues.put($TRAVELDATE$, ConstantUtil.EMPTY);
			}
			nameValues.put($PACKAGE_CONDITIONS$, conditions);
			nameValues.put($PACKAGE_PRIZE_DESC$, travellerDetail
					.getPackageCost().getPkcDescription());
			if (travellerDetail.getPack().getVendorInformation() != null) {
				nameValues.put($PACKAGE_CONTACT$, travellerDetail.getPack()
						.getVendorInformation().getViContactNo1());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nameValues;
	}

	public static HashMap<String, String> getCustomerLoginURLMapValue(
			HashMap<String, String> nameValues) {
		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream("config.properties");
		try {
			prop.load(input);
			nameValues.put($LOGIN_URL$, prop.getProperty("customerLoginUrl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nameValues;
	}

	public static HashMap<String, String> getAdminLoginURLMapValue(
			HashMap<String, String> nameValues) {
		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream("config.properties");
		try {
			prop.load(input);
			nameValues.put($LOGIN_URL$, prop.getProperty("adminLoginUrl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nameValues;
	}

	public static HashMap<String, String> getNewUserCreationMapValue(
			String userMail, String firstName, String lastName,
			String encodedPassword, String userId) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($EMAIL$, userMail);
		nameValues.put($NEW_PASSWORD$, encodedPassword);
		nameValues.put($LASTNAME$, lastName);
		nameValues.put($FIRSTNAME$, firstName);
		nameValues.put($USER_ID$, userId);
		return nameValues;
	}

	public static HashMap getPersonDetailMapValue(PersonDetail personDetail) {
		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($SALUTATION$, personDetail.getSalutation());
		nameValues.put($NAME$,
				personDetail.getFirstname() + " " + personDetail.getLastname());
		nameValues.put($AGE$, personDetail.getAge().toString());
		return nameValues;
	}

	public static HashMap<String, String> getEnquiryMapValue(
			CustomerEnquiry customerEnquiry) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($FIRSTNAME$, customerEnquiry.getEnqCustomerName());
		nameValues.put($QUERY_MESSAGE$, customerEnquiry.getEnqMessage());
		nameValues.put($ENQUIRY_CODE$, customerEnquiry.getEnqCode());
		nameValues.put($ENQUIRY_REPLAY$, customerEnquiry.getEnqReplay());
		nameValues.put($STATUS$, customerEnquiry.getEnqStatus());
		if (customerEnquiry.getTravellerDetail() != null) {
			nameValues.put($TRIP_COMMENTS_START$, ConstantUtil.EMPTY);
			nameValues.put($TRIP_COMMENTS_END$, ConstantUtil.EMPTY);
			nameValues.put($TRIPCODE$, customerEnquiry.getTravellerDetail()
					.getTripcode());
		} else {
			nameValues.put($TRIP_COMMENTS_START$,
					ConstantUtil.HTML_COMMENTS_START);
			nameValues.put($TRIP_COMMENTS_END$, ConstantUtil.HTML_COMMENTS_END);
		}
		nameValues.put($COMMENTS_START$, ConstantUtil.HTML_COMMENTS_START);
		nameValues.put($COMMENTS_END$, ConstantUtil.HTML_COMMENTS_END);
		return nameValues;
	}

	public static HashMap<String, String> getEnquiryPackageMapValue(
			CustomerEnquiry customerEnquiry) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		if (customerEnquiry != null) {
			nameValues.put($FIRSTNAME$, customerEnquiry.getEnqCustomerName());
			nameValues.put($QUERY_MESSAGE$, customerEnquiry.getEnqMessage());
			nameValues.put($ENQUIRY_CODE$, customerEnquiry.getEnqCode());
			nameValues.put($ENQUIRY_REPLAY$, customerEnquiry.getEnqReplay());
			nameValues.put($STATUS$, customerEnquiry.getEnqStatus());

			nameValues.put($PACKAGE_NAME$, customerEnquiry.getEnqPackageName());
			nameValues.put($ADUILTS$,
					customerEnquiry.getNoOfAdults() != null ? customerEnquiry
							.getNoOfAdults().toString()
							: ConstantUtil.STRING_ZERO);
			nameValues.put($CHILDREN$,
					customerEnquiry.getNoOfChildren() != null ? customerEnquiry
							.getNoOfChildren().toString()
							: ConstantUtil.STRING_ZERO);
			if (customerEnquiry.getEnqTripDate() != null) {
				nameValues.put($TRAVELDATE$, CommonUtil
						.getDateFormat(customerEnquiry.getEnqTripDate()));
			} else {
				nameValues.put($TRAVELDATE$, ConstantUtil.EMPTY);
			}

			nameValues.put($TRIP_COMMENTS_START$,
					ConstantUtil.HTML_COMMENTS_START);
			nameValues.put($TRIP_COMMENTS_END$, ConstantUtil.HTML_COMMENTS_END);
			nameValues.put($COMMENTS_START$, ConstantUtil.EMPTY);
			nameValues.put($COMMENTS_END$, ConstantUtil.EMPTY);
		}
		return nameValues;
	}

	public static HashMap<String, String> getTripResponce(
			TravellerDetail travellerDetail) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($FIRSTNAME$, travellerDetail.getFirstname());
		nameValues.put($TRIPCODE$, travellerDetail.getTripcode());
		nameValues.put($PAYMENT_STATUS$, travellerDetail.getPaymentStatus());
		nameValues.put($PAYMENT_COMMENTS$,
				travellerDetail.getPayementCommands() == null ? ""
						: travellerDetail.getPayementCommands());
		nameValues.put($APPROVE_STATUS$, travellerDetail.getApprovedStatus());
		nameValues.put($APPROVE_COMMENTS$,
				travellerDetail.getStatusCommands() == null ? ""
						: travellerDetail.getStatusCommands());
		return nameValues;
	}
	
	public static HashMap<String, String> getTripMapValue(
			TravellerDetail travellerDetail) {

		final HashMap<String, String> nameValues = new HashMap<String, String>();
		nameValues.put($FIRSTNAME$, travellerDetail.getFirstname());
		nameValues.put($TRIPCODE$, travellerDetail.getTripcode());
		return nameValues;
	}

}

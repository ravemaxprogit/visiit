package com.rave.visiit.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Encoder;

import com.rave.visiit.entity.CustomerEnquiry;

public class CommonUtil {
	

	public static String generateNextCode(String prefix, int maxId) {
		String nextId = "";
		/*
		 * if(maxId<10){ nextId = "0000"+maxId; }else if(maxId<100){ nextId =
		 * "000" + maxId; }else if(maxId<1000){ nextId = "00" + maxId; }else
		 * if(maxId<10000){ nextId = "0" + maxId; }else if(maxId>=10000){ nextId
		 * = "" + maxId; }
		 */
		nextId = "" + prefix + String.format("%05d", maxId);

		return nextId;
	}

	public static Date getExpiredDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		Date nextYear = cal.getTime();
		return nextYear;
	}

	public static List<Integer> covertStringToIntegerList(String str) {
		List<Integer> intList = null;
		if (StringUtils.isNotBlank(str)) {
			List<String> parentids = Arrays.asList(str.split("\\s*,\\s*"));
			intList = new ArrayList<Integer>();
			for (String id : parentids) {
				intList.add(Integer.parseInt(id));
			}
		}
		return intList;
	}

	public static Timestamp DateCalculation(Date todayDate,
			int expectedDateInInt) {
		Calendar autoAcceptTime = new GregorianCalendar();
		autoAcceptTime.setTime(todayDate);
		autoAcceptTime.add(Calendar.DATE, -expectedDateInInt);
		return new Timestamp(autoAcceptTime.getTimeInMillis());
	}

	public static Map JsonObjectToMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = JSontoList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = JsonObjectToMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static List JSontoList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = JSontoList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = JsonObjectToMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	public static String getLastForDigitsOfNowTimeStamp() {
		long l = System.currentTimeMillis();
		String s = "" + l;
		int stringLenght = s.length();
		int beginIndex = stringLenght - 4 <= 0 ? 1 : stringLenght - 4;
		return s.substring(beginIndex, stringLenght);
	}

	public static String encodeString(String param) {
		if (StringUtils.isBlank(param))
			return "";
		String bytesEncoded = new BASE64Encoder().encode(param.getBytes());
		return bytesEncoded;
	}

	public static String getDateFormat(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}

	public static void sendEnquiryMail(CustomerEnquiry customerEnquiry,
			String fileName, String[] recipitent, String subject)
			throws IOException, URISyntaxException {

		HashMap<String, String> nameValues = new HashMap<String, String>();

		String[] bccRecipients = {};
		MailUtil mailUtil = new MailUtil();

		if (customerEnquiry.getEnqTripDate() != null
				|| customerEnquiry.getNoOfAdults() != null
				|| customerEnquiry.getNoOfChildren() != null) {
			nameValues.putAll(ConstantMailMapperUtil
					.getEnquiryPackageMapValue(customerEnquiry));

		} else {

			nameValues.putAll(ConstantMailMapperUtil
					.getEnquiryMapValue(customerEnquiry));
		}

		String message = mailUtil.parseFile(fileName, nameValues);

		mailUtil.sendMail(recipitent, bccRecipients,
				subject, message);

	}

	public static String getPropertyValue(String property) throws IOException {
		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream(Constants.CONFIG_PROPERTIES);
		prop.load(input);
		String value = prop.getProperty(property);
		return value;
	}
	
	public static String getStringFromListCommaSeperatedWithQuotes(
			Collection<String> theList) {
		if (theList == null || theList.size() < 1){
			return "";
		}

		StringBuilder buf = new StringBuilder();
		int sepCount = 0;
		for (Iterator<String> iterator = theList.iterator(); iterator.hasNext();) {
			String str = iterator.next();
			buf.append("'");
			buf.append(escapeSqlLiterals(str));
			buf.append("'");
			sepCount++;
			if (!(sepCount >= theList.size())) {
				buf.append(Constants.SEPERATOR_COMMA);
			}
		}
		return buf.toString();
	}
	
	public final static String escapeSqlLiterals(final String input) {
		if (null == input) {
			return null;
		}
		return input.replace("'", "''");
	}
	
	public static <T extends Object> List<List<T>> split(List<T> list, int targetSize) {
	    List<List<T>> lists = new ArrayList<List<T>>();
	    for (int i = 0; i < list.size(); i += targetSize) {
	        lists.add(list.subList(i, Math.min(i + targetSize, list.size())));
	    }
	    return lists;
	}
	
	
	public static Date getDateFormat(Date date,int hours, int minits, int second) {
		Calendar calFromDate = Calendar.getInstance();
		calFromDate.setTime(date);
		calFromDate.set(Calendar.HOUR,hours);
		calFromDate.set(Calendar.MINUTE, minits);
		calFromDate.set(Calendar.SECOND, second);
		calFromDate.set(Calendar.HOUR_OF_DAY, hours);
		Date fromDate = calFromDate.getTime();
		return fromDate;
	}
}

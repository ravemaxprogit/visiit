package com.rave.visiit.util;

public class SMSUtil {
	
	private static String SMS_API_URL = "http://api.znisms.com/post/smsv3.asp";
	
	private static String SMS_API_USERID = "VISIIT";
	
	private static String SMS_API_KEY = "3c04643caf99d35e415af625717d680f";
	
	private static String SMS_API_HEAD = "VISIIT";
	
	private static String SMS_SENDERID = "VISIIT";
	
	private static String VISIIT_PHONE = "04424899990";
	
	private static String VISIIT_EMAIL = "support@visiit.com";	
	
	public static String registration(String customer){
		return "Dear "+customer+", Welcome to VISIIT! We promise you loads of fun to keep exploring our beautiful planet. Reach us at M:"+VISIIT_PHONE+",E:"+VISIIT_EMAIL;
	}
	
	public static String changePassword(String customer,String newPassword){
		return "Dear "+customer+", Your password has been reset as ("+newPassword+"). Reach us at "+VISIIT_PHONE+",E:"+VISIIT_EMAIL;
	}
	
	public static String packageBooked(String customer, String packageDetail){
		return "Dear "+customer+", Thanks for booking "+packageDetail+". Our trip expert will communicate you soon. Reach us at M:"+VISIIT_PHONE;
	}
	
	public static String packageVoucher(String customer, String documentTitle){
		return "Dear "+customer+", We will send you "+documentTitle+" shortly. All are delivered to your VISIIT panel and mail.  Reach us at M:"+VISIIT_PHONE;		
	}
	
	public static String packageEnquiryRecieved(String customer, String packageDetail, String queryId){
		return "Dear "+customer+", Thanks for your interest in "+packageDetail+" and your query Ref No is:"+queryId+". Our trip expert will assist you soon";
	}
	
	public static String packageEnquiryReplied(String customer, String enquiryDetail, String enquiryStatus, String action){
		return "Dear "+customer+", Your query on "+enquiryDetail+" has been "+enquiryStatus+". Kindly proceed for "+action+" from your mail or from your panel in VISIIT site.";
	}
	
	public static String alertAdminUser(String user,String query,String order){
		return "Dear "+user+", A new "+query+" ("+order+") has been raised. Kindly proceed for further processing.";
	}
	
	public static String sms(String message, String smsto){
		return SMS_API_URL+"?userid="+SMS_API_USERID+"&apikey="+SMS_API_KEY+"&message="+message+"&senderid="+SMS_SENDERID+"&sendto="+smsto;
	}
}

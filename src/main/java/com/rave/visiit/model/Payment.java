package com.rave.visiit.model;

public class Payment {
	
	private String key;
	private String txnid;
	private Double amount;
	private String productinfo;
	private String firstname;
	private String email;
	private String phone;
	private String hash;
	private String surl;
	private String furl;
	private String curl;
	private String status;
	
	public Payment(){}
	
	public Payment(String key, String txnid, Double amount, String productinfo,
			String firstname, String email, String phone, String hash,
			String surl, String furl, String curl) {
		super();
		this.key = key;
		this.txnid = txnid;
		this.amount = amount;
		this.productinfo = productinfo;
		this.firstname = firstname;
		this.email = email;
		this.phone = phone;
		this.hash = hash;
		this.surl = surl;
		this.furl = furl;
		this.curl = curl;
	}

	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getTxnid() {
		return txnid;
	}
	
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getProductinfo() {
		return productinfo;
	}
	
	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayUHash() {
		return key+"|"+txnid+"|"+amount+"|"+productinfo+"|"+firstname+"|"+email+"|||||||||||OmM6jqjz";
	}

	public String getMerchantHash() {
		
		return "OmM6jqjz|"+status+"|||||||||||"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|"+key;
	}
	
	@Override
	public String toString() {
		return "[{\"key\":\"" + key + "\",\"txnid\":\"" + txnid + "\",\"amount\":\""
				+ amount + "\",\"productinfo\":\"" + productinfo + "\",\"firstname\":\""
				+ firstname + "\",\"email\":\"" + email + "\",\"phone\":\"" + phone
				+ "\",\"hash\":\"" + hash + "\",\"surl\":\"" + surl + "\",\"furl\":\"" + furl
				+ "\",\"curl\":\"" + curl + "\"}]";
	}
	
}

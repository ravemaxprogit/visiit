package com.rave.visiit.model;

import java.util.Date;
import java.sql.Timestamp;


public class PackagePrice {

	private int validDays;
	private String pkcDescription;
	private int pkcCost;
	private Integer pkcId;
	private Integer pkId;
	private Date validFrom;
	private Date validTo;
	private int offerPrice;
	private Timestamp validFromTime;
    private Timestamp validToTime;

	public int getValidDays() {
		return validDays;
	}

	public void setValidDays(int validDays) {
		this.validDays = validDays;
	}

	public String getPkcDescription() {
		return pkcDescription;
	}

	public void setPkcDescription(String pkcDescription) {
		this.pkcDescription = pkcDescription;
	}

	public int getPkcCost() {
		return pkcCost;
	}

	public void setPkcCost(int pkcCost) {
		this.pkcCost = pkcCost;
	}

	public Integer getPkcId() {
		return pkcId;
	}

	public void setPkcId(Integer pkcId) {
		this.pkcId = pkcId;
	}

	public Integer getPkId() {
		return pkId;
	}

	public void setPkId(Integer pkId) {
		this.pkId = pkId;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public int getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(int offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Timestamp getValidFromTime() {
		return validFromTime;
	}

	public void setValidFromTime(Timestamp validFromTime) {
		this.validFromTime = validFromTime;
	}

	public Timestamp getValidToTime() {
		return validToTime;
	}

	public void setValidToTime(Timestamp validToTime) {
		this.validToTime = validToTime;
	}
	
	
}

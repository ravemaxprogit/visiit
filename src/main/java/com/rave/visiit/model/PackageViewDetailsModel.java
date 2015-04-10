package com.rave.visiit.model;

import java.util.List;

public class PackageViewDetailsModel {

	private Integer packageId;

	private String packageName;

	private String imageUrl;

	private Integer lowPrize;
	
	private Integer lowprizeId;

	private Integer days;

	private Integer nights;

	private String priceDescription;

	private List<PackageActivity> packageActivityModel;

	private List<PackageInclusionModel> packageInclusionModelList;

	private List<PackageExclusionModel> packageExclusionModelList;

	private List<PackagePrice> packagePriceList;

	private List<PackageConditionModel> packageConditionModelList;

	private List<HotelModel> hotelModelList;
	
	private List<String> locationReviewCode;
	
	private Integer offerPrice;
	
	private Integer originalPrice;
	
	public PackageViewDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PackageViewDetailsModel(Integer packageId, String packageName,Integer originalPrice,
			String imageUrl, Integer lowPrize, Integer days, Integer nights,
			String priceDescription, List<PackageActivity> packageActivityModel,
			List<PackageInclusionModel> packageInclusionModelList,
			List<PackageExclusionModel> packageExclusionModelList,
			List<PackagePrice> packagePriceList,Integer offerPrice,
			List<PackageConditionModel> packageConditionModelList,
			List<HotelModel> hotelModelList,List<String> locationReviewCode) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.imageUrl = imageUrl;
		this.lowPrize = lowPrize;
		this.days = days;
		this.nights = nights;
		this.priceDescription = priceDescription;
		this.packageActivityModel = packageActivityModel;
		this.packageInclusionModelList = packageInclusionModelList;
		this.packageExclusionModelList = packageExclusionModelList;
		this.packagePriceList = packagePriceList;
		this.packageConditionModelList = packageConditionModelList;
		this.hotelModelList = hotelModelList;
		this.locationReviewCode = locationReviewCode;
		this.offerPrice = offerPrice;
		this.originalPrice = originalPrice;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer integer) {
		this.packageId = integer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getLowPrize() {
		return lowPrize;
	}

	public void setLowPrize(Integer lowPrize) {
		this.lowPrize = lowPrize;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public List<PackageActivity> getPackageActivityModel() {
		return packageActivityModel;
	}

	public void setPackageActivityModel(
			List<PackageActivity> packageActivityModel) {
		this.packageActivityModel = packageActivityModel;
	}

	public List<PackageInclusionModel> getPackageInclusionModelList() {
		return packageInclusionModelList;
	}

	public void setPackageInclusionModelList(
			List<PackageInclusionModel> packageInclusionModelList) {
		this.packageInclusionModelList = packageInclusionModelList;
	}

	public List<PackageExclusionModel> getPackageExclusionModelList() {
		return packageExclusionModelList;
	}

	public void setPackageExclusionModelList(
			List<PackageExclusionModel> packageExclusionModelList) {
		this.packageExclusionModelList = packageExclusionModelList;
	}

	public List<PackagePrice> getPackagePriceList() {
		return packagePriceList;
	}

	public void setPackagePriceList(List<PackagePrice> packagePriceList) {
		this.packagePriceList = packagePriceList;
	}

	public List<PackageConditionModel> getPackageConditionModelList() {
		return packageConditionModelList;
	}

	public void setPackageConditionModelList(
			List<PackageConditionModel> packageConditionModelList) {
		this.packageConditionModelList = packageConditionModelList;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<HotelModel> getHotelModelList() {
		return hotelModelList;
	}

	public void setHotelModelList(List<HotelModel> hotelModelList) {
		this.hotelModelList = hotelModelList;
	}

	public List<String> getLocationReviewCode() {
		return locationReviewCode;
	}

	public void setLocationReviewCode(List<String> locationReviewCode) {
		this.locationReviewCode = locationReviewCode;
	}

	public Integer getLowprizeId() {
		return lowprizeId;
	}

	public void setLowprizeId(Integer lowprizeId) {
		this.lowprizeId = lowprizeId;
	}

	public Integer getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Integer offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	
	
}

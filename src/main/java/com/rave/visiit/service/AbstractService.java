package com.rave.visiit.service;

import com.rave.visiit.dao.CategoryDao;
import com.rave.visiit.dao.CountryDao;
import com.rave.visiit.dao.CustRegistrationDao;
import com.rave.visiit.dao.FeaturedpackageDao;
import com.rave.visiit.dao.HotelContactDao;
import com.rave.visiit.dao.HotelDao;
import com.rave.visiit.dao.PackageAttractionDao;
import com.rave.visiit.dao.PackageCategoryDao;
import com.rave.visiit.dao.PackageConditionDao;
import com.rave.visiit.dao.PackageCostDao;
import com.rave.visiit.dao.PackageDao;
import com.rave.visiit.dao.PackageExclusionDao;
import com.rave.visiit.dao.PackageHotelDao;
import com.rave.visiit.dao.PackageInclusionDao;
import com.rave.visiit.dao.PackageItineraryDao;
import com.rave.visiit.dao.PackageLocationDao;
import com.rave.visiit.dao.RoleMasterDao;
import com.rave.visiit.dao.VendorDao;

public abstract class AbstractService {

	private CountryDao countryDao;

	private VendorDao vendorDao;

	private RoleMasterDao roleMasterDao;
	
	private HotelDao hotelDao;
	
	private HotelContactDao hotelContactDao;
	
	private CategoryDao categoryDao;
	
	private CustRegistrationDao custRegistrationDao;
	
	private PackageDao packageDao;
	
	private PackageCostDao packageCostDao;
	
	private PackageItineraryDao packageItineraryDao;
	
	private PackageAttractionDao packageAttractionDao;
	
	private PackageHotelDao packageHotelDao;
	
	private PackageInclusionDao packageInclusionDao;
	
	private PackageExclusionDao packageExclusionDao;
	
	private PackageLocationDao packageLocationDao;
	
	private  FeaturedpackageDao featuredpackageDao;
	
	private PackageConditionDao packageConditionDao;
	
	private PackageCategoryDao packageCategoryDao;
	
	public RoleMasterDao getRoleMasterDao() {
		return roleMasterDao;
	}

	public void setRoleMasterDao(RoleMasterDao roleMasterDao) {
		this.roleMasterDao = roleMasterDao;
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public VendorDao getVendorDao() {
		return vendorDao;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	public HotelDao getHotelDao() {
		return hotelDao;
	}

	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}

	public HotelContactDao getHotelContactDao() {
		return hotelContactDao;
	}

	public void setHotelContactDao(HotelContactDao hotelContactDao) {
		this.hotelContactDao = hotelContactDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public CustRegistrationDao getCustRegistrationDao() {
		return custRegistrationDao;
	}

	public void setCustRegistrationDao(CustRegistrationDao custRegistrationDao) {
		this.custRegistrationDao = custRegistrationDao;
	}

	public PackageDao getPackageDao() {
		return packageDao;
	}

	public void setPackageDao(PackageDao packageDao) {
		this.packageDao = packageDao;
	}

	public PackageItineraryDao getPackageItineraryDao() {
		return packageItineraryDao;
	}

	public void setPackageItineraryDao(PackageItineraryDao packageItineraryDao) {
		this.packageItineraryDao = packageItineraryDao;
	}

	public PackageAttractionDao getPackageAttractionDao() {
		return packageAttractionDao;
	}

	public void setPackageAttractionDao(PackageAttractionDao packageAttractionDao) {
		this.packageAttractionDao = packageAttractionDao;
	}

	public PackageInclusionDao getPackageInclusionDao() {
		return packageInclusionDao;
	}

	public void setPackageInclusionDao(PackageInclusionDao packageInclusionDao) {
		this.packageInclusionDao = packageInclusionDao;
	}

	public PackageExclusionDao getPackageExclusionDao() {
		return packageExclusionDao;
	}

	public void setPackageExclusionDao(PackageExclusionDao packageExclusionDao) {
		this.packageExclusionDao = packageExclusionDao;
	}

	public PackageCostDao getPackageCostDao() {
		return packageCostDao;
	}

	public void setPackageCostDao(PackageCostDao packageCostDao) {
		this.packageCostDao = packageCostDao;
	}

	public PackageHotelDao getPackageHotelDao() {
		return packageHotelDao;
	}

	public void setPackageHotelDao(PackageHotelDao packageHotelDao) {
		this.packageHotelDao = packageHotelDao;
	}

	public PackageLocationDao getPackageLocationDao() {
		return packageLocationDao;
	}

	public void setPackageLocationDao(PackageLocationDao packageLocationDao) {
		this.packageLocationDao = packageLocationDao;
	}

	public FeaturedpackageDao getFeaturedpackageDao() {
		return featuredpackageDao;
	}

	public void setFeaturedpackageDao(FeaturedpackageDao featuredpackageDao) {
		this.featuredpackageDao = featuredpackageDao;
	}

	public PackageConditionDao getPackageConditionDao() {
		return packageConditionDao;
	}

	public void setPackageConditionDao(PackageConditionDao packageConditionDao) {
		this.packageConditionDao = packageConditionDao;
	}

	public PackageCategoryDao getPackageCategoryDao() {
		return packageCategoryDao;
	}

	public void setPackageCategoryDao(PackageCategoryDao packageCategoryDao) {
		this.packageCategoryDao = packageCategoryDao;
	}
	
	
}
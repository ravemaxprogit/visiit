package com.rave.visiit.dao;

import java.util.List;
import java.util.Map;

import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.CronJobAudit;
import com.rave.visiit.entity.CronJobException;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.PackageView;

public interface PackageDao {
	
	public Package saveorupdate(Package pack) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<Package> getAll() throws Exception;

	public Package get(Integer id) throws Exception;
	
	public String getMaxCode() throws Exception;

	public List<Package> getFeatured() throws Exception;

	public List<Package> getByCategory(Category cat) throws Exception;
	
	public Package getPackageByCode(String code) throws Exception;
	
	public List<Package> getAllPackagesByType(String type) throws Exception;
	
	public List<Package> getAllPackages() throws Exception;
	
	public List<PackageCost> getAllHotDealPackages(String type) throws Exception;
	
	public Long getPackageCountByPackLoaction(Integer packageLocationId) throws Exception;
	
	public Long getPackageCountByCategory(Integer categoryId) throws Exception;
	
	public boolean getPackagevalid(Integer categoryId) throws Exception;
	
	public <T> List getTableRefForField(String tableName, String primaryKeyId);
	
	public boolean checkFieldValueReference(Integer id, List list);
	
	public CronJobAudit saveCronJobAudit(CronJobAudit cronJobAudit) throws Exception;
	
	public CronJobException saveCronJobAuditException(CronJobException cronJobException);

	public List<Package> getAllPackageAuto(String packStr);
	
	public List<PackageView> searchPackages(String searchString) throws Exception;
		
	public List<Package> searchPackagesByName(String searchString) throws Exception;
	
	public List<Package> getPackByCategory(Category cat) throws Exception;
		
	/*	public List<PackageInclusion> searchPackagesInInclusions(String searchString) throws Exception;
		
		public List<PackageExclusion> searchPackagesInExclusions(String searchString) throws Exception;
		
		public List<PackageItinerary> searchPackagesInActivities(String searchString) throws Exception;*/
		
    public List<Package> searchAllPackages(String searchString) throws Exception;
		
	public Map<String,Integer> getPackCount() throws Exception;
	
}

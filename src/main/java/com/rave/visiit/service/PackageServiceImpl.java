package com.rave.visiit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.CronJobAudit;
import com.rave.visiit.entity.CronJobException;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.PackageView;

@Service
public class PackageServiceImpl extends AbstractService implements
		PackageService {

	@Override
	@Transactional
	public Package saveorupdate(Package pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().delete(id);
	}

	@Override
	@Transactional
	public List<Package> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().getAll();
	}

	@Override
	public Package get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().get(id);
	}

	@Override
	@Transactional
	public String getMaxCode() throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().getMaxCode();
	}

	@Override
	@Transactional
	public List<Package> getFeatured() throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().getFeatured();
	}

	@Override
	@Transactional
	public List<Package> getByCategory(Category cat) throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().getByCategory(cat);
	}
	
	@Override
	@Transactional
	public Package getPackageByCode(String code) throws Exception {
		return getPackageDao().getPackageByCode(code);
	}
	
	@Override
	@Transactional
	public List<Package> getAllPackagesByType(String type) throws Exception {
		return getPackageDao().getAllPackagesByType(type);
	}
	
	@Override
	@Transactional
	public List<Package> getAllPackages() throws Exception {
		return getPackageDao().getAllPackages();
	}
	
	@Override
	@Transactional
	public List<PackageCost> getAllHotDealPackages(String type) throws Exception {
		return getPackageDao().getAllHotDealPackages(type);
	}
	
	@Override
	@Transactional
	public Long getPackageCountByPackLoaction(Integer packageLocationId) throws Exception {
		return getPackageDao().getPackageCountByPackLoaction(packageLocationId);
	}
	
	@Override
	@Transactional
	public Long getPackageCountByCategory(Integer categoryId) throws Exception {
		return getPackageDao().getPackageCountByCategory(categoryId);
	}
	
	@Override
	@Transactional
	public boolean getPackagevalid(Integer packageId) throws Exception {
		List list = getPackageDao().getTableRefForField("package", "pk_id");
		return getPackageDao().checkFieldValueReference(packageId, list);
	}
	
	@Override
	@Transactional
	public CronJobAudit saveCronJobAudit(CronJobAudit cronJobAudit) throws Exception {
		return getPackageDao().saveCronJobAudit(cronJobAudit);
	}
	
	@Override
	@Transactional
	public CronJobException saveCronJobAuditException(CronJobException cronJobException) {
		return getPackageDao().saveCronJobAuditException(cronJobException);
	}

	@Override
	@Transactional
	public List<Package> getAllPackageAuto(String packStr) {
		return getPackageDao().getAllPackageAuto(packStr);
	}
	
	@Override
	@Transactional
	public List<PackageView> searchPackages(String searchString) throws Exception {
		return getPackageDao().searchPackages(searchString);
	}
		
	@Override
	@Transactional
	public List<Package> searchPackagesByName(String searchString) throws Exception {
		return getPackageDao().searchPackagesByName(searchString);
	}
	
	@Override
	@Transactional
	public List<Package> searchAllPackages(String searchString) throws Exception {
		return getPackageDao().searchAllPackages(searchString);
	}
	/*@Override
	@Transactional
	public List<PackageInclusion> searchPackagesInInclusions(String searchString) throws Exception {
		return getPackageDao().searchPackagesInInclusions(searchString);
	}
	
	@Override
	@Transactional
	public List<PackageExclusion> searchPackagesInExclusions(String searchString) throws Exception {
		return getPackageDao().searchPackagesInExclusions(searchString);
	}
	
	@Override
	@Transactional
	public List<PackageItinerary> searchPackagesInActivities(String searchString) throws Exception {
		return getPackageDao().searchPackagesInActivities(searchString);
	}*/

	@Override
	@Transactional
	public <T> List getTableRefForField(String tableName, String primaryKeyId) {
		// TODO Auto-generated method stub
		return getPackageDao().getTableRefForField(tableName, primaryKeyId);
	}

	@Override
	@Transactional
	public boolean checkFieldValueReference(Integer id, List list) {
		// TODO Auto-generated method stub
		return getPackageDao().checkFieldValueReference(id, list);
	}

	@Override
	@Transactional
	public List<Package> getPackByCategory(Category cat) throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().getPackByCategory(cat);
	}

	@Override
	@Transactional
	public Map<String, Integer> getPackCount() throws Exception {
		// TODO Auto-generated method stub
		return getPackageDao().getPackCount();
	}
}

package com.rave.visiit.quartz;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.rave.visiit.entity.CronJobAudit;
import com.rave.visiit.entity.CronJobException;
import com.rave.visiit.entity.PackageConditions;
import com.rave.visiit.entity.PackageCost;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageHotel;
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageItinerary;
import com.rave.visiit.service.PackageConditionService;
import com.rave.visiit.service.PackageCostService;
import com.rave.visiit.service.PackageExclusionService;
import com.rave.visiit.service.PackageHotelService;
import com.rave.visiit.service.PackageInclusionService;
import com.rave.visiit.service.PackageItineraryService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;
/**
 * 
 * Created cron job to updated the package validation.
 * 1.package should have at least one Activity
 * 2.package should have at least one price with valid date
 * 3.package should have at least one hotels
 * 4.package should have at least one inclusion
 * 5.package should have at least one exclusion
 * 6.package should have at least one condition
 * then only its valid package to show customer
 * 
 *
 */
public class PackageUpdationCronJob extends QuartzJobBean{
	
	private static Logger logger = Logger.getLogger(PackageUpdationCronJob.class);
	
	private static String jobName = Constants.PACKAGE_UPDATION_CRON_JOB;
	
	private PackageService packageService;
	
	private PackageCostService packageCostService;
	 
	private PackageConditionService packageConditionService;
	
	private PackageItineraryService packageItineraryService;
	
	private PackageHotelService packageHotelService;
	
	private PackageInclusionService packageInclusionService;
	
	private PackageExclusionService packageExclusionService;
	
	public PackageCostService getPackageCostService() {
		return packageCostService;
	}

	public void setPackageCostService(PackageCostService packageCostService) {
		this.packageCostService = packageCostService;
	}

	public PackageConditionService getPackageConditionService() {
		return packageConditionService;
	}

	public void setPackageConditionService(
			PackageConditionService packageConditionService) {
		this.packageConditionService = packageConditionService;
	}

	public PackageItineraryService getPackageItineraryService() {
		return packageItineraryService;
	}

	public void setPackageItineraryService(
			PackageItineraryService packageItineraryService) {
		this.packageItineraryService = packageItineraryService;
	}

	public PackageHotelService getPackageHotelService() {
		return packageHotelService;
	}

	public void setPackageHotelService(PackageHotelService packageHotelService) {
		this.packageHotelService = packageHotelService;
	}

	public PackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}
	
	public PackageInclusionService getPackageInclusionService() {
		return packageInclusionService;
	}

	public void setPackageInclusionService(
			PackageInclusionService packageInclusionService) {
		this.packageInclusionService = packageInclusionService;
	}

	public PackageExclusionService getPackageExclusionService() {
		return packageExclusionService;
	}

	public void setPackageExclusionService(
			PackageExclusionService packageExclusionService) {
		this.packageExclusionService = packageExclusionService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		long startTime = System.currentTimeMillis();
		logger.info("Cron job invocation for PackageUpdationCronJob, processing start time : "+ new Timestamp(startTime));
		CronJobAudit cronJobAudit = null;
		try {
			String cronEnabled = CommonUtil.getPropertyValue(Constants.CRON_JOB_PACKAGE_UPDATION);
			if(cronEnabled != null && Constants.TRUE_STR.equalsIgnoreCase(cronEnabled)){
				cronJobAudit = populateCronJobAudit();
				cronJobAudit = packageService.saveCronJobAudit(cronJobAudit);
				List<com.rave.visiit.entity.Package> packages = packageService.getAll();
				if(packages != null && !packages.isEmpty()){
					for (com.rave.visiit.entity.Package pack : packages) {
						boolean isValid = checkIsPackageValid(pack);
						pack.setPkModifiedBy(Constants.SYSTEM);
						pack.setPkModifiedOn(new Timestamp(System.currentTimeMillis()));
						pack.setIsValid(isValid);
						packageService.saveorupdate(pack);
					}
			 }
				cronJobAudit.setEndedOn(new Timestamp(System.currentTimeMillis()));
				cronJobAudit = packageService.saveCronJobAudit(cronJobAudit);
		  }
			
		} catch (Exception e) {
			CronJobException cronJobException = populateCronJobException(cronJobAudit.getJobId(), e.getMessage());
			packageService.saveCronJobAuditException(cronJobException);
			e.printStackTrace();
		}
		logger.info("Cron job invocation for PackageUpdationCronJob, processing finished in "+ (System.currentTimeMillis() - startTime) + " ms");
	}
	
	/**
	 * populates cron job audit data
	 * 
	 * @return CronJobAudit
	 */
	private CronJobAudit populateCronJobAudit() {
		CronJobAudit cronJobAudit = new CronJobAudit();
		cronJobAudit.setJobName(jobName);
		cronJobAudit.setStartedOn(new Timestamp(System.currentTimeMillis()));
		return cronJobAudit;
	}
	
	/**
	 * populates cron job exception audit
	 * 
	 * @param jobId
	 * @param oid
	 * @param errorMessage
	 * @return CronJobException
	 */
	private CronJobException populateCronJobException(Integer jobId, String errorMessage) {
		CronJobException cronJobException = new CronJobException();
		cronJobException.setJobId(jobId);
		cronJobException.setFailureCause(errorMessage);
		return cronJobException;
	}
	
	private boolean checkIsPackageValid(com.rave.visiit.entity.Package pack){
		boolean isValid = true;
		try {
			
			if(pack.getImageUrl() == null || pack.getImageUrl().isEmpty()){
				isValid = false;
				return isValid;
			}
			List<PackageCost> cost = packageCostService.getAllPrice(pack.getPkId());
			if(cost == null || cost.isEmpty()){
				isValid = false;
				return isValid;
			}
			List<PackageConditions> conditions = packageConditionService.getAll(pack.getPkId());
			if(conditions == null || conditions.isEmpty()){
				isValid = false;
				return isValid;
			}
			List<PackageHotel> hotels = packageHotelService.getAll(pack.getPkId());
			if(hotels == null || hotels.isEmpty()){
				isValid = false;
				return isValid;
			}
			List<PackageItinerary> activities = packageItineraryService.getAll(pack.getPkId());
			if(activities == null || activities.isEmpty()){
				isValid = false;
				return isValid;
			}
			List<PackageInclusion> inclusions = packageInclusionService.getValideInclusionByPackage(pack.getPkId());
			if(inclusions == null || inclusions.isEmpty()){
				isValid = false;
				return isValid;
			}
			List<PackageExclusion> exclusions = packageExclusionService.getValideExclusionByPackage(pack.getPkId());
			if(exclusions == null || exclusions.isEmpty()){
				isValid = false;
				return isValid;
			}
		}catch(Exception e){
			logger.info("Error occur while checking valid packages " + e.getMessage());
		}
		return isValid;
	}
}

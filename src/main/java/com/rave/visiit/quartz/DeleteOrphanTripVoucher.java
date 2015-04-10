package com.rave.visiit.quartz;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.rave.visiit.entity.CronJobAudit;
import com.rave.visiit.entity.CronJobException;
import com.rave.visiit.entity.TripVoucher;
import com.rave.visiit.service.FileUploadService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;

public class DeleteOrphanTripVoucher extends QuartzJobBean{
	
	private static Logger logger = Logger.getLogger(DeleteOrphanTripVoucher.class);
	
	private static String jobName = Constants.DELETE_ORPHAN_TRIP_VOUCHER_CRON_JOB;
	
	private FileUploadService fileUploadService;
	
	private PackageService packageService;
	
	public FileUploadService getFileUploadService() {
		return fileUploadService;
	}

	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	
	public PackageService getPackageService() {
		return packageService;
	}


	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		long startTime = System.currentTimeMillis();
		logger.info("Cron job invocation for DeleteOrphanTripVoucher, processing start time : "+ new Timestamp(startTime));
		CronJobAudit cronJobAudit = populateCronJobAudit();
		try {
			String cronEnabled = CommonUtil.getPropertyValue(Constants.CRON_JOB_DELETE_ORPHAN_TRIP_VOUCHER);
			if(cronEnabled != null && Constants.TRUE_STR.equalsIgnoreCase(cronEnabled)){
				cronJobAudit = packageService.saveCronJobAudit(cronJobAudit);
				List<TripVoucher> tripVouchers = fileUploadService.getAllDeleteTripVoucher();
				if(tripVouchers != null && !tripVouchers.isEmpty()){
					for (TripVoucher voucher : tripVouchers) {
							fileUploadService.deleteTripVoucherById(voucher.getVoucherId());
						}
					}
				cronJobAudit.setEndedOn(new Timestamp(System.currentTimeMillis()));
				cronJobAudit = packageService.saveCronJobAudit(cronJobAudit);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			CronJobException cronJobException = populateCronJobException(cronJobAudit.getJobId(), e.getMessage());
			packageService.saveCronJobAuditException(cronJobException);
		}
		logger.info("Cron job invocation for DeleteOrphanTripVoucher, processing finished in "+ (System.currentTimeMillis() - startTime) + " ms");
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

}

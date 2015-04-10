package com.rave.visiit.quartz;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.rave.visiit.entity.CronJobAudit;
import com.rave.visiit.entity.CronJobException;
import com.rave.visiit.entity.NewsLetter;
import com.rave.visiit.entity.NewsLetterSubscriber;
import com.rave.visiit.service.FileUploadService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.MailUtil;

public class PublishNewsLetterCronJob  extends QuartzJobBean{
	
	private static Logger logger = Logger.getLogger(PublishNewsLetterCronJob.class);
	
	private static String jobName = Constants.PUBLISH_NEWS_LETTER;
	
	private PackageService packageService;
	
	private FileUploadService fileUploadService;
	
	public PackageService getPackageService() {
		return packageService;
	}
	public void setPackageService(PackageService packageService) {
		this.packageService = packageService;
	}

	public FileUploadService getFileUploadService() {
		return fileUploadService;
	}
	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		MailUtil mailUtil = new MailUtil();
		long startTime = System.currentTimeMillis();
		logger.info("Cron job invocation for PublishNewsLetterCronJob, processing start time : "+ new Timestamp(startTime));
		CronJobAudit cronJobAudit = null;
		try {
			String cronEnabled = CommonUtil.getPropertyValue(Constants.CRON_JOB_PUBLISH_NEWSLETTER_UPDATION);
			if(cronEnabled != null && Constants.TRUE_STR.equalsIgnoreCase(cronEnabled)){
				cronJobAudit = populateCronJobAudit();
				cronJobAudit = packageService.saveCronJobAudit(cronJobAudit);
				List<NewsLetterSubscriber> subscribers = fileUploadService.getAllSubscriberByIsSent();
				if(subscribers != null && !subscribers.isEmpty()){
					NewsLetter newsLetter = fileUploadService.getNewsLetter();
					if(newsLetter != null){
						String content = new String(newsLetter.getData());
						List<List<NewsLetterSubscriber>> splitedList = CommonUtil.split(subscribers, 100);
						for (List<NewsLetterSubscriber> list : splitedList) {
							String[] recipitent = new String[splitedList.size()];
							int i = 0;
							for (NewsLetterSubscriber newsLetterSubscriber : list) {
								recipitent[i] = newsLetterSubscriber.getSubscriberEmailId();
								i=i+1;
								newsLetterSubscriber.setIsSent(Boolean.TRUE);
								newsLetterSubscriber.setSentBy(Constants.SYSTEM);
								newsLetterSubscriber.setSentdOn(new Timestamp(System.currentTimeMillis()));
								fileUploadService.saveSubscriber(newsLetterSubscriber);
							}
							mailUtil.sendNewsLetter(null, recipitent, Constants.REG_VISIIT_NEWS_LETTER , content);
						}
						//fileUploadService.updateNewsLetterSubscriber();
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
		logger.info("Cron job invocation for PublishNewsLetterCronJob, processing finished in "+ (System.currentTimeMillis() - startTime) + " ms");
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

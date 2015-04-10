package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;


@Entity
@Table(name = "cron_job_exception", catalog = Constants.DB_CATELOG)
public class CronJobException  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8364089267697898118L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "exception_id", unique = true, nullable = false)
	private Integer exceptionId;

	@Column(name = "job_id")
	private Integer jobId;

	@Column(name = "failure_cause")
	private String failureCause;
	
	public Integer getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}

}

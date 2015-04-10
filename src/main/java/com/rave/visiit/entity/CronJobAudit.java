package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "cron_job_audit", catalog = Constants.DB_CATELOG)
public class CronJobAudit implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3731589768664488190L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "job_id", unique = true, nullable = false)
	private Integer jobId;
	
	@Column(name = "job_name")
	private String jobName;

	@Column(name = "started_on")
	private Timestamp startedOn;

	@Column(name = "ended_on")
	private Timestamp endedOn;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public Timestamp getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(Timestamp startedOn) {
		this.startedOn = startedOn;
	}
	
	public Timestamp getEndedOn() {
		return endedOn;
	}

	public void setEndedOn(Timestamp endedOn) {
		this.endedOn = endedOn;
	}
	
}

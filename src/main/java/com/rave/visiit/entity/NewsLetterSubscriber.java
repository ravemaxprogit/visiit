package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "newsletter_subscriber", catalog = Constants.DB_CATELOG)
public class NewsLetterSubscriber implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String subscriberEmailId;
	
	private Integer subId;
	
	private String sentBy;
	
	private Date sentdOn;
	
	private Boolean isSent = Boolean.FALSE;
	
	private String subscribedBy;
	
	private Date subscribedOn;
	
	@Column(name = "subscriber_email", unique = true, nullable = false)
	public String getSubscriberEmailId() {
		return subscriberEmailId;
	}

	public void setSubscriberEmailId(String subscriberEmailId) {
		this.subscriberEmailId = subscriberEmailId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sub_id", unique = true, nullable = false)
	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	
	@Column(name = "sent_by", length = 50)
	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sent_on", length = 0)
	public Date getSentdOn() {
		return sentdOn;
	}

	public void setSentdOn(Date sentdOn) {
		this.sentdOn = sentdOn;
	}
	
	@Column(name = "is_sent")
	public Boolean getIsSent() {
		return isSent;
	}

	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}
	
	@Column(name = "subscribed_by", nullable = false, length = 50)
	public String getSubscribedBy() {
		return subscribedBy;
	}

	public void setSubscribedBy(String subscribedBy) {
		this.subscribedBy = subscribedBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subscribed_on", nullable = false, length = 0)
	public Date getSubscribedOn() {
		return subscribedOn;
	}
	
	
	public void setSubscribedOn(Date subscribedOn) {
		this.subscribedOn = subscribedOn;
	}
	
}

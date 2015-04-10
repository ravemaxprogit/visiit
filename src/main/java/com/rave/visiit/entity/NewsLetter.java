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
@Table(name = "news_letter", catalog = Constants.DB_CATELOG)
public class NewsLetter implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;

	private byte[] data;

	private String mimeType;
	
	private Integer letterId;
	
	private String uploadedBy;
	
	private Date uploadedOn;
	
	@Column(name = "file_name", nullable = false, length = 30)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "data")
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name = "mime_type", nullable = false, length = 30)
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "letter_id", unique = true, nullable = false)
	public Integer getLetterId() {
		return letterId;
	}

	public void setLetterId(Integer letterId) {
		this.letterId = letterId;
	}
	
	@Column(name = "uploaded_by", nullable = false, length = 50)
	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "uploaded_on", nullable = false, length = 0)
	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}
	
}

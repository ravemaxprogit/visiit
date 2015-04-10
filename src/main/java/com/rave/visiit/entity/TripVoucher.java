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
@Table(name = "trip_voucher", catalog = Constants.DB_CATELOG)
public class TripVoucher implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;

	private byte[] data;

	private String mimeType;
	
	private String tripCode;
	
	private Integer voucherId;
	
	private String uploadedBy;
	
	private Date uploadedOn;
	
	private String modifiedBy;
	
	private Date modifiedOn;
	
	private Boolean isDeleted = Boolean.FALSE;
	
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
	
	@Column(name = "trip_code", nullable = false, length = 50)
	public String getTripCode() {
		return tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "voucher_id", unique = true, nullable = false)
	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
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
	
	@Column(name = "modified_by", nullable = false, length = 50)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on", nullable = false, length = 0)
	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	@Column(name = "is_deleted", nullable = false, length = 6)
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	
	@Column(name = "is_deleted", nullable = false, length = 6)
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}

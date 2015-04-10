package com.rave.visiit.entity;
// default package
// Generated 29 Dec, 2014 3:09:30 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.rave.visiit.util.Constants;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = Constants.DB_CATELOG)
public class Category implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer catId;
	private String catTitle;
	private int catLevel;
	private String catDescription;
	private Boolean catIsactive  = Boolean.TRUE;
	private String catFilePath;
	private Timestamp catModifiedOn;
	private String catModifiedBy;
	private Integer catSeqId;
	private String imageUrl;
	private String iconUrl;
	private Integer seqId;
	private Boolean isDeleted = Boolean.FALSE;
	@Transient
	private String activeModel;
//	private Set<Package> packages = new HashSet<Package>(0);

	public Category() {
	}

	public Category(String catTitle, int catLevel, String catDescription,
			Boolean catIsactive, String catFilePath, Timestamp catModifiedOn,Integer catSeqId,String imageUrl,String iconURL,Integer seqId,Boolean isDeleted) {
		this.catTitle = catTitle;
		this.catLevel = catLevel;
		this.catDescription = catDescription;
		this.catIsactive = catIsactive;
		this.catFilePath = catFilePath;
		this.catModifiedOn = catModifiedOn;
		this.catSeqId = catSeqId;
		this.imageUrl = imageUrl;
		this.seqId = seqId;
		this.isDeleted = isDeleted;
	}

	public Category(String catTitle, int catLevel, String catDescription,
			Boolean catIsactive, String catFilePath, Timestamp catModifiedOn,
			String catModifiedBy, Set<Package> packages,Integer catSeqId,String imageUrl,String iconURL,Integer seqId,Boolean isDeleted) {
		this.catTitle = catTitle;
		this.catLevel = catLevel;
		this.catDescription = catDescription;
		this.catIsactive = catIsactive;
		this.catFilePath = catFilePath;
		this.catModifiedOn = catModifiedOn;
		this.catModifiedBy = catModifiedBy;
		this.catSeqId = catSeqId;
		this.imageUrl = imageUrl;
		this.iconUrl = iconURL;
		this.seqId = seqId;
		this.isDeleted = isDeleted;
//		this.packages = packages;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cat_id", unique = true, nullable = false)
	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	@Column(name = "cat_title", nullable = false, length = 30)
	public String getCatTitle() {
		return this.catTitle;
	}

	public void setCatTitle(String catTitle) {
		this.catTitle = catTitle;
	}

	@Column(name = "cat_level", nullable = false)
	public int getCatLevel() {
		return this.catLevel;
	}

	public void setCatLevel(int catLevel) {
		this.catLevel = catLevel;
	}

	@Column(name = "cat_description", nullable = false, length = 50)
	public String getCatDescription() {
		return this.catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}

	@Column(name = "cat_is_active", nullable = false, length = 1)
	public Boolean getCatIsactive() {
		return this.catIsactive;
	}

	public void setCatIsactive(Boolean catIsactive) {
		this.catIsactive = catIsactive;
	}

	@Column(name = "cat_file_path", nullable = false, length = 300)
	public String getCatFilePath() {
		return this.catFilePath;
	}

	public void setCatFilePath(String catFilePath) {
		this.catFilePath = catFilePath;
	}

	@Column(name = "cat_seq_id", length = 30)
	public Integer getCatSeqId() {
		return catSeqId;
	}

	public void setCatSeqId(Integer catSeqId) {
		this.catSeqId = catSeqId;
	}
	
	@Column(name = "image_url", length = 255)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		if(imageUrl != null){
			this.imageUrl = imageUrl.replace("\\", "/");
		}else {this.imageUrl = imageUrl;}
	}
	
	@Column(name = "icon_url", length = 255)
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		if(iconUrl != null){
			this.iconUrl = iconUrl.replace("\\", "/");
		}else {this.iconUrl = iconUrl;}
	}
	@Column(name = "seq_id", length = 10)
	public Integer getSeqId() {
		return seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	
	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cat_modified_on", nullable = false, length = 0)
	public Timestamp getCatModifiedOn() {
		return catModifiedOn;
	}

	public void setCatModifiedOn(Timestamp catModifiedOn) {
		this.catModifiedOn = catModifiedOn;
	}
	
	@Column(name = "cat_modified_by", nullable = false, length = 30)
	public String getCatModifiedBy() {
		return catModifiedBy;
	}

	public void setCatModifiedBy(String catModifiedBy) {
		this.catModifiedBy = catModifiedBy;
	}
	
	
	@Transient
	public String getActiveModel() {
		if(catIsactive != null && catIsactive){
			this.activeModel = "Y";
		} else {
			this.activeModel = "N";
		}
		return activeModel;
	}

	public void setActiveModel(String activeModel) {
			this.activeModel = activeModel;
	}
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
//	public Set<Package> getPackages() {
//		return this.packages;
//	}
//
//	public void setPackages(Set<Package> packages) {
//		this.packages = packages;
//	}

}

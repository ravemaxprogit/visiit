package com.rave.visiit.entity;
// default pack
// Generated 2 Feb, 2015 3:59:10 PM by Hibernate Tools 3.4.0.CR1


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.rave.visiit.util.Constants;

/**
 * PackageLocation generated by hbm2java
 */
@Entity
@Table(name="package_location"
    ,catalog=Constants.DB_CATELOG
)
public class PackageLocation  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pklId;
     private int pklLocation;
     private int pklPkId;
     private int pklType;
     private String pklDescription;
     private Boolean pklIsactive = Boolean.TRUE;
     private Date pklModifiedOn;
     private String pklModifiedBy;
     private String activeModel;
     private Boolean isDeleted = Boolean.TRUE;
    public PackageLocation() {
    }

    public PackageLocation(int pklLocation, int pklPkId,int pklType, String pklDescription, Boolean pklIsactive, Date pklModifiedOn, String pklModifiedBy) {
       this.pklLocation = pklLocation;
       this.pklPkId = pklPkId;
       this.pklType = pklType;
       this.pklDescription = pklDescription;
       this.pklIsactive = pklIsactive;
       this.pklModifiedOn = pklModifiedOn;
       this.pklModifiedBy = pklModifiedBy;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)  
    @Column(name="pkl_id", unique=true, nullable=false)
    public Integer getPklId() {
        return this.pklId;
    }
    
    public void setPklId(Integer pklId) {
        this.pklId = pklId;
    }

    @Column(name="pkl_location", nullable=false)
    public int getPklLocation() {
        return this.pklLocation;
    }
    
    public void setPklLocation(int pklLocation) {
        this.pklLocation = pklLocation;
    }

    @Column(name="pkl_pk_id", nullable=false)
    public int getPklPkId() {
        return this.pklPkId;
    }
    
    public void setPklPkId(int pklPkId) {
        this.pklPkId = pklPkId;
    }

    
    @Column(name="pkl_type", nullable=false)
    public int getPklType() {
        return this.pklType;
    }
    
    public void setPklType(int pklType) {
        this.pklType = pklType;
    }

    
    @Column(name="pkl_description", length=500)
    public String getPklDescription() {
        return this.pklDescription;
    }
    
    public void setPklDescription(String pklDescription) {
        this.pklDescription = pklDescription;
    }

    
    @Column(name="pkl_is_active", length=6)
    public Boolean getPklIsactive() {
        return this.pklIsactive;
    }
    
    public void setPklIsactive(Boolean pklIsactive) {
        this.pklIsactive = pklIsactive;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pkl_modified_on", length=0)
    public Date getPklModifiedOn() {
        return this.pklModifiedOn;
    }
    
    public void setPklModifiedOn(Date pklModifiedOn) {
        this.pklModifiedOn = pklModifiedOn;
    }

    
    @Column(name="pkl_modified_by", nullable=false, length=30)
    public String getPklModifiedBy() {
        return this.pklModifiedBy;
    }
    
    public void setPklModifiedBy(String pklModifiedBy) {
        this.pklModifiedBy = pklModifiedBy;
    }
    
    @Transient
	public String getActiveModel() {
		if(pklIsactive != null && pklIsactive){
			this.activeModel = "Y";
		} else {
			this.activeModel = "N";
		}
		return activeModel;
	}

	public void setActiveModel(String activeModel) {
			this.activeModel = activeModel;
	}
	
	@Column(name="is_deleted", nullable=false, length=6)
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}


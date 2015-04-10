package com.rave.visiit.entity;
// default package
// Generated 30 Jan, 2015 2:51:33 PM by Hibernate Tools 3.4.0.CR1


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.rave.visiit.util.Constants;

/**
 * PackageHotel generated by hbm2java
 */
@Entity
@Table(name="package_hotel"
    ,catalog=Constants.DB_CATELOG
)
public class PackageHotel  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pkhId;
     private Hotel Hotel;
     private Package pack;
     private Integer pkhDay;
     private Integer pkhNight;
     private String pkhDesc;
     private Boolean pkhIsActive = Boolean.TRUE;
     private Date pkhModifiedOn;
     private String pkhModifiedBy;
     private Boolean isDeleted = Boolean.FALSE;
     private String status;
     private String city;
     private String imageUrl;
     @Transient
 	 private String activeModel;
    public PackageHotel() {
    }

	
    public PackageHotel(Hotel Hotel, Package pack, Boolean pkhIsActive, String pkhModifiedBy,Boolean isDeleted,String status,String city,String imageUrl) {
        this.Hotel = Hotel;
        this.pack = pack;
        this.pkhIsActive = pkhIsActive;
        this.pkhModifiedBy = pkhModifiedBy;
        this.isDeleted = isDeleted;
        this.status =status;
        this.city = city;
        this.imageUrl = imageUrl;
    }
    public PackageHotel(String city,String status,Hotel Hotel, Package pack, Integer pkhDay, Integer pkhNight, String pkhDesc, Boolean pkhIsActive, Date pkhModifiedOn, String pkhModifiedBy,Boolean isDeleted,String imageUrl) {
       this.Hotel = Hotel;
       this.pack = pack;
       this.pkhDay = pkhDay;
       this.pkhNight = pkhNight;
       this.pkhDesc = pkhDesc;
       this.pkhIsActive = pkhIsActive;
       this.pkhModifiedOn = pkhModifiedOn;
       this.pkhModifiedBy = pkhModifiedBy;
       this.isDeleted = isDeleted;
       this.status =status;
       this.city = city;
       this.imageUrl = imageUrl;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="pkh_id", unique=true, nullable=false)
    public Integer getPkhId() {
        return this.pkhId;
    }
    
    public void setPkhId(Integer pkhId) {
        this.pkhId = pkhId;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="pkh_hd_seq_id", nullable=false)
    public Hotel getHotel() {
        return this.Hotel;
    }
    
    public void setHotel(Hotel Hotel) {
        this.Hotel = Hotel;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="pkh_pk_id", nullable=false)
    public Package getPack() {
        return this.pack;
    }
    
    public void setPack(Package pack) {
        this.pack = pack;
    }

    
    @Column(name="pkh_day")
    public Integer getPkhDay() {
        return this.pkhDay;
    }
    
    public void setPkhDay(Integer pkhDay) {
        this.pkhDay = pkhDay;
    }

    
    @Column(name="pkh_night")
    public Integer getPkhNight() {
        return this.pkhNight;
    }
    
    public void setPkhNight(Integer pkhNight) {
        this.pkhNight = pkhNight;
    }

    
    @Column(name="pkh_desc", length=500)
    public String getPkhDesc() {
        return this.pkhDesc;
    }
    
    public void setPkhDesc(String pkhDesc) {
        this.pkhDesc = pkhDesc;
    }

    
    @Column(name="pkh_is_active", nullable=false, length=2)
    public Boolean getPkhIsActive() {
        return this.pkhIsActive;
    }
    
    public void setPkhIsActive(Boolean pkhIsActive) {
        this.pkhIsActive = pkhIsActive;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pkh_modified_on", length=0)
    public Date getPkhModifiedOn() {
        return this.pkhModifiedOn;
    }
    
    public void setPkhModifiedOn(Date pkhModifiedOn) {
        this.pkhModifiedOn = pkhModifiedOn;
    }

    
    @Column(name="pkh_modified_by", nullable=false, length=30)
    public String getPkhModifiedBy() {
        return this.pkhModifiedBy;
    }
    
    public void setPkhModifiedBy(String pkhModifiedBy) {
        this.pkhModifiedBy = pkhModifiedBy;
    }

    @Column(name="is_deleted", nullable=false, length=6)
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="pkh_status", nullable=false, length=7)
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="pkh_city", nullable=false)
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="image_url")
	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		if(imageUrl != null){
			this.imageUrl = imageUrl.replace("\\", "/");
		}else{this.imageUrl = imageUrl;}
	}
	
	@Transient
	public String getActiveModel() {
		if(pkhIsActive != null && pkhIsActive){
			this.activeModel = "Y";
		} else {
			this.activeModel = "N";
		}
		return activeModel;
	}

	public void setActiveModel(String activeModel) {
			this.activeModel = activeModel;
	}
}


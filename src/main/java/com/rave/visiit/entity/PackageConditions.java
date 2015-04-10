package com.rave.visiit.entity;

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

@Entity
@Table(name = "package_condition", catalog = Constants.DB_CATELOG)
public class PackageConditions {
	
	private Integer pcSeqId;
	private Package pack;
	private String pcDescriptions;
	private Boolean pcIsactive = Boolean.TRUE;
    private Date pcModifiedOn;
    private String pcModifiedBy;
    private Boolean isDeleted = Boolean.FALSE;
    @Transient
	private String activeModel;
    
    
	public PackageConditions() {
		super();
	}

	public PackageConditions(Integer pcSeqId, Package pack,
			String pcDescriptions, Boolean pcIsactive, Date pcModifiedOn,
			String pcModifiedBy, Boolean isDeleted) {
		super();
		this.pcSeqId = pcSeqId;
		this.pack = pack;
		this.pcDescriptions = pcDescriptions;
		this.pcIsactive = pcIsactive;
		this.pcModifiedOn = pcModifiedOn;
		this.pcModifiedBy = pcModifiedBy;
		this.isDeleted = isDeleted;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pc_seq_id", unique = true, nullable = false)
	public Integer getPcSeqId() {
		return pcSeqId;
	}
	public void setPcSeqId(Integer pcSeqId) {
		this.pcSeqId = pcSeqId;
	}
		
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name="pc_pk_id", nullable=false)
	public Package getPack() {
		return pack;
	}
	public void setPack(Package pack) {
		this.pack = pack;
	}
	
	@Column(name = "pc_description", nullable = false,length=500)
	public String getPcDescriptions() {
		return pcDescriptions;
	}
	public void setPcDescriptions(String pcDescriptions) {
		this.pcDescriptions = pcDescriptions;
	}
	
	 @Column(name="pc_is_active", nullable=false, length=6)
	    public Boolean getPcIsactive() {
	        return this.pcIsactive;
	    }
	    
	    public void setPcIsactive(Boolean pcIsactive) {
	        this.pcIsactive = pcIsactive;
	    }

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name="pc_modified_on", length=0)
	    public Date getPcModifiedOn() {
	        return this.pcModifiedOn;
	    }
	    
	    public void setPcModifiedOn(Date pcModifiedOn) {
	        this.pcModifiedOn = pcModifiedOn;
	    }

	    
	    @Column(name="pc_modified_by", nullable=false, length=30)
	    public String getPcModifiedBy() {
	        return this.pcModifiedBy;
	    }
	    
	    public void setPcModifiedBy(String pcModifiedBy) {
	        this.pcModifiedBy = pcModifiedBy;
	    }
	    
	    @Column(name="is_deleted")
		public Boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
		
		@Transient
		public String getActiveModel() {
			if(pcIsactive != null && pcIsactive){
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

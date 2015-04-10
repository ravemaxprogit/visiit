package com.rave.visiit.entity;

	import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.rave.visiit.util.Constants;

	@Entity
	@Table(name = "package_category", catalog = Constants.DB_CATELOG)
	public class PackageCategory {

		@Id
		@GeneratedValue(strategy = IDENTITY)
		@Column(name="pkct_id", nullable=false)
		private Integer id;
		
		@Column(name="pkct_pk_id", nullable=false)
		private Integer pack;
		
		@Column(name="pkct_cat_id", nullable=false)
		private Integer cat;
		
		@Column(name="pkct_is_active", nullable=false, length=6)
        private Boolean isactive = Boolean.TRUE;
        
		@Column(name="is_deleted", nullable=false, length=6)
        private Boolean isDeleted = Boolean.FALSE;
	    
		@Column(name="pkct_modified_on", nullable=false, length=6)
	    private Date modifiedOn;
	    
		@Column(name="pkct_modified_by", nullable=false, length=6)
		private String modifiedBy;
		
        public PackageCategory(){}
        
        public PackageCategory(Integer pack, Integer cat) {
			super();
			this.pack = pack;
			this.cat = cat;
		}
		
		public PackageCategory(Integer pack, Integer cat, Date modifiedOn,
				String modifiedBy) {
			super();
			this.pack = pack;
			this.cat = cat;
			this.modifiedOn = modifiedOn;
			this.modifiedBy = modifiedBy;
		}
//
//		@Transient
//		public String getActiveModel() {
//			if(isactive != null && isactive){
//				this.activeModel = "Y";
//			} else {
//				this.activeModel = "N";
//			}
//			return activeModel;
//		}
//
//		public void setActiveModel(String activeModel) {
//				this.activeModel = activeModel;
//		}
//		
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getPack() {
			return pack;
		}

		public void setPack(Integer pack) {
			this.pack = pack;
		}

		public Integer getCat() {
			return cat;
		}

		public void setCat(Integer cat) {
			this.cat = cat;
		}

		public Boolean getIsactive() {
			return isactive;
		}

		public void setIsactive(Boolean isactive) {
			this.isactive = isactive;
		}

		public Boolean getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Date getModifiedOn() {
			return modifiedOn;
		}

		public void setModifiedOn(Date modifiedOn) {
			this.modifiedOn = modifiedOn;
		}

		public String getModifiedBy() {
			return modifiedBy;
		}

		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}
		
		@Override
		public String toString() {
			return "PackageCategory [id=" + id + ", pack=" + pack + ", cat="
					+ cat + ", isactive=" + isactive + ", isDeleted="
					+ isDeleted + "]";
		}

}

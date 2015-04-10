package com.rave.visiit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name="package_view",catalog=Constants.DB_CATELOG)
public class PackageView implements java.io.Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5120980883086989572L;
	 @Id @GeneratedValue(strategy=IDENTITY)
  	 @Column(name="pk_id")
	 private Integer Id;
	 @Column(name="pack_description")
	 private String packDescription;
	 @Column(name="pack_name")
	 private String pkName;
	 @Column(name="pki_description")
	 private String pkiDescription;
	 @Column(name="pkin_description")
	 private String pkinDescription;
	 @Column(name="pkex_description")
	 private String pkexDescription;
	 @Column(name="pkex_seq_id")
	 private Integer pkexSeqId;
	 @Column(name="pkin_seq_id")
	 private Integer pkinSeqId;
	 @Column(name="pki_seq_id")
	 private Integer pkiSeqId;
	public String getPackDescription() {
		return packDescription;
	}
	public void setPackDescription(String packDescription) {
		this.packDescription = packDescription;
	}
	public String getPkName() {
		return pkName;
	}
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}
	public Integer getId() {
		return Id;
	}
	public void Id(Integer Id) {
		this.Id = Id;
	}
	public String getPkiDescription() {
		return pkiDescription;
	}
	public void setPkiDescription(String pkiDescription) {
		this.pkiDescription = pkiDescription;
	}
	public String getPkinDescription() {
		return pkinDescription;
	}
	public void setPkinDescription(String pkinDescription) {
		this.pkinDescription = pkinDescription;
	}
	public String getPkexDescription() {
		return pkexDescription;
	}
	public void setPkexDescription(String pkexDescription) {
		this.pkexDescription = pkexDescription;
	}
	public Integer getPkexSeqId() {
		return pkexSeqId;
	}
	public void setPkexSeqId(Integer pkexSeqId) {
		this.pkexSeqId = pkexSeqId;
	}
	public Integer getPkinSeqId() {
		return pkinSeqId;
	}
	public void setPkinSeqId(Integer pkinSeqId) {
		this.pkinSeqId = pkinSeqId;
	}
	public Integer getPkiSeqId() {
		return pkiSeqId;
	}
	public void setPkiSeqId(Integer pkiSeqId) {
		this.pkiSeqId = pkiSeqId;
	}
	
}

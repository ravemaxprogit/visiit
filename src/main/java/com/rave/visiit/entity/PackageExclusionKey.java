package com.rave.visiit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "package_exclusion_key", catalog = Constants.DB_CATELOG)
public class PackageExclusionKey {

	@Id
	@Column(name ="pk_exclusion_key")
	private Integer key;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
	
	
}

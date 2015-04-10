package com.rave.visiit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "package_inclusion_key", catalog = Constants.DB_CATELOG)
public class PackageInclusionKey {

	@Id
	@Column(name ="pk_inclusion_key")
	private Integer key;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
	
	
}

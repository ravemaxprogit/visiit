package com.rave.visiit.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import com.rave.visiit.util.Constants;

@Entity
@Table(name = "vendor_information_key", catalog = Constants.DB_CATELOG)
public class VendorKey {
	@Id
	@Column(name ="vendorKey")
	private Integer key;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
}

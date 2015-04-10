package com.rave.visiit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rave.visiit.util.Constants;

@Entity
@Table(name = "hotel_detail_key", catalog = Constants.DB_CATELOG)
public class HotelKey {

	@Id
	@Column(name ="hotelKey")
	private Integer key;

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
	
	
}

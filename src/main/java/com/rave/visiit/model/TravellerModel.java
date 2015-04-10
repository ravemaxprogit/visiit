package com.rave.visiit.model;

import java.util.List;

import com.rave.visiit.entity.TravellerDetail;

public class TravellerModel {

	private Integer maxRecord;

	private List<TravellerDetail> travellerDetail;

	public Integer getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(Integer maxRecord) {
		this.maxRecord = maxRecord;
	}

	public List<TravellerDetail> getTravellerDetail() {
		return travellerDetail;
	}

	public void setTravellerDetail(List<TravellerDetail> travellerDetail) {
		this.travellerDetail = travellerDetail;
	}

}

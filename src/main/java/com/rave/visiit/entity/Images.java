package com.rave.visiit.entity;

public class Images {
	
	String imgName;
	String imgData;
	String imgModule;
	
	public Images(){}
	
	public Images(String imgName, String imgModule) {
		super();
		this.imgName = imgName;
		this.imgData = "";
		this.imgModule = imgModule;
	}
	
	public Images(String imgName, String imgData, String imgModule) {
		super();
		this.imgName = imgName;
		this.imgData = imgData;
		this.imgModule = imgModule;
	}

	public String getImgModule() {
		return imgModule;
	}

	public void setImgModule(String imgModule) {
		this.imgModule = imgModule;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}	

}

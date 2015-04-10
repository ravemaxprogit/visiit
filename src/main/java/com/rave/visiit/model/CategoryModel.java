package com.rave.visiit.model;

public class CategoryModel {

	private int catId;
	private String catTitle;
	private String imageUrl;
	private String iconUrl;
	private Integer seqId;
	
	public CategoryModel(){}
	
	public CategoryModel(int catId, String catTitle, String imageUrl,String iconUrl,Integer seqId) {
		
		super();
		this.catId = catId;
		this.catTitle = catTitle;
		this.seqId = seqId;
		this.imageUrl = imageUrl;
		if(iconUrl != null && !iconUrl.isEmpty()){
			this.iconUrl = iconUrl.replace("\\", "/");
		} else {this.iconUrl = iconUrl;}
	}
	
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatTitle() {
		return catTitle;
	}
	public void setCatTitle(String catTitle) {
		this.catTitle = catTitle;
	}
	public String getImgurl() {
		return imageUrl;
	}
	public void setImgurl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	public Integer getSeqId() {
		return seqId;
	}

	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}

	@Override
	public String toString() {
		return "CategoryModel [catId=" + catId + ", catTitle=" + catTitle
				+ ", imageUrl=" + imageUrl + ", iconUrl=" + iconUrl +", seqId=" + seqId +"]";
	}

}

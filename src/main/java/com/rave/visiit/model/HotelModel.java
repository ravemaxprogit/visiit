package com.rave.visiit.model;

public class HotelModel {
	
	private int pkId;
	private int hdId;
	private String hdName;
	private String hdType;
	private String hdDescription;
	private String imageUrl;
	private boolean hdBar;
	private boolean hdFood;
	private boolean hdWifi;
	private boolean hdGym;
	private boolean hafHelthclubYoga;
	private boolean hdSwimmingpool;
	private boolean hdParking;
	private boolean hdTransfer;
	private String city;
	private String hotel;
	private String status;
	private String cityName;
	private Integer phId;
	private String reviewCode;
	private boolean hdAirCondition;
	private boolean hdDoctorOnCall;
	private boolean hdlaundry;
	private boolean hdLibrary;
	private boolean hdSPA;
	private boolean hdTravelDesk;
	private boolean hdTVDVD;
	private boolean hdBussinessCenter;
	private boolean hdHeater;
	private boolean hdIroning;
	private boolean hdKids;
	private boolean hdDialphone;
	private boolean hdRoomsafe;
	private boolean hdVilla;
	private boolean hdShower;
	private boolean hdPickup;
	private boolean hdFloralRequest;
	
public HotelModel(){}
	

public HotelModel(int pkId, int hdId, String hdName, String hdType,boolean hdAirCondition,boolean hdDoctorOnCall,boolean hdlaundry,boolean hdLibrary,boolean hdSPA,boolean hdTravelDesk,
		String hdDescription, String imageUrl, boolean hdBar, boolean hdFood,boolean hdTVDVD,boolean hdBussinessCenter,boolean hdHeater,boolean hdIroning,boolean hdKids,boolean hdDialphone,
		boolean hdWifi, boolean hdGym, boolean hafHelthclubYoga,boolean hdRoomsafe,boolean hdVilla,boolean hdShower,boolean hdPickup,boolean hdFloralRequest,
		boolean hdSwimmingpool, boolean hdParking, boolean hdTransfer,
		String city, String hotel, String status,String cityName) {
	super();
	this.pkId = pkId;
	this.hdId = hdId;
	this.hdName = hdName;
	this.hdType = hdType;
	this.hdDescription = hdDescription;
	this.imageUrl = imageUrl;
	this.hdBar = hdBar;
	this.hdFood = hdFood;
	this.hdWifi = hdWifi;
	this.hdGym = hdGym;
	this.hafHelthclubYoga = hafHelthclubYoga;
	this.hdSwimmingpool = hdSwimmingpool;
	this.hdParking = hdParking;
	this.hdTransfer = hdTransfer;
	this.city = city;
	this.hotel = hotel;
	this.status = status;
	this.hdAirCondition = hdAirCondition;
	this.hdDoctorOnCall = hdDoctorOnCall;
	this.hdlaundry = hdlaundry;
	this.hdLibrary = hdLibrary;
	this.hdSPA = hdSPA;
	this.hdTravelDesk = hdTravelDesk;
	this.hdTVDVD = hdTVDVD;
	this.hdBussinessCenter = hdBussinessCenter;
	this.hdHeater = hdHeater;
	this.hdIroning = hdIroning;
	this.hdKids = hdKids;
	this.hdDialphone = hdDialphone;
	this.hdRoomsafe = hdRoomsafe;
	this.hdVilla = hdVilla;
	this.hdShower = hdShower;
	this.hdPickup = hdPickup;
	this.hdFloralRequest = hdFloralRequest;
}

	public String getHdName() {
		return hdName;
	}

	public void setHdName(String hdName) {
		this.hdName = hdName;
	}

	public String getHdType() {
		return hdType;
	}

	public void setHdType(String hdType) {
		this.hdType = hdType;
	}

	public String getHdDescription() {
		return hdDescription;
	}

	public void setHdDescription(String hdDescription) {
		this.hdDescription = hdDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isHdBar() {
		return hdBar;
	}

	public void setHdBar(boolean hdBar) {
		this.hdBar = hdBar;
	}

	public boolean isHdFood() {
		return hdFood;
	}

	public void setHdFood(boolean hdFood) {
		this.hdFood = hdFood;
	}

	public boolean isHdWifi() {
		return hdWifi;
	}

	public void setHdWifi(boolean hdWifi) {
		this.hdWifi = hdWifi;
	}

	public boolean isHdGym() {
		return hdGym;
	}

	public void setHdGym(boolean hdGym) {
		this.hdGym = hdGym;
	}

	public boolean isHafHelthclubYoga() {
		return hafHelthclubYoga;
	}

	public void setHafHelthclubYoga(boolean hafHelthclubYoga) {
		this.hafHelthclubYoga = hafHelthclubYoga;
	}

	public boolean isHdSwimmingpool() {
		return hdSwimmingpool;
	}

	public void setHdSwimmingpool(boolean hdSwimmingpool) {
		this.hdSwimmingpool = hdSwimmingpool;
	}

	public boolean isHdParking() {
		return hdParking;
	}

	public void setHdParking(boolean hdParking) {
		this.hdParking = hdParking;
	}

	public boolean isHdTransfer() {
		return hdTransfer;
	}

	public void setHdTransfer(boolean hdTransfer) {
		this.hdTransfer = hdTransfer;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPkId() {
		return pkId;
	}
	public void setPkId(int pkId) {
		this.pkId = pkId;
	}
	public int getHdId() {
		return hdId;
	}
	public void setHdId(int hdId) {
		this.hdId = hdId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getPhId() {
		return phId;
	}
	public void setPhId(Integer phId) {
		this.phId = phId;
	}
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public boolean isHdAirCondition() {
		return hdAirCondition;
	}
	public void setHdAirCondition(boolean hdAirCondition) {
		this.hdAirCondition = hdAirCondition;
	}
	public boolean isHdDoctorOnCall() {
		return hdDoctorOnCall;
	}
	public void setHdDoctorOnCall(boolean hdDoctorOnCall) {
		this.hdDoctorOnCall = hdDoctorOnCall;
	}
	public boolean isHdlaundry() {
		return hdlaundry;
	}
	public void setHdlaundry(boolean hdlaundry) {
		this.hdlaundry = hdlaundry;
	}
	public boolean isHdLibrary() {
		return hdLibrary;
	}
	public void setHdLibrary(boolean hdLibrary) {
		this.hdLibrary = hdLibrary;
	}
	public boolean isHdSPA() {
		return hdSPA;
	}
	public void setHdSPA(boolean hdSPA) {
		this.hdSPA = hdSPA;
	}
	public boolean isHdTravelDesk() {
		return hdTravelDesk;
	}
	public void setHdTravelDesk(boolean hdTravelDesk) {
		this.hdTravelDesk = hdTravelDesk;
	}
	public boolean isHdTVDVD() {
		return hdTVDVD;
	}
	public void setHdTVDVD(boolean hdTVDVD) {
		this.hdTVDVD = hdTVDVD;
	}
	public boolean isHdBussinessCenter() {
		return hdBussinessCenter;
	}
	public void setHdBussinessCenter(boolean hdBussinessCenter) {
		this.hdBussinessCenter = hdBussinessCenter;
	}
	public boolean isHdHeater() {
		return hdHeater;
	}
	public void setHdHeater(boolean hdHeater) {
		this.hdHeater = hdHeater;
	}
	public boolean isHdIroning() {
		return hdIroning;
	}
	public void setHdIroning(boolean hdIroning) {
		this.hdIroning = hdIroning;
	}
	public boolean isHdKids() {
		return hdKids;
	}
	public void setHdKids(boolean hdKids) {
		this.hdKids = hdKids;
	}
	public boolean isHdDialphone() {
		return hdDialphone;
	}
	public void setHdDialphone(boolean hdDialphone) {
		this.hdDialphone = hdDialphone;
	}
	public boolean isHdRoomsafe() {
		return hdRoomsafe;
	}
	public void setHdRoomsafe(boolean hdRoomsafe) {
		this.hdRoomsafe = hdRoomsafe;
	}
	public boolean isHdVilla() {
		return hdVilla;
	}
	public void setHdVilla(boolean hdVilla) {
		this.hdVilla = hdVilla;
	}
	public boolean isHdShower() {
		return hdShower;
	}
	public void setHdShower(boolean hdShower) {
		this.hdShower = hdShower;
	}
	public boolean isHdPickup() {
		return hdPickup;
	}
	public void setHdPickup(boolean hdPickup) {
		this.hdPickup = hdPickup;
	}
	public boolean isHdFloralRequest() {
		return hdFloralRequest;
	}
	public void setHdFloralRequest(boolean hdFloralRequest) {
		this.hdFloralRequest = hdFloralRequest;
	}
}

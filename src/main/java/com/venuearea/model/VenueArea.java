package com.venuearea.model;

import java.io.Serializable;

public class VenueArea implements Serializable{
	private Integer venueAreaID;
	private Integer venueID;
	private String areaName;
	private byte[] areaPicture;
	
	public byte[] getAreaPicture() {
		return areaPicture;
	}

	public void setAreaPicture(byte[] areaPicture) {
		this.areaPicture = areaPicture;
	}

	public VenueArea(Integer venueAreaID, Integer venueID, String areaName, byte[] areaPicture) {
		super();
		this.venueAreaID = venueAreaID;
		this.venueID = venueID;
		this.areaName = areaName;
		this.areaPicture = areaPicture;
	}

	public VenueArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VenueArea(Integer venueAreaID, Integer venueID, String areaName) {
		super();
		this.venueAreaID = venueAreaID;
		this.venueID = venueID;
		this.areaName = areaName;
	}

	public Integer getVenueAreaID() {
		return venueAreaID;
	}

	public void setVenueAreaID(Integer venueAreaID) {
		this.venueAreaID = venueAreaID;
	}

	public Integer getVenueID() {
		return venueID;
	}

	public void setVenueID(Integer venueID) {
		this.venueID = venueID;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}

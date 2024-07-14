package com.venue.model;

import java.io.Serializable;

public class Venue implements Serializable {
	private Integer venueID;
	private String venueName;
	private String venuePhone;
	private String venueContactPerson;
	private String venueAddress;
	private String venueLocation;

	public Venue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venue(Integer venueID, String venueName, String venuePhone, String venueContactPerson, String venueAddress,
			String venueLocation) {
		super();
		this.venueID = venueID;
		this.venueName = venueName;
		this.venuePhone = venuePhone;
		this.venueContactPerson = venueContactPerson;
		this.venueAddress = venueAddress;
		this.venueLocation = venueLocation;
	}

	public Integer getVenueID() {
		return venueID;
	}

	public void setVenueID(Integer venueID) {
		this.venueID = venueID;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenuePhone() {
		return venuePhone;
	}

	public void setVenuePhone(String venuePhone) {
		this.venuePhone = venuePhone;
	}

	public String getVenueContactPerson() {
		return venueContactPerson;
	}

	public void setVenueContactPerson(String venueContactPerson) {
		this.venueContactPerson = venueContactPerson;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getVenueLocation() {
		return venueLocation;
	}

	public void setVenueLocation(String venueLocation) {
		this.venueLocation = venueLocation;
	}

}

package com.venuearea.model;

import java.util.List;

public class VenueAreaService {
	private VenueAreaDAO dao;

	public VenueAreaService() {
		dao = new VenueAreaDAOImpl();
	}

	public VenueArea addVenueArea(Integer venueID, String areaName, byte[] areaPicture) {
		VenueArea venueArea = new VenueArea();

		venueArea.setVenueID(venueID);
		venueArea.setAreaName(areaName);
		venueArea.setAreaPicture(areaPicture);

		dao.insert(venueArea);

		return venueArea;
	}

	public VenueArea updateVenueArea(Integer venueAreaID, Integer venueID, String areaName, byte[] areaPicture) {
		VenueArea venueArea = new VenueArea();

		venueArea.setVenueAreaID(venueAreaID);
		venueArea.setVenueID(venueID);
		venueArea.setAreaName(areaName);
		venueArea.setAreaPicture(areaPicture);

		dao.update(venueArea);

		return venueArea;
	}

	public void deleteVenueArea(Integer venueAreaID) {
		dao.delete(venueAreaID);
	}

	public VenueArea getOneVenueArea(Integer venueAreaID) {
		return dao.findbyprimarykey(venueAreaID);
	}

	public List<VenueArea> getAll() {
		return dao.getAll();
	}
}

package com.venuearea.model;

import java.util.List;

public interface VenueAreaDAO {
	public void insert(VenueArea venueArea);
	public void delete(Integer venueAreaID);
	public void update(VenueArea venueArea);
	public VenueArea findbyprimarykey(Integer venueAreaID);
	public List<VenueArea> getAll();
}

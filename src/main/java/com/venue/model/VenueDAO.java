package com.venue.model;

import java.util.List;

public interface VenueDAO {
	public void insert(Venue venue);
	public void update(Venue venue);
	public void delete(int VenueID);
	public Venue findByPrimaryKey(int VenueID);
	public List<Venue> getAll();
}

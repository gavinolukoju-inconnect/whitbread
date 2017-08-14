package com.whitbread.foursquare.spring.formbean;

import java.util.ArrayList;
import java.util.List;

public class FourSquareDemoFormBean {
	
	private String locationName;
	private List<MapDataFormBean> venueList = new ArrayList<MapDataFormBean>();

	/*******************************************************************/
	/****************** UNCHANGED ACCESSOR METHODS FOLLOW **************/
	/*******************************************************************/
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public List<MapDataFormBean> getVenueList() {
		return venueList;
	}

	public void setVenueList(List<MapDataFormBean> venueList) {
		this.venueList = venueList;
	}
}

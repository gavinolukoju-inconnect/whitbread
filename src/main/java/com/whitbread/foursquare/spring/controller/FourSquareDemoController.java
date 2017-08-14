package com.whitbread.foursquare.spring.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.whitbread.foursquare.client.FourSquareClient;
import com.whitbread.foursquare.spring.formbean.FourSquareDemoFormBean;
import com.whitbread.foursquare.spring.formbean.MapDataFormBean;

import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

/**
 * Technical Test for whitbread
 * 
 *  OBJECTIVE
 *  =========
 *  
 *  You are tasked with creating a page or application which allows you to search for a place by 
 *  name and return the recommended or popular venues near that location.
 * 
 *  @author Gavin Olukoju
 *  @email gavin.olukoju@inconnect.co.uk
 *  
 *  
 *  https://developers.google.com/chart/interactive/docs/gallery/map
 *
 */

@Controller
public class FourSquareDemoController {
	
//	@Autowired
//	@Qualifier("fourSquareClient")
//	private FourSquareClient fourSquareClient;
	
	@RequestMapping(value="/location/search", method=RequestMethod.GET)
    public String customerLogin(ModelMap model) { 
		
		
		FourSquareClient fourSquareClient = new FourSquareClient();
		List<MapDataFormBean> venueList = new ArrayList<MapDataFormBean>();
		try {
			Result<VenuesSearchResult> result = fourSquareClient.searchVenues("Reading, UK");
			
			for (CompactVenue venue : result.getResult().getVenues()) {
				// TODO: Do something we the data
//				System.out.println(venue.toString());
				
				MapDataFormBean mapDataFormBean = new MapDataFormBean();
				mapDataFormBean.setName(venue.getName());
				mapDataFormBean.setLatitude(venue.getLocation().getLat().toString());
				mapDataFormBean.setLongitude(venue.getLocation().getLng().toString());
				
				venueList.add(mapDataFormBean);
			}
		} catch (FoursquareApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FourSquareDemoFormBean fourSquareDemoFormBean = new FourSquareDemoFormBean();
		fourSquareDemoFormBean.setVenueList(venueList);
        model.addAttribute("fourSquareDemoFormBean", fourSquareDemoFormBean);

		return "location/search";
	}
	
	@RequestMapping(value="/location/search", method=RequestMethod.POST)
    public ModelAndView processForm(@ModelAttribute(value="fourSquareDemoFormBean") FourSquareDemoFormBean formBean,BindingResult result, HttpServletRequest request, 
            HttpServletResponse response){
		
		System.out.println("Inside location/search post");
		
		if(!result.hasErrors()){
			FourSquareClient fourSquareClient = new FourSquareClient();
			try {
				Result<VenuesSearchResult> venueSearchResult = fourSquareClient.searchVenues(formBean.getLocationName());
				
				List<MapDataFormBean> venueList = new ArrayList<MapDataFormBean>();
				formBean.getVenueList().clear();
				
				for (CompactVenue venue : venueSearchResult.getResult().getVenues()) {
					// TODO: Do something we the data
//					System.out.println(venue.toString());
					
					MapDataFormBean mapDataFormBean = new MapDataFormBean();
					mapDataFormBean.setName(venue.getName());
					mapDataFormBean.setLatitude(venue.getLocation().getLat().toString());
					mapDataFormBean.setLongitude(venue.getLocation().getLng().toString());
					
					venueList.add(mapDataFormBean);
					
				}
				
				formBean.setVenueList(venueList);
			} catch (FoursquareApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException npes) {
				// TODO Auto-generated catch block
				npes.printStackTrace();
			}

			
			return new ModelAndView("location/search","fourSquareDemoFormBean",formBean);
		} else {
			return new ModelAndView("location/search","fourSquareDemoFormBean",formBean);
		}
	}
	
	/*******************************************************************/
	/****************** UNCHANGED ACCESSOR METHODS FOLLOW **************/
	/*******************************************************************/
	
//	public void setFourSquareClient(FourSquareClient fourSquareClient) {
//		this.fourSquareClient = fourSquareClient;
//	}
}

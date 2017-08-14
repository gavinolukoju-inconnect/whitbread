package com.whitbread.foursquare.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class FourSquareClient {

	private static final String CLIENT_ID = "MP2Y500WLFWHYV5PTYTA500EDKBBUAPLFQFDT1V5IVNBOENV";
	private static final String CLIENT_SECRET = "OPOQ54CYQR2LEMUMFRYKEI22QDRFQIXPWVW41ZKBUAVNFDR2";
	
	

	public Result<VenuesSearchResult> searchVenues(String near) throws FoursquareApiException {
	    // First we need a initialize FoursquareApi. 
	    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, "Callback URL");
	    
	    //String near, String query, Integer limit, String intent, String categoryId, String url, String providerId, String linkedId
	    
	    // After client has been initialized we can make queries.
	    Result<VenuesSearchResult> result = foursquareApi.venuesSearch(near, null, 250, null, null, null, null, null);
	    
	    
	    if (result.getMeta().getCode() == 200) {
	      // if query was ok we can finally we do something with the data
//	      for (CompactVenue venue : result.getResult().getVenues()) {
//	        // TODO: Do something we the data
//	        System.out.println(venue.getName());
//	      }
	      
	      return result;
	    } else {
	      // TODO: Proper error handling
	      System.out.println("Error occured: ");
	      System.out.println("  code: " + result.getMeta().getCode());
	      System.out.println("  type: " + result.getMeta().getErrorType());
	      System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
	      
	      return null;
	    }
	}
	  
	  public void handleCallback(HttpServletRequest request, HttpServletResponse response) {
	    // After user has logged in and confirmed that our program may access user's Foursquare account
	    // Foursquare redirects user back to callback url. 
	    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, "Callback URL");
	    // Callback url contains authorization code 
	    String code = request.getParameter("code");
	    try {
	      // finally we need to authenticate that authorization code 
	      foursquareApi.authenticateCode(code);
	      // ... and voilà we have a authenticated Foursquare client
	    } catch (FoursquareApiException e) {
	     // TODO: Error handling
	    }
	  }

}

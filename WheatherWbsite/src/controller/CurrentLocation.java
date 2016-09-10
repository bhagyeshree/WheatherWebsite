package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CurrentLocation {

	 public static String getCurrentLocation() throws IOException
	  	{
	  		String webPage = "http://ip-api.com/json";
	  		URL url = new URL(webPage);
	  		URLConnection urlConnection = url.openConnection();
	  		InputStream is = urlConnection.getInputStream();
	  		InputStreamReader isr = new InputStreamReader(is);

	  		int numCharsRead;
	  		char[] charArray = new char[1024];
	  		StringBuffer sb = new StringBuffer();
	  		while ((numCharsRead = isr.read(charArray)) > 0) {
	  			sb.append(charArray, 0, numCharsRead);
	  		}
	  		String result = sb.toString();			
	  		
	  		JSONObject obj = null;
	  		
			try {
				obj = (JSONObject) new JSONParser().parse(result);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
	  		
	  		String city2 = (String) obj.get("city");
	  					
	  		return city2;

	  	}
	 
	 public static String getTimeZone(String lat, String lon) throws IOException
	 {
		    String webPage = "https://maps.googleapis.com/maps/api/timezone/json?location="+lat+","+lon+"&timestamp=1331161200";
	  		URL url = new URL(webPage);
	  		URLConnection urlConnection = url.openConnection();
	  		InputStream is = urlConnection.getInputStream();
	  		InputStreamReader isr = new InputStreamReader(is);

	  		int numCharsRead;
	  		char[] charArray = new char[1024];
	  		StringBuffer sb = new StringBuffer();
	  		while ((numCharsRead = isr.read(charArray)) > 0) {
	  			sb.append(charArray, 0, numCharsRead);
	  		}
	  		String result = sb.toString();			
	  		
	  		JSONObject obj = null;
	  		
			try {
				obj = (JSONObject) new JSONParser().parse(result);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			
	  		
	  		String zone = (String) obj.get("timeZoneId");
		 
	  		
	  		return zone;
	 }
	 
}

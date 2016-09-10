package com.analytics.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebService;

import database.DB_Controller;
import model.CityWeather;

@WebService(endpointInterface = "com.analytics.ws.WebServiceWeather")
public class WebServiceServerRPC implements WebServiceWeather{

	@Override
	public CityWeather getCityWeatherInfoRPC(String cityName) {
		
		
		 boolean newOrNot = DB_Controller.checkCityEXISTS(cityName);
         
     	
       	if(newOrNot == false)
       	{
         	try {
         		
				DB_Controller.AddNewWeatherToDB(DB_Controller.createCityWeather(cityName));
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
       	}else
       	{
         	
         	Date dt = new Date();
         	Calendar c = Calendar.getInstance(); 
         	c.setTime(dt); 
         	c.add(Calendar.DATE, 15);
         	dt = c.getTime();
         	
         	
         	Date dt2 = DB_Controller.getLastDate(cityName);
         	
         	
         	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
         	
         	Date date1;
         	Date date2;
				try {
					
					date1 = df.parse(df.format(dt));
					date2  = df.parse(df.format(dt2));
					
					if(date1.after(date2))
	            	{
	            		DB_Controller.AddNewWeatherToDB_CityEXISTS(DB_Controller.createCityWeather(cityName));
	            	}
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (org.json.simple.parser.ParseException e) {
					e.printStackTrace();
				} 
         	
         	
         	
       	}
         	
         	
			return DB_Controller.getInfoForClient(cityName);
			
	}

}

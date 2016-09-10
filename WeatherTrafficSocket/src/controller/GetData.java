package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.CityWeather;
import model.Temperature;

public class GetData {
	
	public static CityWeather createCityWeather(String city1) throws ParseException
	{
		city1 = city1.replace(" ", "");

		String cityName = "";
		String country = "";
		Long cityID;
		double cityLocationLatitude = 0;
		double cityLocationLongitude = 0;
		ArrayList<Temperature> temperatureList = new ArrayList<Temperature>();
		CityWeather cityWeather = new CityWeather();

		
		
		try {
			String webPage = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+city1+"&mode=json&units=metric&cnt=16&appid=44db6a862fba0b067b1930da0d769e98";
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
			
			JSONObject obj= (JSONObject) new JSONParser().parse(result);
			
			JSONObject city = (JSONObject) obj.get("city");
			  
			cityName = (String) city.get("name");			
			country = (String) city.get("country");			
			cityID = (Long) city.get("id");			
			JSONObject coord = (JSONObject) city.get("coord");
			cityLocationLatitude = (double) coord.get("lat");			
			cityLocationLongitude = (double) coord.get("lon");
			
			JSONArray list = (JSONArray) obj.get("list");
			
			ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
			
			for (int i =0 ; i < list.size(); i++)
			{
				jsonList.add((JSONObject) list.get(i));

			}
			
			for(int i = 0; i < jsonList.size();i++ )
			{
				Temperature t = new Temperature();
				
				Long d = (Long) jsonList.get(i).get("dt");
				t.setDate(new Date( d * 1000));
				t.setCityID(cityID);
				
				try{
				t.setPressure((double) jsonList.get(i).get("pressure"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) jsonList.get(i).get("pressure");
					double d2 = l.doubleValue();
					t.setPressure(d2);
				}
				
				t.setHumidity((Long) jsonList.get(i).get("humidity"));
				
				try{
				t.setSpeed((double) jsonList.get(i).get("speed"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) jsonList.get(i).get("speed");
					double d2 = l.doubleValue();
					t.setSpeed(d2);

				}
				t.setWindDegree((Long) jsonList.get(i).get("deg"));
				t.setClouds((Long) jsonList.get(i).get("clouds"));
				
				JSONObject temp = (JSONObject) jsonList.get(i).get("temp");
				
				try{
				t.setMax((double) temp.get("max"));
				
				}catch (ClassCastException exc) {
					
					Long l = (Long) temp.get("max");
					double d2 = l.doubleValue();
					t.setMax(d2);		
				}
				
				try{
				t.setMin((double) temp.get("min"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) temp.get("min");
					double d2 = l.doubleValue();
					t.setMin(d2);		
				}
				
				try{
				t.setDay((double) temp.get("day"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) temp.get("day");
					double d2 = l.doubleValue();
					t.setDay(d2);		
				}

				try{
				t.setNight((double) temp.get("night"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) temp.get("night");
					double d2 = l.doubleValue();
					t.setNight(d2);		
				}

				try{
				t.setEvening((double) temp.get("eve"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) temp.get("eve");
					double d2 = l.doubleValue();
					t.setEvening(d2);		
				}

				try{
				t.setMorning((double) temp.get("morn"));
				}catch (ClassCastException exc) {
					
					Long l = (Long) temp.get("morn");
					double d2 = l.doubleValue();
					t.setMorning(d2);		
				}

				
				JSONArray w = (JSONArray) jsonList.get(i).get("weather");
				
				ArrayList<JSONObject> weather = new ArrayList<JSONObject>();
				
				for(int k = 0; k < w.size() ; k++)
				{
					weather.add((JSONObject) w.get(k));
				}

				t.setWeatherID((Long) weather.get(0).get("id"));
				t.setWeatherMain((String) weather.get(0).get("main"));
				t.setDescription((String) weather.get(0).get("description"));
				t.setIcon((String) weather.get(0).get("icon"));

				temperatureList.add(t);
		
			}
						
			cityWeather.setCityID(cityID);
			cityWeather.setCityName(cityName);
			cityWeather.setCityLocationLatitude(cityLocationLatitude);
			cityWeather.setCityLocationLongitude(cityLocationLongitude);
			cityWeather.setCountry(country);
			cityWeather.setTemperatureList(temperatureList);			

			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cityWeather;		
	}

}




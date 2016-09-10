package model;

import java.io.Serializable;
import java.util.ArrayList;

public class CityWeather implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cityName;
	private String country;
	private Long cityID;
	private double cityLocationLatitude;
	private double cityLocationLongitude;
	private ArrayList<Temperature> temperatureList;
	
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getCityID() {
		return cityID;
	}
	public void setCityID(Long cityID) {
		this.cityID = cityID;
	}
	public double getCityLocationLatitude() {
		return cityLocationLatitude;
	}
	public void setCityLocationLatitude(double cityLocationLatitude) {
		this.cityLocationLatitude = cityLocationLatitude;
	}
	public double getCityLocationLongitude() {
		return cityLocationLongitude;
	}
	public void setCityLocationLongitude(double cityLocationLongitude) {
		this.cityLocationLongitude = cityLocationLongitude;
	}
	public ArrayList<Temperature> getTemperatureList() {
		return temperatureList;
	}
	public void setTemperatureList(ArrayList<Temperature> temperatureList) {
		this.temperatureList = temperatureList;
	}
	
	
	public String toString()
	{
		String s = "";
		
		s = "cityName: "+getCityName()+"\n"+
		    "country: "+ getCountry()+"\n"+
			"cityID: "+ getCityID()+"\n"+
		    "cityLocationLatitude: "+getCityLocationLatitude()+"\n"+
			"cityLocationLongitude: "+getCityLocationLongitude()+"\n";
		
		
		for(int i = 0; i < getTemperatureList().size(); i++)
		{
			s = s + "------Day("+(i+1)+")------\n"+
		    "cityID: "+getTemperatureList().get(i).getCityID()+"\n"+
		    "date: "+getTemperatureList().get(i).getDate()+"\n"+
			"pressure: "+getTemperatureList().get(i).getPressure()+"\n"+
		    "humidity: "+getTemperatureList().get(i).getHumidity()+"\n"+
			"min: "+ getTemperatureList().get(i).getMin()+"\n"+
		    "max: "+getTemperatureList().get(i).getMax()+"\n"+
			"day: "+getTemperatureList().get(i).getDay()+"\n"+
		    "night: "+getTemperatureList().get(i).getNight()+"\n"+
			"morning: "+getTemperatureList().get(i).getMorning()+"\n"+
		    "evening: "+getTemperatureList().get(i).getEvening()+"\n"+
			"speed: "+getTemperatureList().get(i).getSpeed()+"\n"+
		    "windDegree: "+getTemperatureList().get(i).getWindDegree()+"\n"+
			"clouds: "+ getTemperatureList().get(i).getClouds()+"\n"+
		    "weatherID: "+ getTemperatureList().get(i).getWeatherID()+"\n"+
			"weatherMain: "+getTemperatureList().get(i).getWeatherMain()+"\n"+
		    "description: "+getTemperatureList().get(i).getDescription()+"\n"+
			"icon: "+getTemperatureList().get(i).getIcon()+"\n";
		}
				
		
		return s;
		
	}
	

}

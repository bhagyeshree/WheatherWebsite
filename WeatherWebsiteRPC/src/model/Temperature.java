package model;


import java.io.Serializable;
import java.util.Date;

public class Temperature implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cityID;
	private Date date;
	private double pressure;
	private Long humidity;
	private double min;
	private double max;
	private double day;
	private double night;
	private double evening;
	private double morning;
	private double speed;
	private Long windDegree;
	private Long clouds;
	private Long weatherID;
	private String weatherMain;
	private String description;
	private String icon;
	
	
	public Long getCityID() {
		return cityID;
	}
	public void setCityID(Long cityID) {
		this.cityID = cityID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public Long getHumidity() {
		return humidity;
	}
	public void setHumidity(Long humidity) {
		this.humidity = humidity;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getDay() {
		return day;
	}
	public void setDay(double day) {
		this.day = day;
	}
	public double getNight() {
		return night;
	}
	public void setNight(double night) {
		this.night = night;
	}
	public double getEvening() {
		return evening;
	}
	public void setEvening(double evening) {
		this.evening = evening;
	}
	public double getMorning() {
		return morning;
	}
	public void setMorning(double morning) {
		this.morning = morning;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Long getWindDegree() {
		return windDegree;
	}
	public void setWindDegree(Long windDegree) {
		this.windDegree = windDegree;
	}
	public Long getClouds() {
		return clouds;
	}
	public void setClouds(Long clouds) {
		this.clouds = clouds;
	}
	public Long getWeatherID() {
		return weatherID;
	}
	public void setWeatherID(Long weatherID) {
		this.weatherID = weatherID;
	}
	public String getWeatherMain() {
		return weatherMain;
	}
	public void setWeatherMain(String weatherMain) {
		this.weatherMain = weatherMain;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}


}

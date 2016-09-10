package com.analytics.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import model.CityWeather;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceWeather{
 
	@WebMethod CityWeather getCityWeatherInfoRPC(String cityName);

}
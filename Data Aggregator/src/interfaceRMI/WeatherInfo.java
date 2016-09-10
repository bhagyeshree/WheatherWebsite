package interfaceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.CityWeather;

public interface WeatherInfo extends Remote{
	
	CityWeather getCityWeatherInfoRMI(String cityName) throws RemoteException;

}

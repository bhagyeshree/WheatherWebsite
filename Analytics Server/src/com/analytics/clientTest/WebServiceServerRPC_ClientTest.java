package com.analytics.clientTest;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.analytics.ws.WebServiceWeather;

public class WebServiceServerRPC_ClientTest {

	public static void main(String[] args) throws Exception {
		   
		URL url = new URL("http://localhost:9999/ws/weather?wsdl");
		
	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://ws.analytics.com/", "WebServiceServerRPCService");

	        Service service = Service.create(url, qname);

	        WebServiceWeather weather = service.getPort(WebServiceWeather.class);

	        System.out.println(weather.getCityWeatherInfoRPC("Taif").toString());

	    }
}

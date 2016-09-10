package controller;

import java.io.IOException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.analytics.ws.WebServiceWeather;

import model.CityWeather;


@WebServlet("/WeatherAnalyticsDisplay")
public class WeatherAnalyticsDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public WeatherAnalyticsDisplay() {
    }

    
    
    public static CityWeather getCityWeatherInfo(String cityName)
    {
    	try {
	
    		URL url = new URL("http://localhost:9999/ws/weather?wsdl");
    		
	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://ws.analytics.com/", "WebServiceServerRPCService");

	        Service service = Service.create(url, qname);

	        WebServiceWeather weather = service.getPort(WebServiceWeather.class);
	    	
	    	CityWeather cityW = new CityWeather();
	    	
	    	cityW = (CityWeather) weather.getCityWeatherInfoRPC(cityName);    	
  	
	    	return cityW;
	    	
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CityWeather cityW = new CityWeather();
		
        String newLocation = request.getParameter("newLocation");
        String m = request.getParameter("m");

        if(m == null)
        {
        	m = "C";
        }

    
        if(newLocation == null)
        {
		   cityW = (CityWeather)getCityWeatherInfo(CurrentLocation.getCurrentLocation());
       	   request.setAttribute("newLocation",cityW.getCityName());

        }else
        {  request.setAttribute("newLocation",newLocation);
     	   try { 
 		     cityW = (CityWeather)getCityWeatherInfo(newLocation);
 			 request.setAttribute("cityInfo", cityW);		 
 			 request.setAttribute("max", Math.round(cityW.getTemperatureList().get(0).getMax()));
 		     request.setAttribute("min", Math.round(cityW.getTemperatureList().get(0).getMin()));

 			 
 			   } catch (Exception e) {
 				   cityW = (CityWeather)getCityWeatherInfo(CurrentLocation.getCurrentLocation());
 			   }
        }
        
        
        
         
        
     
	       request.setAttribute("cityInfo", cityW);

		   
		   if(m.equals("F"))
			 {
				request.setAttribute("max", Math.round(cityW.getTemperatureList().get(0).getMax()* 1.8 + 32) );
	 			request.setAttribute("min", Math.round(cityW.getTemperatureList().get(0).getMin()* 1.8 + 32));

			 }else
			 {
				request.setAttribute("max", Math.round(cityW.getTemperatureList().get(0).getMax()));
	 			request.setAttribute("min", Math.round(cityW.getTemperatureList().get(0).getMin()));

			 }

			
			String zone = CurrentLocation.getTimeZone(cityW.getCityLocationLatitude()+"", cityW.getCityLocationLongitude()+"");
	          
	        SimpleDateFormat sdf = new SimpleDateFormat("HH");

	        TimeZone tz = TimeZone.getTimeZone(zone);
	        sdf.setTimeZone(tz);
	           
	        String hoursString = sdf.format(Calendar.getInstance().getTime());
			
	        int hours = Integer.parseInt(hoursString);
	        
	    	if( hours >= 12 && hours < 18 ) 
			{
				if(m.equals("F"))
				 {
		          request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getDay()* 1.8 + 32));
				 }else
				 {
				    request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getDay()));

				 }

			}else if (hours >= 18 && hours < 22)
			{
				if(m.equals("F"))
				 {
			      request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getEvening()* 1.8 + 32));
				 }else
				 {
				     request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getEvening()));
				 }
			      
			}else if((hours >= 22 && hours < 24) || (hours >= 0 && hours < 6))
			{
				if(m.equals("F"))
				{
			      request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getNight()* 1.8 + 32));
				}else
				{
				 request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getNight()));

				}

			}else if((hours >= 0 && hours < 12))
			{
				if(m.equals("F"))
				{
			     request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getMorning()* 1.8 + 32));
				}else
				{
					   request.setAttribute("current", Math.round(cityW.getTemperatureList().get(0).getMorning()));

				}
			}
		   	   
		   	   
			
		   request.setAttribute("weatherMain", cityW.getTemperatureList().get(0).getWeatherMain());
		   request.setAttribute("details", cityW.getTemperatureList().get(0));
		   

		   SimpleDateFormat df = new SimpleDateFormat("MM-dd");
		   
		   request.setAttribute("day1", df.format(cityW.getTemperatureList().get(0).getDate()));
		   request.setAttribute("day2", df.format(cityW.getTemperatureList().get(1).getDate()));
		   request.setAttribute("day3", df.format(cityW.getTemperatureList().get(2).getDate()));
		   request.setAttribute("day4", df.format(cityW.getTemperatureList().get(3).getDate()));
		   request.setAttribute("day5", df.format(cityW.getTemperatureList().get(4).getDate()));
		   request.setAttribute("day6", df.format(cityW.getTemperatureList().get(5).getDate()));
		   request.setAttribute("day7", df.format(cityW.getTemperatureList().get(6).getDate()));
		   request.setAttribute("day8", df.format(cityW.getTemperatureList().get(7).getDate()));
		   request.setAttribute("day9", df.format(cityW.getTemperatureList().get(8).getDate()));
		   request.setAttribute("day10", df.format(cityW.getTemperatureList().get(9).getDate()));
		   request.setAttribute("day11", df.format(cityW.getTemperatureList().get(10).getDate()));
		   request.setAttribute("day12", df.format(cityW.getTemperatureList().get(11).getDate()));


		   
		   int averageTemperature = 0;
		   int totalTemperature = 0;
		   int averageHumidity =  0;
		   int totalHumidity =  0;
		   double averagePressure = 0;
		   double totalPressure = 0;
		   double averageSpeed = 0;
		   double totalSpeed = 0;
		   int averageWindDegree = 0;
		   int totalWindDegree = 0;
		   int averageClouds = 0;
		   int totalClouds = 0;

		   
		   for(int  i = 0 ; i < cityW.getTemperatureList().size(); i++ )
		   {
			   if(m.equals("F"))
				{
			     totalTemperature = (int) (totalTemperature + Math.round(cityW.getTemperatureList().get(i).getMax()* 1.8 + 32));
				}else
				{
				 totalTemperature = (int) (totalTemperature + Math.round(cityW.getTemperatureList().get(i).getMax()));

				}
			   totalHumidity = (int) (totalHumidity + cityW.getTemperatureList().get(i).getHumidity());
			   totalPressure  = totalPressure + cityW.getTemperatureList().get(i).getPressure();
			   totalSpeed = totalSpeed + cityW.getTemperatureList().get(i).getSpeed();
			   totalWindDegree = (int) (totalWindDegree + cityW.getTemperatureList().get(i).getWindDegree());
			   totalClouds = (int) (totalClouds + cityW.getTemperatureList().get(i).getClouds());
			   
		   }
		   
		   averageTemperature = totalTemperature / cityW.getTemperatureList().size();
		   averageHumidity = totalHumidity / cityW.getTemperatureList().size();
		   averagePressure = totalPressure / cityW.getTemperatureList().size();
		   averageSpeed = totalSpeed / cityW.getTemperatureList().size();
		   averageWindDegree = totalWindDegree / cityW.getTemperatureList().size();
		   averageClouds = totalClouds / cityW.getTemperatureList().size();

		   
		   averagePressure =  Math.round(averagePressure*100.0)/100.0;
		   averageSpeed =  Math.round(averageSpeed*100.0)/100.0;
		   
		   request.setAttribute("averageTemperature",averageTemperature);
		   request.setAttribute("averageHumidity",averageHumidity);
		   request.setAttribute("averagePressure",averagePressure);
		   request.setAttribute("averageSpeed",averageSpeed);
	       request.setAttribute("averageWindDegree",averageWindDegree);
		   request.setAttribute("averageClouds",averageClouds);


		   if(m.equals("F"))
		   {
		   request.setAttribute("max1",Math.round(cityW.getTemperatureList().get(0).getMax()* 1.8 + 32));
		   request.setAttribute("max2",Math.round(cityW.getTemperatureList().get(1).getMax()* 1.8 + 32));
		   request.setAttribute("max3",Math.round(cityW.getTemperatureList().get(2).getMax()* 1.8 + 32));
		   request.setAttribute("max4",Math.round(cityW.getTemperatureList().get(3).getMax()* 1.8 + 32));
		   request.setAttribute("max5",Math.round(cityW.getTemperatureList().get(4).getMax()* 1.8 + 32));
		   request.setAttribute("max6",Math.round(cityW.getTemperatureList().get(5).getMax()* 1.8 + 32));
		   request.setAttribute("max7",Math.round(cityW.getTemperatureList().get(6).getMax()* 1.8 + 32));
		   request.setAttribute("max8",Math.round(cityW.getTemperatureList().get(7).getMax()* 1.8 + 32));
		   request.setAttribute("max9",Math.round(cityW.getTemperatureList().get(8).getMax()* 1.8 + 32));
		   request.setAttribute("max10",Math.round(cityW.getTemperatureList().get(9).getMax()* 1.8 + 32));
		   request.setAttribute("max11",Math.round(cityW.getTemperatureList().get(10).getMax()* 1.8 + 32));
		   request.setAttribute("max12",Math.round(cityW.getTemperatureList().get(11).getMax()* 1.8 + 32));
		   
		   
		   request.setAttribute("min1",Math.round(cityW.getTemperatureList().get(0).getMin()* 1.8 + 32));
		   request.setAttribute("min2",Math.round(cityW.getTemperatureList().get(1).getMin()* 1.8 + 32));
		   request.setAttribute("min3",Math.round(cityW.getTemperatureList().get(2).getMin()* 1.8 + 32));
		   request.setAttribute("min4",Math.round(cityW.getTemperatureList().get(3).getMin()* 1.8 + 32));
		   request.setAttribute("min5",Math.round(cityW.getTemperatureList().get(4).getMin()* 1.8 + 32));
		   request.setAttribute("min6",Math.round(cityW.getTemperatureList().get(5).getMin()* 1.8 + 32));
		   request.setAttribute("min7",Math.round(cityW.getTemperatureList().get(6).getMin()* 1.8 + 32));
		   request.setAttribute("min8",Math.round(cityW.getTemperatureList().get(7).getMin()* 1.8 + 32));
		   request.setAttribute("min9",Math.round(cityW.getTemperatureList().get(8).getMin()* 1.8 + 32));
		   request.setAttribute("min10",Math.round(cityW.getTemperatureList().get(9).getMin()* 1.8 + 32));
		   request.setAttribute("min11",Math.round(cityW.getTemperatureList().get(10).getMin()* 1.8 + 32));
		   request.setAttribute("min12",Math.round(cityW.getTemperatureList().get(11).getMin()* 1.8 + 32));
		   }else
		   {
			   
			   request.setAttribute("max1",Math.round(cityW.getTemperatureList().get(0).getMax()));
			   request.setAttribute("max2",Math.round(cityW.getTemperatureList().get(1).getMax()));
			   request.setAttribute("max3",Math.round(cityW.getTemperatureList().get(2).getMax()));
			   request.setAttribute("max4",Math.round(cityW.getTemperatureList().get(3).getMax()));
			   request.setAttribute("max5",Math.round(cityW.getTemperatureList().get(4).getMax()));
			   request.setAttribute("max6",Math.round(cityW.getTemperatureList().get(5).getMax()));
			   request.setAttribute("max7",Math.round(cityW.getTemperatureList().get(6).getMax()));
			   request.setAttribute("max8",Math.round(cityW.getTemperatureList().get(7).getMax()));
			   request.setAttribute("max9",Math.round(cityW.getTemperatureList().get(8).getMax()));
			   request.setAttribute("max10",Math.round(cityW.getTemperatureList().get(9).getMax()));
			   request.setAttribute("max11",Math.round(cityW.getTemperatureList().get(10).getMax()));
			   request.setAttribute("max12",Math.round(cityW.getTemperatureList().get(11).getMax()));
			   
			   
			   request.setAttribute("min1",Math.round(cityW.getTemperatureList().get(0).getMin()));
			   request.setAttribute("min2",Math.round(cityW.getTemperatureList().get(1).getMin()));
			   request.setAttribute("min3",Math.round(cityW.getTemperatureList().get(2).getMin()));
			   request.setAttribute("min4",Math.round(cityW.getTemperatureList().get(3).getMin()));
			   request.setAttribute("min5",Math.round(cityW.getTemperatureList().get(4).getMin()));
			   request.setAttribute("min6",Math.round(cityW.getTemperatureList().get(5).getMin()));
			   request.setAttribute("min7",Math.round(cityW.getTemperatureList().get(6).getMin()));
			   request.setAttribute("min8",Math.round(cityW.getTemperatureList().get(7).getMin()));
			   request.setAttribute("min9",Math.round(cityW.getTemperatureList().get(8).getMin()));
			   request.setAttribute("min10",Math.round(cityW.getTemperatureList().get(9).getMin()));
			   request.setAttribute("min11",Math.round(cityW.getTemperatureList().get(10).getMin()));
			   request.setAttribute("min12",Math.round(cityW.getTemperatureList().get(11).getMin()));
			   
		   }
			
		   request.getRequestDispatcher( "/WEB-INF/WeatherAnalyticsDisplay.jsp" ).forward(
	                request, response );
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

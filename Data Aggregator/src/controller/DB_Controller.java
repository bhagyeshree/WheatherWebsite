package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import model.CityWeather;
import model.Temperature;

public class DB_Controller {
	
	
	
	public static CityWeather getInfoForClient(String cityName) 
	{
		CityWeather cityW = new CityWeather();
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://localhost/weather";
            String username = "ahmed";
            String password = "1234";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from city where cityName LIKE '%"+cityName+"%'");

            
            while( rs.next() )
            {
            	cityW.setCityID(rs.getLong("cityID"));
            	cityW.setCityLocationLatitude(rs.getDouble("cityLocationLatitude"));
            	cityW.setCityLocationLongitude(rs.getDouble("cityLocationLongitude"));
            	cityW.setCityName(rs.getString("cityName"));
            	cityW.setCountry(rs.getString("country"));
            	
      	
            }   
                        
            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "select * from temperature where cityID = "+cityW.getCityID());
            
            ArrayList<Temperature> tList = new ArrayList<Temperature>();
       	 
       	    while( rs2.next() )
            {
       	    	Temperature t = new Temperature();
       	    	
        		t.setDate(rs2.getDate("date"));      

       	    	Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");           	
            	Date date1;
            	Date date2;
            	
            	boolean before = true;
            	
				try {
					
					date1 = df.parse(df.format(date));
					date2 = df.parse(df.format(t.getDate()));

					if(date2.before(date1))
	            	{
						before = true;
	            	}
					else
					{
						before = false;
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				
				
				
				if(before == false)
				{
				t.setCityID(rs2.getLong("cityID"));
        		t.setClouds(rs2.getLong("clouds"));
        		t.setDay(rs2.getDouble("day"));
        		t.setDescription(rs2.getString("description"));
        		t.setEvening(rs2.getDouble("evening"));
        		t.setHumidity(rs2.getLong("humidity"));
        		t.setIcon(rs2.getString("icon"));
        		t.setMax(rs2.getDouble("max"));
        		t.setMin(rs2.getDouble("min"));
        		t.setMorning(rs2.getDouble("morning"));
        		t.setNight(rs2.getDouble("night"));
        		t.setPressure(rs2.getDouble("pressure"));
        		t.setSpeed(rs2.getDouble("speed"));
        		t.setWeatherID(rs2.getLong("weatherID"));
        		t.setWeatherMain(rs2.getString("weatherMain"));
        		t.setWindDegree(rs2.getLong("windDegree"));
        		
        		tList.add(t);    
				}
        		
	        
			}
  		  	
       	    
       	    
           
            
       	 cityW.setTemperatureList(tList);
            
        }
        catch( SQLException e )
        {
        	System.out.println("there is an error in the connection to the database");
        	System.out.println(e);


        } 
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
	        	System.out.println(e);

            }
        }
  
		
		
		
		return cityW;
	}
	
	public static void AddNewWeatherToDB(CityWeather cityWeather)
	{

		   Connection c = null;
	        try
	        {
	            String url = "jdbc:mysql://localhost/weather";
	            String username = "ahmed";
	            String password = "1234";
	            	
	            	
	            String sql = "insert into city (cityid, cityname, country, citylocationlatitude, citylocationlongitude) values (?, ?, ?, ?, ?)";

	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setLong(1, cityWeather.getCityID());
	            pstmt.setString( 2, cityWeather.getCityName() );
	            pstmt.setString( 3, cityWeather.getCountry() );
	            pstmt.setDouble(4, cityWeather.getCityLocationLatitude());
	            pstmt.setDouble(5, cityWeather.getCityLocationLongitude());
	            pstmt.executeUpdate();
	            
	            
	            for(int i = 0; i < cityWeather.getTemperatureList().size(); i++)
	            {
	            String sql2 = "insert into TEMPERATURE (cityid, date, pressure, humidity, min, max, day, night, evening, morning, speed, winddegree, clouds , weatherid, weathermain, description, icon) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
	            pstmt2.setLong(1, cityWeather.getCityID());
	            pstmt2.setDate(2,  new java.sql.Date(cityWeather.getTemperatureList().get(i).getDate().getTime()));
	            pstmt2.setDouble(3, cityWeather.getTemperatureList().get(i).getPressure());
	            pstmt2.setLong(4, cityWeather.getTemperatureList().get(i).getHumidity());
	            pstmt2.setDouble(5, cityWeather.getTemperatureList().get(i).getMin());
	            pstmt2.setDouble(6, cityWeather.getTemperatureList().get(i).getMax());
	            pstmt2.setDouble(7, cityWeather.getTemperatureList().get(i).getDay());
	            pstmt2.setDouble(8, cityWeather.getTemperatureList().get(i).getNight());
	            pstmt2.setDouble(9, cityWeather.getTemperatureList().get(i).getEvening());
	            pstmt2.setDouble(10, cityWeather.getTemperatureList().get(i).getMorning());
	            pstmt2.setDouble(11, cityWeather.getTemperatureList().get(i).getSpeed());
	            pstmt2.setLong(12, cityWeather.getTemperatureList().get(i).getWindDegree());
	            pstmt2.setLong(13, cityWeather.getTemperatureList().get(i).getClouds());
	            pstmt2.setLong(14, cityWeather.getTemperatureList().get(i).getWeatherID());
	            pstmt2.setString(15, cityWeather.getTemperatureList().get(i).getWeatherMain());
	            pstmt2.setString(16, cityWeather.getTemperatureList().get(i).getDescription());
	            pstmt2.setString(17, cityWeather.getTemperatureList().get(i).getIcon());
	            pstmt2.executeUpdate();
	            }

	            System.out.println("The information of " + cityWeather.getCityName()+" has been added to the database"); 
	                        
	        }
	        catch( SQLException e )
	        {
	        	System.out.println("there is an error in the connection to the database");
	        	System.out.println(e);


	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	            	System.out.println("there is an error in the connection to the database");
		        	System.out.println(e);


	            }
	        }

	}
	
	public static void  AddNewWeatherToDB_CityEXISTS(CityWeather cityWeather)
	{
		
		 Connection c = null;
		 List<Temperature> entries = new ArrayList<Temperature>();

	        try
	        {
	        	String url = "jdbc:mysql://localhost/weather";
		        String username = "ahmed";
		        String password = "1234";

	            String sql = "select * from TEMPERATURE where cityid = ?";

	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setLong( 1, cityWeather.getCityID());
	            ResultSet rs = pstmt.executeQuery();
	            
	            while( rs.next() )
	            {
	            	Temperature entry = new Temperature();
	            	entry.setDate(rs.getDate("date"));
	            	entries.add(entry);
	            }
	            
	   		   List<Temperature> newValues = new ArrayList<Temperature>();
	   		   
	   		   for(int i = 0; i < cityWeather.getTemperatureList().size(); i++)
	   		   {
	   			   boolean exists = false;
	   			   
	   			   for(int j = 0; j < entries.size(); j++ )
	   			   {
	   	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   	    		String date1 = sdf.format(cityWeather.getTemperatureList().get(i).getDate());
	   	    		String date2 = sdf.format(entries.get(j).getDate());
	   	    		
	   				   if(date1.equals(date2))
	   				   {
	   					exists = true;
	   					
	   					
	   		            Statement stmt3 = c.createStatement();
	   		            ResultSet rs2 = stmt3.executeQuery( "select * from temperature WHERE date = '"+date1+"' AND cityID ="+cityWeather.getCityID());
	   	       	    	
	   		            Temperature t = new Temperature();
	   	       	    	
	   		            t.setCityID(rs2.getLong("cityID"));
	         		    t.setClouds(rs2.getLong("clouds"));
	         		    t.setDay(rs2.getDouble("day"));
	         		    t.setDescription(rs2.getString("description"));
	         		    t.setEvening(rs2.getDouble("evening"));
	         		    t.setHumidity(rs2.getLong("humidity"));
	         		    t.setIcon(rs2.getString("icon"));
	         		    t.setMax(rs2.getDouble("max"));
	         		    t.setMin(rs2.getDouble("min"));
	         		    t.setMorning(rs2.getDouble("morning"));
	         	    	t.setNight(rs2.getDouble("night"));
	         		    t.setPressure(rs2.getDouble("pressure"));
	         		    t.setSpeed(rs2.getDouble("speed"));
	         		    t.setWeatherID(rs2.getLong("weatherID"));
	         		    t.setWeatherMain(rs2.getString("weatherMain"));
	         		    t.setWindDegree(rs2.getLong("windDegree"));
	         		    
	         		    if(!cityWeather.getTemperatureList().get(i).equals(t))
	         		    {
	         		     String sql3 = "update temperature set pressure= ?, humidity = ?,"
	         		    			+"min = ?, max = ?, day = ?, night = ?, evening = ?, morning = ?, speed = ?,"
	         		    			+"windDegree =?, clouds= ?, weatherID = ?, weatherMain = ?, description= ?, icon = ?"
	         		    			+" where date= ? AND cityID = ?";
	         		     
	     	                PreparedStatement pstmt3 = c.prepareStatement( sql3 );
	     	                
	     	                pstmt3.setDouble(1, cityWeather.getTemperatureList().get(i).getPressure());     	              
	     	                pstmt3.setLong(2, cityWeather.getTemperatureList().get(i).getHumidity());
	     	                pstmt3.setDouble(3, cityWeather.getTemperatureList().get(i).getMin());
	     	                pstmt3.setDouble(4, cityWeather.getTemperatureList().get(i).getMax());
	     	                pstmt3.setDouble(5, cityWeather.getTemperatureList().get(i).getDay());
	     	                pstmt3.setDouble(6, cityWeather.getTemperatureList().get(i).getNight());
	     	                pstmt3.setDouble(7, cityWeather.getTemperatureList().get(i).getEvening());
	     	                pstmt3.setDouble(8, cityWeather.getTemperatureList().get(i).getMorning());
	     	                pstmt3.setDouble(9, cityWeather.getTemperatureList().get(i).getSpeed());
	     	                pstmt3.setLong(10, cityWeather.getTemperatureList().get(i).getWindDegree());
	     	                pstmt3.setLong(11, cityWeather.getTemperatureList().get(i).getClouds());
	     	                pstmt3.setLong(12, cityWeather.getTemperatureList().get(i).getWeatherID());
	     	                pstmt3.setString(13, cityWeather.getTemperatureList().get(i).getWeatherMain());
	     	                pstmt3.setString(14, cityWeather.getTemperatureList().get(i).getDescription());
	     	                pstmt3.setString(15, cityWeather.getTemperatureList().get(i).getIcon());
	     	                pstmt3.setString(16, sdf.format(cityWeather.getTemperatureList().get(i).getDate()));
	     	                pstmt3.setLong(17, cityWeather.getCityID());
	     	                pstmt3.executeUpdate();
	         		    }

	   					break;
	   				   }
	   			   }
	   			   
	   			   if(exists == false)
	   			   {
	   				newValues.add(cityWeather.getTemperatureList().get(i));
	   			   }
	   		   }
	   		   
	   		   
	   		   if(newValues.size() > 0)
	   		   {
	   			   
	   			for(int i = 0; i < newValues.size(); i++)
	            {
	            String sql2 = "insert into TEMPERATURE (cityid, date, pressure, humidity, min, max, day, night, evening, morning, speed, winddegree, clouds , weatherid, weathermain, description, icon) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement pstmt2 = c.prepareStatement( sql2 );
	            pstmt2.setLong(1, cityWeather.getCityID());
	            pstmt2.setDate(2,  new java.sql.Date(newValues.get(i).getDate().getTime()));
	            pstmt2.setDouble(3, newValues.get(i).getPressure());
	            pstmt2.setLong(4, newValues.get(i).getHumidity());
	            pstmt2.setDouble(5, newValues.get(i).getMin());
	            pstmt2.setDouble(6, newValues.get(i).getMax());
	            pstmt2.setDouble(7, newValues.get(i).getDay());
	            pstmt2.setDouble(8, newValues.get(i).getNight());
	            pstmt2.setDouble(9, newValues.get(i).getEvening());
	            pstmt2.setDouble(10, newValues.get(i).getMorning());
	            pstmt2.setDouble(11, newValues.get(i).getSpeed());
	            pstmt2.setLong(12, newValues.get(i).getWindDegree());
	            pstmt2.setLong(13, newValues.get(i).getClouds());
	            pstmt2.setLong(14, newValues.get(i).getWeatherID());
	            pstmt2.setString(15, newValues.get(i).getWeatherMain());
	            pstmt2.setString(16, newValues.get(i).getDescription());
	            pstmt2.setString(17, newValues.get(i).getIcon());
	            pstmt2.executeUpdate();
	            }

	            System.out.println("The information of " + cityWeather.getCityName()+" has been updated to the database"); 
	   			
	   		   }
	   		   else
	   		   {
		            System.out.println("The information of " + cityWeather.getCityName()+" is already updated, there is no new values to add"); 

	   		   }
	   		   
	   		   

	        }
	        catch( SQLException e )
	        {
	        	System.out.println("there is an error in the connection to the database");
	        	System.out.println(e);

	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	            	
		        	System.out.println(e);

	            }
	        }

		
	}
	
	public static Date getLastDate(String cityName)
	{
		Date date = null;
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://localhost/weather";
            String username = "ahmed";
            String password = "1234";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from city where cityName LIKE '%"+cityName+"%'");

            Long id = (long) 0;
            
            while( rs.next() )
            {
            	id = rs.getLong("cityID"); 	 
            }   
            
            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "select * from temperature where cityID = "+id);
       	 
       	    while( rs2.next() )
            {
       		 date = rs2.getDate("date");
            }
            
            
        }
        catch( SQLException e )
        {
        	System.out.println("there is an error in the connection to the database");
        	System.out.println(e);


        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
	        	System.out.println(e);

            }
        }
  
       return date;
	}
	
	
	public static boolean  checkCityEXISTS(String cityName)
	{
		boolean exists = false;
		
        Connection c = null;
        try
        {
            String url = "jdbc:mysql://localhost/weather";
            String username = "ahmed";
            String password = "1234";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from city where cityName LIKE '%"+cityName+"%'");

            while( rs.next() )
            {
            if(rs.getString("cityName") != null)
            {
            	exists = true;
            	
            }else
            {
            	exists = false;
            }
            }
           
          
            
        }
        catch( SQLException e )
        {
        	System.out.println("there is an error in the connection to the database");
        	System.out.println(e);


        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
	        	System.out.println(e);

            }
        }
  
      
        return exists;
	}
	
	
	

}

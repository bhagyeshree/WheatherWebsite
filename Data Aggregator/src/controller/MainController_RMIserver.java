package controller;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import interfaceRMI.WeatherInfo;
import model.CityWeather;

public class MainController_RMIserver implements WeatherInfo{
	

    private static Socket socket;
    
    public static CityWeather getDataFromServer(String cityName)
    {

    	CityWeather cityInfo = null;
        try
        {
            
            int port = 25000;
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            DataOutputStream bw = new DataOutputStream(os);
            
            bw.writeUTF(cityName);
            bw.flush();
 
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            ObjectInputStream inFromServer = new ObjectInputStream(is);
             
            return (CityWeather) inFromServer.readObject();
            
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }    
        
        return cityInfo;
    }
 
    
    
    public static void main(String args[])
    {
     try {
    	    MainController_RMIserver obj2 = new MainController_RMIserver();
          	
          	WeatherInfo stub2 = (WeatherInfo) UnicastRemoteObject.exportObject(obj2, 0);
		    // Bind the remote object's stub in the registry
		    Registry registry2 = LocateRegistry.getRegistry();
		    registry2.bind("WeatherInfo", stub2);
		    System.out.println("Server RMI is ready");
    		        	
          	}
             catch (Exception e) {
    	    e.printStackTrace();
    	}
      }
    	
    



	@Override
	public CityWeather getCityWeatherInfoRMI(String cityName) throws RemoteException {

	
             boolean newOrNot = DB_Controller.checkCityEXISTS(cityName);
             
        	
          	if(newOrNot == false)
          	{
            	DB_Controller.AddNewWeatherToDB(MainController_RMIserver.getDataFromServer(cityName));
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
	            		DB_Controller.AddNewWeatherToDB_CityEXISTS(MainController_RMIserver.getDataFromServer(cityName));
	            	}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
            	
            	
            	
          	}
            	
            	
			return DB_Controller.getInfoForClient(cityName);
			
			
		
	}

}

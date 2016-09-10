package controller;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import model.CityWeather;
 
public class SocketServer
{
    private static Socket socket;  
    public SocketServer()
    {
    	
    	  try
          {
   
          	InetAddress add = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
              int port = 25000;
              ServerSocket serverSocket = new ServerSocket(port, 0, add);
   
              while(true)
              {
                  //Reading the message from the client
                  socket = serverSocket.accept();
                  InputStream is = socket.getInputStream();
                  DataInputStream isr = new DataInputStream(is);
                
                  String cityName = isr.readUTF();
    
                  CityWeather cityInfo = GetData.createCityWeather(cityName);

                  //Sending the response back to the client.
                  OutputStream os = socket.getOutputStream();
                  ObjectOutputStream outToClient = new ObjectOutputStream(os);
                  outToClient.writeObject(cityInfo);   
                  
                  outToClient.flush();
                  outToClient.close();
              }
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          finally
          {
              try
              {
                  socket.close();
              }
              catch(Exception e){}
          }
    	
    }
 
    
    public static void main(String[] args)
    {
    	new Thread(new Runnable() {@Override
			public void run() {	
    		
    		        System.out.println("Socket Server is ready");
    		
					SocketServer server = new SocketServer();	
					
			}
		}).start();
    }
}
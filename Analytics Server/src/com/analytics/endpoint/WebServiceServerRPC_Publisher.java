package com.analytics.endpoint;
import javax.xml.ws.Endpoint;

import com.analytics.ws.WebServiceServerRPC;

public class WebServiceServerRPC_Publisher {

	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:9999/ws/weather", new WebServiceServerRPC());
	   System.out.println("RPC server is ready");
    }

}



package com.saumya.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class NewsReader {

//	DomParser dp = new DomParser();
//	dp.readXMLDomParser();
	
	  public static void main(String[] args)  
	  {  
	    String output  = getUrlContent("https://cfo.economictimes.indiatimes.com/rss/economy");  
	    System.out.println(output);  
	    DomParser dp = new DomParser();
	    List<Articles> feedDetails = dp.readXMLDomParser("economics times", output);
	    System.out.println("Main SIZE: " + feedDetails.size());
	  }  
	
	public static String getUrlContent(String theUrl){
		String str = "";
		StringBuilder content = new StringBuilder();
		try{
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection(); // creating a urlconnection object 
			   // wrapping the urlconnection in a bufferedreader  
		    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
		    String line;  
		      // reading from the urlconnection using the bufferedreader  
		    while ((line = bufferedReader.readLine()) != null) {  
		        content.append(line + "\n");  
		    }  
		      bufferedReader.close();
		}
		catch(Exception ex) {
			
		}
		return content.toString();
	}
	
}

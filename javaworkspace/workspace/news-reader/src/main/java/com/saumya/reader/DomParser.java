package com.saumya.reader;

import java.net.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.io.*;  
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DomParser {


  public static void main(String[] args)  
  {  
    String output  = getUrlContents("https://cfo.economictimes.indiatimes.com/rss/economy");  
    System.out.println(output);  
    List<Articles> feedDetails = readXMLDomParser("economics times", output);
    System.out.println("Main SIZE: " + feedDetails.size());
  }  
  
  private static String getUrlContents(String theUrl)  
  {  
    StringBuilder content = new StringBuilder();  
  // Use try and catch to avoid the exceptions  
    try  
    {  
      URL url = new URL(theUrl); // creating a url object  
      URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
  
      // wrapping the urlconnection in a bufferedreader  
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
      String line;  
      // reading from the urlconnection using the bufferedreader  
      while ((line = bufferedReader.readLine()) != null)  
      {  
        content.append(line + "\n");  
      }  
      bufferedReader.close();  
    }  
    catch(Exception e)  
    {  
      e.printStackTrace();  
    }  
    return content.toString();  
  }  
  
  public static  List<Articles> readXMLDomParser(String publisher,String xmlData){
	  // Instantiate the Factory
	  List<Articles> feedDetails = new ArrayList();
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         try {

          // optional, but recommended
          // process XML securely, avoid attacks like XML External Entities (XXE)
          //dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          //Document doc = db.parse(new File(FILENAME));
          //Document doc =db.parse(xmlData);
         // DocumentBuilder builder = factory.newDocumentBuilder();
          InputSource is = new InputSource(new StringReader(xmlData));
          Document doc = db.parse(is);
          

          // optional, but recommended
          // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
          doc.getDocumentElement().normalize();

          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");

          
          // get <staff>
          NodeList list = doc.getElementsByTagName("item");
          Articles article = null;
          
          for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // get staff's attribute
                 // String id = element.getAttribute("id");

                  // get text
                  String title = element.getElementsByTagName("title").item(0).getTextContent();
                  String link = element.getElementsByTagName("link").item(0).getTextContent();
                  String desc = element.getElementsByTagName("description").item(0).getTextContent();

                  String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();;
                 

                  article = new Articles(link, desc, pubDate, publisher, title);
                  
                  // get salary's attribute
                  //String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                  System.out.println("Current Element :" + node.getNodeName());
                 
                  System.out.println("Title : " + title);
                  System.out.println("Link : " + link);
                  System.out.println("Description : " + desc);
                  System.out.println("Date : " + pubDate);
                  
              }
              feedDetails.add(article);
          }

          System.out.println("SIZE: " + feedDetails.size());
         
      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }
      return feedDetails;
  }
}  
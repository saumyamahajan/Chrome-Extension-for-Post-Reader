package com.saumya.reader;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.saumya.db.ArticleDetailDaoImpl;

public class ArticlesManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArticlesManager.getFeedData("economics times", "https://cfo.economictimes.indiatimes.com/rss/economy");
		
	}
	
	public static List<Articles> getFeedData(String publisher, String URL){
		
		NewsReader newsReader = new NewsReader();
		DomParser dp = new DomParser();
		
		String content = newsReader.getUrlContent(URL);
		List<Articles> articles = dp.readXMLDomParser(publisher, content);
		System.out.println("Main SIZE: " + articles.size());
		
		return articles;
	}

//	public synchronized static void getFeedData(ArticleDetailDaoImpl articleDetailDaoImpl, String publisher, String URL){
//		
//		NewsReader newsReader = new NewsReader();
//		DomParser dp = new DomParser();
//		
//		String content = newsReader.getUrlContent(URL);
//		List<Articles> articles = dp.readXMLDomParser(publisher, content);
//		System.out.println("Main SIZE: " + articles.size());
//		articleDetailDaoImpl.batchUpdateUsingJdbcTemplate(articles);
//		
//	}
//	
	
	public synchronized static String getFeedData(ArticleDetailDaoImpl articleDetailDaoImpl, String publisher, String URL){
	
	NewsReader newsReader = new NewsReader();
//	DomParser dp = new DomParser();
	
	String content = newsReader.getUrlContent(URL);
//	List<Articles> articles = dp.readXMLDomParser(publisher, content);
//	System.out.println("Main SIZE: " + articles.size());
	//articleDetailDaoImpl.batchUpdateUsingJdbcTemplate(articles);
	
	return content;
	
}

	
//public synchronized static void getFeedData1(ArticleDetailDaoImpl articleDetailDaoImpl, String publisher, String URL){
//		
//		NewsReader newsReader = new NewsReader();
//		DomParser dp = new DomParser();
//		// if 1st time content from URL and dumping in db further on content from db and newContent from URL 
//		String content = newsReader.getUrlContent(URL);
//		String currentHash = createMD5Hash(content);
//		
//		while(true) {
//		    try {
////		        perform the get request and store it in a var
//		    	content = newsReader.getUrlContent(URL);
//		 
////		        create a hash
//		    	currentHash = createMD5Hash(content);
//		 
////		        wait for 30 seconds
////		        time.sleep(30)
//		 
////		        perform the get request
//		        content = newsReader.getUrlContent(URL);
//		 
////		        create a new hash
//		        String newHash = createMD5Hash(content);
//		 
////		        check if new hash is same as the previous hash
//		        if (newHash == currentHash)
//		            continue;
//		 
////		        if something changed in the hashes
//		        else
////		            notify
//		            System.out.println("something changed");
//		 
////		            again read the website
//		        	content = newsReader.getUrlContent(URL);;
//		 
////		            create a hash
//		        	currentHash = createMD5Hash(content);;
//		 
////		            wait for 30 seconds
////		            time.sleep(30)
//		            continue;
//		    }
//		}
//		
//		List<Articles> articles = dp.readXMLDomParser(publisher, content);
//		System.out.println("Main SIZE: " + articles.size());
//		articleDetailDaoImpl.batchUpdateUsingJdbcTemplate(articles);
//		
//	}

public synchronized static void dumpData(ArticleDetailDaoImpl articleDetailDaoImpl, String publisher, String content) {
	DomParser dp = new DomParser();
	List<Articles> articles = dp.readXMLDomParser(publisher, content);
	System.out.println("Main SIZE: " + articles.size());
	articleDetailDaoImpl.batchUpdateUsingJdbcTemplate(articles);

}

public synchronized static String createMD5Hash(final String input){

   String hashtext = null;
   MessageDigest md = null;
try {
	md = MessageDigest.getInstance("MD5");
} catch (NoSuchAlgorithmException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

   // Compute message digest of the input
   byte[] messageDigest = md.digest(input.getBytes());

   hashtext = convertToHex(messageDigest);

   return hashtext;
}

private static String convertToHex(final byte[] messageDigest) {
   BigInteger bigint = new BigInteger(1, messageDigest);
   String hexText = bigint.toString(16);
   while (hexText.length() < 32) {
      hexText = "0".concat(hexText);
   }
   return hexText;
}

	
}

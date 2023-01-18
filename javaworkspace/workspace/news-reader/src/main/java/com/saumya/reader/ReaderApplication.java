package com.saumya.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.saumya.db.ArticleDetailDaoImpl;

@SpringBootApplication
public class ReaderApplication {

	public static final String pub_economics = "economics times";
	public static final String pub_economics_url = "https://cfo.economictimes.indiatimes.com/rss/economy";
	public static final String pub_cnbc = "Business Std";
	public static final String pub_cnbc_url = "https://www.business-standard.com/rss/markets-106.rss";
	public static final String pub_business = "CNBC";
	public static final String pub_business_url = "https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=10000664";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		org.springframework.context.ApplicationContext context = new ClassPathXmlApplicationContext("ArticleDataSource.xml");

		ArticleDetailDaoImpl articleDetailDaoImpl = (ArticleDetailDaoImpl)
	         context.getBean("articleDetailDaoBean");
		
		Thread economics_pub = new Thread(new Runnable() {
			   public void run() {
			       System.out.println("economics_pub running");
			       String oldHashCode = "";
			       while (true){
//			            if (condition){
//			               // your logic
//			            }
			           
			    	   String content = ArticlesManager.getFeedData(articleDetailDaoImpl, pub_economics, pub_economics_url);
			    	   String newHashCode = ArticlesManager.createMD5Hash(content);
			    	   System.out.println("outside: " + oldHashCode);
			    	   if(!newHashCode.equals(oldHashCode)) {
			    		   ArticlesManager.dumpData(articleDetailDaoImpl, pub_economics, content);
			    		   oldHashCode = newHashCode;
			    		   System.out.println("inside: " + oldHashCode);
			    	   }
			    	   try {
							Thread.sleep(1000*60);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			       }
			   }
			});
		economics_pub.start();
		
		Thread cnbc_pub = new Thread(new Runnable() {
			   public void run() {
			       System.out.println("cnbc_pub running");
			       String oldHashCode = "";
			       while (true){
//			            if (condition){
//			               // your logic
//			            }
			           
			    	   String content = ArticlesManager.getFeedData(articleDetailDaoImpl, pub_cnbc, pub_cnbc_url);
			    	   String newHashCode = ArticlesManager.createMD5Hash(content);
			    	   if(!newHashCode.equals(oldHashCode)) {
			    		   ArticlesManager.dumpData(articleDetailDaoImpl, pub_cnbc, content);
			    		   oldHashCode = newHashCode;
			    	   }
			    	   try {
							Thread.sleep(1000*60);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			       }
			      
			   }
			});
		cnbc_pub.start();
		
		Thread business_pub = new Thread(new Runnable() {
			   public void run() {
			       System.out.println("business_pub running");
			       String oldHashCode = "";
			       while (true){
//			            if (condition){
//			               // your logic
//			            }
			           
			    	   String content = ArticlesManager.getFeedData(articleDetailDaoImpl, pub_business, pub_business_url);
			    	   String newHashCode = ArticlesManager.createMD5Hash(content);
			    	   if(!newHashCode.equals(oldHashCode)) {
			    		   ArticlesManager.dumpData(articleDetailDaoImpl, pub_business, content);
			    		   oldHashCode = newHashCode;
			    	   }
			    	   try {
							Thread.sleep(1000*60);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
			       }
			   }
			});
		business_pub.start();
		
//		ArticlesManager articlesManager = new ArticlesManager();
		//List<Articles> articles = articlesManager.getFeedData("economics times", "https://cfo.economictimes.indiatimes.com/rss/economy");
		
//		Map<String, String> publisher = populatePublisher();
//		for (Map.Entry<String,String> entry : publisher.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            List<Articles> article = articlesManager.getFeedData(entry.getKey(), entry.getValue());
//            articleDetailDaoImpl.batchUpdateUsingJdbcTemplate(article);
//		}
		//System.out.println("Main SIZE: " + publisher.size());
	}

	public static Map<String, String> populatePublisher(){
		Map<String, String> publishers = new HashMap();
		
		publishers.put("economics times", "https://cfo.economictimes.indiatimes.com/rss/economy");
		publishers.put("Business Std", "https://www.business-standard.com/rss/markets-106.rss");
		publishers.put("CNBC", "https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=10000664");
		
		return publishers;
	}
	
}

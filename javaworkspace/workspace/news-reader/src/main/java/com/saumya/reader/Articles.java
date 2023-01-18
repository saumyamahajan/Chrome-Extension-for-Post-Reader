package com.saumya.reader;

import java.security.Timestamp;

public class Articles {

	//private String ID;
	private String link;
    private String description;
    private String pubDate;
    private String publisher;
    private String title;
    
    
    public Articles(String link, String description, String pubdate, String publisher, String title) {
		super();
		this.link = link;
		this.description = description;
		this.pubDate = pubdate;
		this.publisher = publisher;
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}

package com.saumya.web.service;

import java.util.List;

import com.saumya.web.Feed;

public interface FeedService {

	public List<Feed> getFeeds(String tag);
	
	
}

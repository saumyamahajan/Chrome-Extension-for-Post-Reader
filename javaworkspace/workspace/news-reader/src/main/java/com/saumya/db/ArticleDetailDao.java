package com.saumya.db;

import java.util.List;

import com.saumya.reader.Articles;

public interface ArticleDetailDao {

	boolean createFeedDetail();
	
	 int[] batchUpdateUsingJdbcTemplate(List<Articles> feedDetails);
	
}

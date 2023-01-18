package com.saumya.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.saumya.web.Feed;

@Service
public class FeedServiceImpl implements FeedService {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	  public FeedServiceImpl(JdbcTemplate jdbcTemplate) {
	      this.jdbcTemplate = jdbcTemplate;
		}
	
	@Override
	public List<Feed> getFeeds(String tag) {
		System.out.println("tag:"+tag);
		String stag="'%" +tag+ "%'";
		String sql ="";
		//"select x1.* from feeddetail x1 where
		//   (select count(*) from feeddetail x2 where x2.publisher = x1.publisher
		//   and x2.id <= x1.id )
		         //   <= 2 order by publisher"

		if (tag.equals(""))
		sql = "SELECT * FROM feeddetail";
		else {
		sql = "SELECT * FROM feeddetail where description like " +stag ;
		sql+= " or title like " +stag ;
		   
		}


		return jdbcTemplate.query(
		                sql,
		                (rs, rowNum) ->
		                        new Feed(
		                                rs.getString("title"),
		                                rs.getString("link"),
		                                rs.getString("description"),  
		                                rs.getString("pubDate"),
		                                rs.getString("publisher")
		                        )
		        );
		    }

	

}

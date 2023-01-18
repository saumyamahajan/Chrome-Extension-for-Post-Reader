package com.saumya.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.saumya.reader.Articles;

public class ArticleDetailDaoImpl implements ArticleDetailDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final String SQL_INSERT_FeedDetail="INSERT INTO FeedDetail (title, link,description, pubDate ,publisher) VALUES (?, ?, ?, ?,?)";
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplate = new JdbcTemplate(dataSource);
	   }
	
	@Override
	public boolean createFeedDetail() {
		// TODO Auto-generated method stub
		String SQL = "insert into FeedDetail (title, link,description,pubDate,publisher) values (?, ?,?,?,?)";
	      
		jdbcTemplate.update( SQL, "title","link","desc","dt","pub");
	      System.out.println("Created Record ");
	 
		return false;
	}

	@Override
	public int[] batchUpdateUsingJdbcTemplate(List<Articles> feedDetails) {
		// TODO Auto-generated method stub
		return jdbcTemplate.batchUpdate(SQL_INSERT_FeedDetail,
		        new BatchPreparedStatementSetter() {
		            @Override
		            public void setValues(PreparedStatement ps, int i) throws SQLException {
		            	Articles feedDetail=(Articles)feedDetails.get(i);
		            	    ps.setString(1, feedDetail.getTitle());
			                ps.setString(2, feedDetail.getLink());
			                ps.setString(3, feedDetail.getDescription());
			                ps.setString(4, feedDetail.getPubDate());
			                ps.setString(5, feedDetail.getPublisher());
		               
		            }
		            @Override
		            public int getBatchSize() {
		                return feedDetails.size();
		            }
					
		        });
	}

}

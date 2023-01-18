package com.saumya.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.saumya.web.service.FeedService;

@RestController
public class FeedController {

	@Autowired
	FeedService feedService;
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	
	@RequestMapping(value = "/feeds")
	   public ResponseEntity<Object> getFeed(@RequestParam("tag") String tag) {
		   List<Feed> lfeed= (List<Feed>) feedService.getFeeds(tag);
		   System.out.println("size:"+lfeed.size());
		   if(lfeed.size()==0) {
			  // return new ResponseEntity(HttpStatus.NO_CONTENT);
		       return new ResponseEntity<>("", HttpStatus.OK );
		   }
		   else
		   return new ResponseEntity<>(lfeed, HttpStatus.OK);
	   }
	
	@RequestMapping(value = "/feeds/{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> 
	      updateFeed(@PathVariable("id") String id, @RequestBody Feed feed) {
	      
		   //feedService.updateFeed(id, feed);
	      return new ResponseEntity<>("feed is updated successsfully", HttpStatus.OK);
	   }
	
	@GetMapping(path="/")
	 public String sayHello() {
	  return "Hello!! from Java Hungry";
	 }

}

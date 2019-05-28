package com.avaya;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	
	 @Override
	 public void configure (HttpSecurity http) throws Exception {
	 http
	 .authorizeRequests (). antMatchers ("/ oauth / token", "/ oauth / authorize **", "/ publishes"). permitAll ();  
	 // .anyRequest (). authenticated (); 
	 http.requestMatchers (). antMatchers ("/ private") // Deny access to "/ private"
	 .and (). authorizeRequests ()
	 .antMatchers ("/ private"). access ("hasRole ('USER')") 
	 .and (). requestMatchers (). antMatchers ("/ admin") // Deny access to "/ admin"
	 .and (). authorizeRequests ()
	 .antMatchers ("/ admin"). access ("hasRole ('ADMIN')");
	 }
	 
	 @RequestMapping ("/ publishes")
	   public String publico () {
	    return "Public Page";
	   }
	   @RequestMapping ("/ private")
	   public String privateo () {
	          return "Private Page";
	   }
	   @RequestMapping ("/ admin")
	   public String admin () {
	     return "Administrator Page";
	   }
}

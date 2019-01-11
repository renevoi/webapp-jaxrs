package com.rene.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("testDate")
public class MyResourceDate {
	
	@GET
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(value = {MediaType.TEXT_PLAIN, "text/shortdate"}) // custom mediatype
	public Date testMethod() {
		
		return Calendar.getInstance().getTime();
	}

}

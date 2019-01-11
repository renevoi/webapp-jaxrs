package com.rene.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {
	
	
	private @QueryParam("year") int year;
    private @QueryParam("start") int start;
    private @QueryParam("size") int size;
	
    
    public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStart() {
		return this.start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
    
}

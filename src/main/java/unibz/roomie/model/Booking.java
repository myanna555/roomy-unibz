package unibz.roomie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {
	
	@JsonProperty("bookingId")
	private String bookingId;
	
	@JsonProperty("roomId")
	private String roomId;
	
	@JsonProperty("day")
	private int day;
	
	@JsonProperty("month")
	private int month;
	
	@JsonProperty("year")
	private int year;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("user")
	private String userId;
	
	@JsonProperty("fromTime")
	private int fromTime;
	
	@JsonProperty("toTime")
	private int toTime;
	
	public Booking(String bid, String rid, int day, int month,
				   int year, String title, String user, int from, int to) {
		this.bookingId = bid;
		this.roomId = rid;
		this.day = day;
		this.month = month;
		this.year = year;
		this.title = title;
		this.userId = user;
		this.fromTime = from;
		this.toTime = to;
		
	}
	
	

}

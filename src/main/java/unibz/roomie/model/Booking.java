package unibz.roomie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {
	
	@JsonProperty("room")
	private String bookingId;
	
	@JsonProperty("room")
	private String roomId;
	
	@JsonProperty("day")
	private String day;
	
	@JsonProperty("month")
	private String month;
	
	@JsonProperty("year")
	private String year;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("user")
	private String userId;
	
	@JsonProperty("from")
	private String fromTime;
	
	@JsonProperty("to")
	private String toTime;
	
	public Booking(String bid, String rid, String day, String month, String year, String title, String user, String from, String to) {
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

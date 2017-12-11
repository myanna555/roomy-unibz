package unibz.roomie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Booking {

	@JsonProperty("bookingId")
	private long bookingId;

	@NotNull
	@JsonProperty(value = "roomId")
	private String roomId;

	@NotNull
	@JsonProperty(value = "day")
	private int day;

	@NotNull
	@JsonProperty(value = "month")
	private int month;

	@NotNull
	@JsonProperty(value = "year")
	private int year;

	@NotNull
	@JsonProperty(value = "title")
	private String title;

	@NotNull
	@JsonProperty(value = "userId")
	private int userId;

	@NotNull
	@JsonProperty(value = "fromTime")
	private int fromTime;

	@NotNull
	@JsonProperty(value = "toTime")
	private int toTime;
	
	public Booking(long bid, String rid, int day, int month,
				   int year, String title, int user, int from, int to) {
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

	public Booking() {
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}

	public void setToTime(int toTime) {
		this.toTime = toTime;
	}

	public long getBookingId() {
        return bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public int getFromTime() {
        return fromTime;
    }

    public int getToTime() {
        return toTime;
    }
}

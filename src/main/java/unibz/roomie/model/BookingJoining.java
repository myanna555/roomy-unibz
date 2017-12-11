package unibz.roomie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BookingJoining {

    @NotNull
    @JsonProperty(value = "bookingId")
    private Long bookingId;

    @NotNull
    @JsonProperty(value = "userId")
    private Integer userId;

    public BookingJoining() {
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

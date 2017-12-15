package unibz.roomie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unibz.roomie.dao.DataService;
import unibz.roomie.dao.DataService.BookingException;
import unibz.roomie.model.Booking;
import unibz.roomie.model.BookingJoining;
import unibz.roomie.model.User;

import javax.validation.Valid;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
public class RoomController {

    @Autowired
    DataService dataService;

	//to do

	@RequestMapping(value = "/api/room/book", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public Booking bookARoom(@Valid @RequestBody Booking booking) {
        int bookingId = dataService.book(booking);
        booking.setBookingId(bookingId);
        return booking;
	}

    @RequestMapping(value = "/api/room/cancel", method = RequestMethod.DELETE, produces = "application/json")
    public void cancelBooking(@Valid @RequestParam long bookingId, @Valid @RequestParam int userId) throws BookingException, SQLException {
        if(dataService.isBookingOwner(bookingId, userId)) {
        		dataService.delete(bookingId);        		
        }
        else {
        	System.out.println("removing participant " + userId);
        		dataService.removeParticipant(bookingId, userId);
        }
    	
    
    }

    @RequestMapping(value = "/api/room/booked", method = RequestMethod.GET)
    public List<Booking> getAllBookings(@RequestParam Optional<String> date,
                                        @RequestParam Optional<Integer> userId) {
        return dataService.getAllBookings(date, userId);
    }

    @RequestMapping(value = "/api/room/join", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public String join(@Valid @RequestBody BookingJoining bookingJoining) throws Throwable {
        int bookingId = dataService.join(bookingJoining);
        return String.format("{\"bookingId\": \"%d\"}", bookingId);
    }

    @RequestMapping(value = "/api/booking/{bookingId}/participants", method = RequestMethod.GET)
    public List<User> getAllParticipants(@PathVariable long bookingId) {
        return dataService.getBookingParticipants(bookingId);
    }



}
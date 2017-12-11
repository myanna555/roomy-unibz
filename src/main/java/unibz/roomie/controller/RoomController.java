package unibz.roomie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unibz.roomie.dao.DataService;
import unibz.roomie.model.Booking;
import unibz.roomie.model.BookingJoining;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class RoomController {

    @Autowired
    DataService dataService;

	//to do

	@RequestMapping(value = "/api/room/book", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String bookARoom(@Valid @RequestBody Booking booking) {
        try {
            int bookingId = dataService.book(booking);
            return String.format("{\"bookingId\": \"%d\"}", bookingId);
        } catch (DataService.BookingException e) {
            return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
        }
	}

    @RequestMapping(value = "/api/room/cancel", method = RequestMethod.DELETE, produces = "application/json")
    public String cancelBooking(@Valid @RequestBody long bookingId) {
        try {
            dataService.delete(bookingId);
            return "OK";
        } catch (DataService.BookingException e) {
            return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
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

}
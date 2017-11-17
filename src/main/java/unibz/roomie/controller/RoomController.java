package unibz.roomie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import unibz.roomie.dao.DataService;
import unibz.roomie.model.Booking;


@RestController
public class RoomController {

    @Autowired
    DataService dataService;

	//to do

	@RequestMapping(value = "/api/room/book", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String bookARoom(@RequestBody Booking booking) {
        try {
            dataService.book(booking);
            return "OK";
        } catch (DataService.BookingException e) {
            return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
        }
	}


	 @RequestMapping(value = "/api/room/book/{id}", method = RequestMethod.GET)
	  public String getRoomsBySimplePathWithPathVariable(
	    @PathVariable("id") long id) {
	      return "Get a specific Room with id=" + id;
	  }

}
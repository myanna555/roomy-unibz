package com.example.controller;

import java.sql.SQLException;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RoomController {

    
	//to do
	
	@RequestMapping(value = "/room/book", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public @ResponseBody String bookARoom(@RequestBody String jsonString) {
	  return jsonString;
	}
	
	
	 @RequestMapping(value = "/room/book/{id}", method = RequestMethod.GET)
	  @ResponseBody
	  public String getRoomsBySimplePathWithPathVariable(
	    @PathVariable("id") long id) {
	      return "Get a specific Room with id=" + id;
	  }

}
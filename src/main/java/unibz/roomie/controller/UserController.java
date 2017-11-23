package unibz.roomie.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import unibz.roomie.dao.UserService;
import unibz.roomie.model.User;


@RestController
@Scope("session")
@SessionAttributes("User")
public class UserController {
	
	  @Autowired
	   UserService userService;
	  
	//register user and return the status code
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String register(@RequestBody User user) {
      try {
          if(userService.registerUser(user))
          return "{\"code\":\"200\", \"userId\":\"" + String.valueOf(user.getId()) + "\"}";
          else
        	  return "{\"code\":\"400\"}";
      } catch (SQLException e) {
    	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
      }
	}
	
	
	//login user and return the status code
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String login(@RequestBody User user, HttpSession session) {
		
		session.setAttribute("user", user);
		
    try {
        if(userService.isUserValid(user)) {
        		//send user info
        		return "{\"code\":\"200\"}";
        }
        else
      	  return "{\"code\":\"400\"}";
    } catch (SQLException e) {
  	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
    }
	}

}

package unibz.roomie.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import unibz.roomie.dao.DataService;
import unibz.roomie.dao.UserService;
import unibz.roomie.model.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

//import org.springframework.web.bind.support.SessionStatus;


@RestController
@Scope("session")
@SessionAttributes("User")
public class UserController {
	final static String SUCCESS = "{\"code\":\"200\"}";
	final static String ERROR = "{\"code\":\"400\"}";
	
	
	
	  @Autowired
	   UserService userService;
	  DataService dataService;
	  
	//register user and return the status code
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String register(@RequestBody User user) {
      try {
    	  	int userId = userService.registerUser(user);
          if(userId>0) { 
        	  	
        	  	return "{\"code\":\"200\", \"userId\":\"" + String.valueOf(userId) + "\"}";
          }
          else {
        	  	return ERROR;
          }
      } catch (SQLException e) {
    	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
      }
	}
	
	
	//login user and return the status code
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public String login(@RequestBody User user, HttpSession session) {		
		
    try {
        if(userService.isUserValid(user)) {
        		//query user by email and create User object to return
        		user = userService.getUserInfo(user.getEmail());
        		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        		String jsonUser = writer.writeValueAsString(user);        		
        		String jsonCode = "{\"code\":\"200\",";
        		return jsonCode.concat(jsonUser.replaceAll("\\{", ""));
        }
        else
      	  return ERROR;
    } catch (JsonProcessingException e) {
  	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
    		}
    catch (SQLException e) {
  	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
    		}
	}
	
	//logout user and return the status code
		@RequestMapping(value = "/api/user/logout", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		  public String logout(@RequestBody User user, HttpSession session) {		
			
	    try {
	        if(userService.doesUserExist(user)) {
	        		return SUCCESS;
	        }
	        else
	      	  	return ERROR;
	    }
	    catch (SQLException e) {
	  	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
	    		}
		}
		
		
		//get user info by id
		@RequestMapping(value = "/api/user/userinfo/{id}", method = RequestMethod.GET, produces = "application/json")
		  public String getUserInfo(@PathVariable("id") int userId, HttpSession session) {		
			
	    try {	    	
	    		if(userService.doesUserExist(new User(userId))) {
	    			User userInfo = userService.getUserInfo(userId);
	        		ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
	        		String jsonUser = writer.writeValueAsString(userInfo);        		
	        		String jsonCode = "{\"code\":\"200\",";
	        		return jsonCode.concat(jsonUser.replaceAll("\\{", ""));
	        }
	        else
	      	  return ERROR;
	    } catch (JsonProcessingException e) {
	  	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
	    		}
	    catch (SQLException e) {
	  	  	return String.format("%s, Reason: %s", e.getMessage(), e.getCause().getMessage());
	    		}
		}
		
	
	
}

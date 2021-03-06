package unibz.roomie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	  public User register(@RequestBody User user) throws SQLException {
    	  	int userId = userService.registerUser(user);
          if(userId>0) {
        	  	return userService.getUserInfo(userId);
          }
          else {
        	  throw new UserNotExistsException("Registration for " + user.getEmail() + " is unsuccessful", null);
          }
     
	}
	
	
	//login user and return the status code
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	  public User login(@RequestBody User user, HttpSession session) throws SQLException {

        if(userService.isUserValid(user)) {
            //query user by email and create User object to return
            return userService.getUserInfo(user.getEmail());
        } else {
            throw new UserNotExistsException("User " + user.getEmail() + " does not exist", null);
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
		  public User getUserInfo(@PathVariable("id") int userId, HttpSession session) throws SQLException {

            if (userService.doesUserExist(new User(userId))) {
                return userService.getUserInfo(userId);
            } else {
                throw new UserNotExistsException("User " + userId + " does not exist", null);
            }
        }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class UserNotExistsException extends RuntimeException {
        public UserNotExistsException(String s, Exception e) {
            super(s, e);
        }
    }
}

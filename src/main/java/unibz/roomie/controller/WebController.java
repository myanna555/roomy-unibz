package unibz.roomie.controller;


import java.io.IOException;
//import java.net.URL;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


import unibz.roomie.dao.UserService;
import unibz.roomie.model.User;

@Controller
@Scope("session")
public class WebController {	
	
	 @Autowired
	   UserService userService;
	 //private String apiString = "https://webservices.scientificnet.org/rest/uisdata/api/v1/timetable/?fromDate=2017-12-19&toDate=2017-12-19&department=13";
	 	 
	//login user via form
			@RequestMapping(value = "/demo/login", method = RequestMethod.POST)
			  public ModelAndView rooms(@RequestParam String email, @RequestParam String password, HttpSession session) throws SQLException, IOException {
				User user = new User(email, password);
				ModelAndView mav=null;
				if(userService.isUserValid(user)) {
	        		//query user by email and create User object to return
	        			user = userService.getUserInfo(user.getEmail());
	        			mav = new ModelAndView("profile");
	        			session.setAttribute("loggedUser", user);
				}
				else {
					mav = new ModelAndView("index");
					mav.addObject("errorMessage", "Sorry, buddy, we don't recognize your credentials. Please try again.");
				}
		        return mav;
		    }
}

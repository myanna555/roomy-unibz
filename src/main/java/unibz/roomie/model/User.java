package unibz.roomie.model;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@Scope("session")
public class User {
	
	@JsonProperty("userId")
	private int userId;
	
	@NotNull
	@JsonProperty("email")
	private String email;
	
	@NotNull
	@JsonProperty("password")
	private String password;
	
	@NotNull
	@JsonProperty("first")
	private String firstName;
	
	@NotNull
	@JsonProperty("last")
	private String lastName;
	
	@NotNull
	@JsonProperty("phone")
	private String phone;
	
	@NotNull
	@JsonProperty("faculty")
	private String faculty;
	
	
	@JsonProperty("picture")
	private String picture;
	

	
    
    public User(int userId, String email, String password, String first, String last, String phone, String faculty, String picture) {
        this.userId = userId;
    		this.email = email;
        this.password = password;
        this.firstName = first;
        this.lastName = last;
        this.phone = phone;
        this.faculty = faculty;
        this.picture = picture;
    }
    
    public User() {
    		this.userId = 0;
    		this.email ="";
    		this.password ="";
    		this.firstName ="";
    		this.lastName="";
    		this.phone="";
    		this.faculty="";
    		this.picture="";
    }
    
    
    //partial constructors

    
    public User(int id) {
        this.userId = id;
    }


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    //getters and setters

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String pass) {
    		this.password = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getFaculty() {
    	return this.faculty;
    }
    
    public void setFaculty(String faculty) {
    		this.faculty = faculty;
    }
    
    public String getPicture() {
    		return this.picture;
    }
    
    public void setPicture(String pic) {
    		this.picture = pic;
    }

 
    
    public String toString() {
    		return "user -> [id:" + this.getId() + "; email:" + this.getEmail() + 
    				"; first:" + this.getFirstName() + "; last:" + this.getLastName() + "; phone:" + this.getPhone() + 
    				"; faculty:" + this.getFaculty() + "; pic:" + this.getPicture();
    }
}

